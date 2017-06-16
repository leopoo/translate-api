package com.leopoo.translate.enums;

public enum LANG {

    AUTO("auto", "auto", "auto", "auto", "自动检测"), // 语言检测,百度,谷歌,有道,名称
    EN("en", "en", "en", "EN", "英文"), // 语言检测,百度,谷歌,有道,名称
    ZH("zh-cn", "zh", "zh-CN", "ZH_CN", "简体中文");

    /**
     * 
     * @param language 语言检测返回字符串
     * @param name 源语言中文名称
     */
    LANG(String language, String baidu, String google, String youdao, String name) {
        this.language = language;
        this.baidu = baidu;
        this.google = google;
        this.youdao = youdao;
        this.name = name;
    }

    private final String language;

    private final String baidu;

    private final String google;

    private final String youdao;

    private final String name;

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取语言枚举， 没有找到返回英文
     * 
     * @param language
     * @return
     */
    public static LANG getLang(String language) {
        for (LANG l : LANG.values()) {
            if (l.getLanguage().equals(language)) { return l; }
        }
        return LANG.EN;
    }

    public String getBaidu() {
        return baidu;
    }

    public String getGoogle() {
        return google;
    }

    public String getYoudao() {
        return youdao;
    }
}
