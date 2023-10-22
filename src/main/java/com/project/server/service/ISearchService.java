package com.project.server.service;

import com.project.server.entity.FlowerDto;

import java.util.List;

public interface ISearchService {

    List<FlowerDto> findFlowerByNameLike(String name);
}
