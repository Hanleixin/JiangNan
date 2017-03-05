package com.bwei.newhan.home.model;

public class ApplicationConstants {

    public static  final String URL_PICTURE = "http://c.m.163.com/nc/article/headline/";
    public static  final String APP_P = "-20.html";

    public static String getUrl(String mParam2,int currentPage){
        String url = URL_PICTURE+mParam2+"/"+currentPage+APP_P;
        return url;
    }

}
