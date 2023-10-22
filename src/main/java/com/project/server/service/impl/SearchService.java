package com.project.server.service.impl;

import com.project.server.dao.FlowerJpaRepository;
import com.project.server.entity.Flower;
import com.project.server.entity.FlowerDto;
import com.project.server.entity.Info;
import com.project.server.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService implements ISearchService {
    @Autowired
    FlowerJpaRepository flowerJpaRepository;
    @Autowired
    InfoService infoService;

    @Override
    public List<FlowerDto> findFlowerByNameLike(String name)
    {
        List<Flower> flowers=flowerJpaRepository.findByNameLike(name);
        List<FlowerDto> flowerDtos= new ArrayList<>();
        for(Flower f:flowers)
        {
            FlowerDto flowerDto=new FlowerDto();
            Info info=infoService.findInfoById(f.getId());
            flowerDto.setInfo(info.getInfo());
            flowerDto.setId(f.getId());
            flowerDto.setName_en(f.getName_en());
            flowerDto.setName_zh(f.getName_zh());
            flowerDto.setUrl(f.getUrl());
            flowerDtos.add(flowerDto);
        }
        return flowerDtos;
    }
}
