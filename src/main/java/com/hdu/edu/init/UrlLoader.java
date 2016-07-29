package com.hdu.edu.init;

import com.hdu.edu.exception.ErrorkeywordException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Created by huazi on 16/7/29.
 */
@Service
public class UrlLoader {


    private final static String URL = "http://tieba.baidu.com/f?kw={0}&ie=utf-8&tab=good";

    public static String GetTowards(String keyword) throws ErrorkeywordException {
        if (keyword.isEmpty() || keyword.equals("")) {
            throw new ErrorkeywordException("keyword 不能为空");
        }

        return MessageFormat.format(URL, keyword.trim());
    }


}
