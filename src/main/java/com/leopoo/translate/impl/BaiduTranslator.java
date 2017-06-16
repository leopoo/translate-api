package com.leopoo.translate.impl;

import com.leopoo.http.HttpParams;
import com.leopoo.http.HttpPostParams;
import com.leopoo.translate.AbstractOnlineTranslator;
import com.leopoo.translate.enums.LANG;
import com.leopoo.translate.enums.Trans;
import com.leopoo.translate.util.TranslationResult;
import com.leopoo.translate.annotation.TranslatorComponent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@TranslatorComponent(id = Trans.Baidu)
final public class BaiduTranslator extends AbstractOnlineTranslator {

    public BaiduTranslator() {
    }

    @Override
    public String getResponse(String query, LANG origin, LANG target) throws Exception {

        HttpParams params = new HttpPostParams().put("from", origin.getBaidu()).put("to", target.getBaidu())
                .put("query", query).put("transtype", "translang").put("simple_means_flag", "3");

        return params.send2String(Trans.Baidu.getUrl());
    }

    @Override
    protected TranslationResult parse(String jsonString) {
        TranslationResult result = new TranslationResult();
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray segments = jsonObject.getJSONObject("trans_result").getJSONArray("data");
        List<String> dst = new ArrayList<>();
        for (int i = 0, len = segments.size(); i < len; i++) {
            JSONObject json = segments.getJSONObject(i);
            dst.add(json.getString("dst"));
        }
        result.setDst(dst);
        return result;
    }
}
