package com.leopoo.translate;

public enum LANG {

    EN("en", "英文"), // 英文
    ZH("zh-cn", "简体中文");

    /**
     * 
     * @param language 语言检测返回字符串
     * @param label 源语言中文名称
     */
    LANG(String language, String label) {
        this.language = language;
        this.label = label;
    }

    private final String language;

    private final String label;

    public String getLanguage() {
        return language;
    }

    public String getLabel() {
        return label;
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

}
