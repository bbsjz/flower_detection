package com.project.server.controller;


import com.project.server.entity.Flower;
import com.project.server.entity.FlowerDto;
import com.project.server.entity.InfoIdDto;
import com.project.server.service.IInfoService;
import com.project.server.service.ISearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
@Api("花卉信息接口")
public class searchController {

    @Autowired
    ISearchService searchService;

    @Autowired
    IInfoService infoService;


    @GetMapping(value="findByName",produces = "application/json;charset=UTF-8")
    @ApiOperation("按文本模糊匹配所有花卉标签")
    public ResponseEntity<List<FlowerDto>> findByName(String name)
    {
        return ResponseEntity.ok(searchService.findFlowerByNameLike(name));
    }

    @GetMapping("getAll")
    @ApiOperation("获取全部类别信息")
    public ResponseEntity<List<Flower>> getAllFlower()
    {
        return ResponseEntity.ok(searchService.getAllFlower());
    }

    @GetMapping("getFlowerById")
    @ApiOperation("获取某个类别信息")
    public ResponseEntity<InfoIdDto> getFlowerById(int id)
    {
        return ResponseEntity.ok(infoService.findInfoDtoById(id));
    }
}
