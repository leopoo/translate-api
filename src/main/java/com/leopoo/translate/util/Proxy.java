package com.leopoo.translate.util;

import org.apache.http.HttpHost;

public interface Proxy {

    /**
     * 设置代理
     * 
     * @param proxy
     */
    public void setProxy(HttpHost proxy);

}
