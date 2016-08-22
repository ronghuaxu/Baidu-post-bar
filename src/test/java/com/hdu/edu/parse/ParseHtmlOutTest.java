package com.hdu.edu.parse;

import com.hdu.edu.exception.ErrorkeywordException;
import com.hdu.edu.fixdata.Datainputmachine;
import com.hdu.edu.init.UrlLoader;
import com.unionpay.common.utils.json.JsonUtil;
import org.htmlcleaner.XPatherException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

/**
 * Created by huazi on 16/7/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/BaiduCrawler.xml"})
public class ParseHtmlOutTest {
    @Autowired
    ParseHtmlOut parseHtmlOut;
    @Autowired
    Datainputmachine datainputmachine;

    @Autowired
    UrlLoader urlLoader;


    @Test
    public void getInfoFromResult() throws ErrorkeywordException, ParserConfigurationException, XPatherException, SAXException, XPathExpressionException, IOException {
        String keyword = "linux";

        datainputmachine.getData(urlLoader.GetTowards(keyword, 0));
        //获取第一页的json数据
        System.out.println("=========================1页=========================");
        parseHtmlOut.getInfoFromResult(datainputmachine.getContent(), keyword);
        int number;
        //判断是否存在多页
        if (parseHtmlOut.getfinalurl(datainputmachine.getContent()) != null) {
            number = PageCount.out(parseHtmlOut.getfinalurl(datainputmachine.getContent()));
        } else {
            return;
        }

        for (int i = 1; i <= number; i++) {
            datainputmachine.getData(urlLoader.GetTowards(keyword, i));
            System.out.println("=========================" + (i + 1) + "页=========================");
            parseHtmlOut.getInfoFromResult(datainputmachine.getContent(), keyword);
        }

    }


}