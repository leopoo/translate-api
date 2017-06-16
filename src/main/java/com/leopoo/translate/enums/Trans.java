package com.leopoo.translate.enums;

/**
 * 翻译方式
 *
 * @author zbb
 * @create 2017-06-14 15:43
 */
public enum Trans {
    Baidu("http://fanyi.baidu.com/v2transapi"),
    Google("https://translate.google.com.hk/translate_a/single"),
    Youdao("http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule&smartresult=ugc&sessionFrom=https://www.baidu.com/link");

    private final String url;

    Trans(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
