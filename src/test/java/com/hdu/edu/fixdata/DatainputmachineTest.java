package com.hdu.edu.fixdata;

import com.hdu.edu.exception.ErrorkeywordException;
import com.hdu.edu.init.UrlLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huazi on 16/7/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/BaiduCrawler.xml"})
public class DatainputmachineTest {
    @Autowired
    Datainputmachine datainputmachine;

    UrlLoader urlLoader = new UrlLoader();

    @Test
    public void getData() {
        try {
            datainputmachine.getData(urlLoader.GetTowards("java", 0));
        } catch (ErrorkeywordException e) {
            e.printStackTrace();
        }
        System.out.println(datainputmachine.getContent());
    }

}