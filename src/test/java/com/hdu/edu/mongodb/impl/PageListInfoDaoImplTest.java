package com.hdu.edu.mongodb.impl;

import com.hdu.edu.model.PageListInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by huazi on 16/8/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/BaiduCrawler.xml"})
public class PageListInfoDaoImplTest {

    @Autowired
    private PageListInfoDaoImpl pageListInfoDaoImpl;


    @Test
    public void savePageListInfo() throws Exception {
        PageListInfo pageListInfo = new PageListInfo();
        pageListInfo.setUrl("aa");
        pageListInfo.setCount(222);
        pageListInfo.setPostData("dsfsd");
        pageListInfo.setTitle("dsafasd");
        System.out.println(pageListInfoDaoImpl.savePageListInfo(pageListInfo));
    }

}