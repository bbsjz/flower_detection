package com.project.server.service.impl;

import com.project.server.dao.InfoJpaRepository;
import com.project.server.entity.Info;
import com.project.server.service.IInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService implements IInfoService {
    @Autowired
    InfoJpaRepository infoJpaRepository;

    @Override
    public Info findInfoById(int id) {
        return infoJpaRepository.findById(Integer.valueOf(id)).get();
    }
}
