package com.project.server.service.impl;

import com.project.server.dao.InfoJpaRepository;
import com.project.server.dao.UrlsJpaRepository;
import com.project.server.entity.Info;
import com.project.server.entity.InfoIdDto;
import com.project.server.entity.Urls;
import com.project.server.service.IInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class InfoService implements IInfoService {
    @Autowired
    InfoJpaRepository infoJpaRepository;

    @Autowired
    UrlsJpaRepository urlsJpaRepository;

    @Override
    public Info findInfoById(int id) {
        try {
            return infoJpaRepository.findById(Integer.valueOf(id)).get();
        }
        catch(NoSuchElementException ex)
        {
            return null;
        }
    }

    @Override
    public InfoIdDto findInfoDtoById(int id)
    {
        try{
            Info info=findInfoById(id);
            Urls urls=urlsJpaRepository.findById(id).get();
            InfoIdDto infoIdDto=new InfoIdDto();
            infoIdDto.setInfo(info.getInfo());
            infoIdDto.setUrl1(urls.getUrl1());
            infoIdDto.setUrl2(urls.getUrl2());
            infoIdDto.setUrl3(urls.getUrl3());
            return infoIdDto;
        }
        catch (NoSuchElementException ex)
        {
            return null;
        }
    }
}
