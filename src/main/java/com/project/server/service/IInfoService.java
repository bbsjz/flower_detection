package com.project.server.service;


import com.project.server.entity.Info;
import com.project.server.entity.InfoIdDto;

public interface IInfoService {
    Info findInfoById(int id);

    InfoIdDto findInfoDtoById(int id);
}