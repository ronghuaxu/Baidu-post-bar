package com.hdu.edu.parse;

import com.hdu.edu.model.PageListInfo;
import com.hdu.edu.mongodb.impl.PageListInfoDaoImpl;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huazi on 16/7/29.
 */
@Service
public class ParseHtmlOut {

    @Autowired
    private PageListInfoDaoImpl pageListInfoDaoImpl;

    public static final int NUMBER_PN = 50;
    HtmlCleaner hc = new HtmlCleaner();

    public void getInfoFromResult(String content, String keyword) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, XPatherException {

        TagNode tn = hc.clean(content);
//        List<PageListInfo> InfoList = new ArrayList<>();


        for (int i = 1; i <= NUMBER_PN; i++) {

            String countXpath = "//*[@id=\"thread_list\"]/li[" + i + "]/div/div[1]/span";

            String titleXpath = "//*[@id=\"thread_list\"]/li[" + i + "]/div/div[2]/div[1]/div[1]/a";

            String dateXpath = "//*[@id=\"thread_list\"]/li[" + i + "]/div/div[2]/div[2]/div[2]/span[2]";

            Object[] titleobjarr = null;

            titleobjarr = tn.evaluateXPath(titleXpath);

            PageListInfo pageLIstInfo = new PageListInfo();
            if (titleobjarr != null && titleobjarr.length > 0) {

                for (Object obja : titleobjarr) {
                    TagNode tna = (TagNode) obja;
                    String url = tna.getAttributeByName("href");
                    if (!url.isEmpty()) {
                        pageLIstInfo.setUrl(url);
                    }
                    String str = tna.getText().toString();
                    if (!str.isEmpty()) {
                        pageLIstInfo.setTitle(str);
                    }
                }


            }
            Object[] countobjarr = null;
            countobjarr = tn.evaluateXPath(countXpath);

            if (countobjarr != null && countobjarr.length > 0) {

                for (Object obja : countobjarr) {
                    TagNode tna = (TagNode) obja;
                    String str = tna.getText().toString();
                    if (!str.isEmpty()) {
                        pageLIstInfo.setCount(Integer.valueOf(str));
                    }
                }
            }

            Object[] postdateobjarr = null;
            postdateobjarr = tn.evaluateXPath(dateXpath);

            if (postdateobjarr != null && postdateobjarr.length > 0) {

                for (Object obja : postdateobjarr) {
                    TagNode tna = (TagNode) obja;
                    String str = tna.getText().toString().trim();
                    if (!str.isEmpty()) {
                        pageLIstInfo.setPostData(str);
                    }
                }
            }
            //精华帖子没有达到50的分页数量
            if (pageLIstInfo.getUrl() == null) {
                break;
            }
            pageLIstInfo.setKeyword(keyword);
            System.out.println(pageListInfoDaoImpl.savePageListInfo(pageLIstInfo));

//            InfoList.add(pageLIstInfo);

        }


//        return InfoList;
    }

    public String getfinalurl(String content) throws XPatherException {

        TagNode tn = hc.clean(content);
        Object[] pageNumber = null;
        pageNumber = tn.evaluateXPath(PageCount.PAGENUMBER);
        String FinalUrl = null;
        for (Object obj : pageNumber) {
            TagNode tna = (TagNode) obj;
            FinalUrl = tna.getAttributeByName("href");
        }
        return FinalUrl;
    }

}
