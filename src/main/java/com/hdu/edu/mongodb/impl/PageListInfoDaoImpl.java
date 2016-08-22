package com.hdu.edu.mongodb.impl;

import com.hdu.edu.model.PageListInfo;
import com.hdu.edu.mongodb.PageListInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by huazi on 16/8/22.
 */
@Repository
public class PageListInfoDaoImpl implements PageListInfoDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String savePageListInfo(PageListInfo pageListInfo) {
        if (null == pageListInfo.getId()) {
            mongoTemplate.insert(pageListInfo);
        } else {
            mongoTemplate.save(pageListInfo);
        }
        return pageListInfo.getId();
    }
}
