package com.hdu.edu.fixdata;

import net.dongliu.requests.Requests;
import org.springframework.stereotype.Service;

/**
 * Created by huazi on 16/7/29.
 */
@Service("datainputmachine")
public class Datainputmachine {

    private String content;
    private int pagenum;

    public void getData(String target) {
        setContent(Requests.get(target).send().readToText());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }
}
