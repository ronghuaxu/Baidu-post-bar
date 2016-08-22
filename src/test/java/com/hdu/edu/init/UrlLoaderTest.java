package com.hdu.edu.init;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by huazi on 16/7/29.
 */
public class UrlLoaderTest {
    UrlLoader urlLoader = new UrlLoader();

    @Test
    public void getTowards() throws Exception {
        System.out.println(urlLoader.GetTowards("java", 0));

    }

}