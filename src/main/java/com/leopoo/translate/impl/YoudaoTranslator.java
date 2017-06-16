package com.leopoo.translate.impl;

import com.leopoo.http.HttpParams;
import com.leopoo.http.HttpPostParams;
import com.leopoo.translate.AbstractOnlineTranslator;
import com.leopoo.translate.enums.LANG;
import com.leopoo.translate.enums.Trans;
import com.leopoo.translate.annotation.TranslatorComponent;

import com.leopoo.translate.util.TranslationResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@TranslatorComponent(id = Trans.Youdao)
final public class YoudaoTranslator extends AbstractOnlineTranslator {

    public YoudaoTranslator() {
    }

    @Override
    protected String getResponse(String query, LANG origin, LANG target) throws Exception {
        HttpParams params = new HttpPostParams().put("type", origin.getYoudao() + "2" + target.getYoudao())
                .put("i", query).put("doctype", "json").put("xmlVersion", "1.8").put("keyfrom", "fanyi.web")
                .put("ue", "UTF-8").put("action", "FY_BY_CLICKBUTTON").put("typoResult", "true");

        return params.send2String(Trans.Youdao.getUrl());
    }

    @Override
    protected TranslationResult parse(String jsonString) {
        TranslationResult result = new TranslationResult();
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray segments = jsonObject.getJSONArray("translateResult");

        return result;
    }
}
