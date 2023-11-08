package com.project.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.server.entity.Flower;
import com.project.server.entity.Possibility;
import com.project.server.service.ISearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("image")
@Api("图片接口")
public class ImageController {
    @Autowired
    ISearchService iSearchService;

    @PostMapping("upload")
    @ApiOperation("上传图片，获取图片相似度预测结果")
    public List<Possibility> getSimilarity(@RequestParam("image") MultipartFile file)
    {
        List<Possibility> possibilityList = new ArrayList<>();
        String url = "http://127.0.0.1:5000/image/search";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(url);
            //添加header
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("utf-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//加上此行代码解决返回中文乱码问题
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
                JSONObject jsonObject =  JSON.parseObject(result);
                JSONArray jsonArray=jsonObject.getJSONArray("possibility");
                for(int i=0;i<jsonArray.toArray().length;i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    int id= (int) jsonObject1.get("id");
                    String similarity=(String) jsonObject1.get("similarity");
                    String url0=(String) jsonObject1.get("url0");
                    String url1=(String) jsonObject1.get("url1");
                    Flower flower=iSearchService.getFlowerById(id);
                    Possibility possibility=new Possibility();
                    possibility.setSimilarity(similarity);
                    possibility.setId(id);
                    possibility.setUrl1(url1);
                    possibility.setUrl0(url0);
                    possibility.setName_zh(flower.getName_zh());
                    possibility.setName_en(flower.getName_en());
                    possibilityList.add(possibility);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return possibilityList;
}}
