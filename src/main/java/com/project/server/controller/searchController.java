package com.project.server.controller;


import com.project.server.entity.FlowerDto;
import com.project.server.service.impl.SearchService;
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
@Api("文本搜索接口")
public class searchController {

    @Autowired
    SearchService searchService;

    @GetMapping(value="",produces = "application/json;charset=UTF-8")
    @ApiOperation("按文本模糊匹配所有标签")
    public ResponseEntity<List<FlowerDto>> findByName(String name)
    {
        List<FlowerDto> list=searchService.findFlowerByNameLike(name);
        return ResponseEntity.ok(list);
    }
}
