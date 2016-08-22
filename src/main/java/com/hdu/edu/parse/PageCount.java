package com.hdu.edu.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huazi on 16/8/19.
 */
public class PageCount {
    public final static String PAGENUMBER = "//*[@id=\"frs_list_pager\"]/a[last()]";
    public final static String PATTERN = "(.*pn=)(.*)";

    public static int out(String href) {
        Pattern pa = Pattern.compile(PATTERN);
        Matcher ma = pa.matcher(href);
        if (ma.find()) {
            return Integer.parseInt(ma.group(2)) / 50;
        }
        //无分页
        return 0;
    }


}

