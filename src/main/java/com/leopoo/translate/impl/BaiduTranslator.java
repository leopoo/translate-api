package com.leopoo.translate.impl;

import com.leopoo.http.HttpParams;
import com.leopoo.http.HttpPostParams;
import com.leopoo.translate.AbstractOnlineTranslator;
import com.leopoo.translate.LANG;
import com.leopoo.translate.Trans;
import com.leopoo.translate.annotation.TranslatorComponent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@TranslatorComponent(id = Trans.Baidu)
final public class BaiduTranslator extends AbstractOnlineTranslator {

    public BaiduTranslator() {
        langMap.put(LANG.EN, "en");
        langMap.put(LANG.ZH, "zh");
    }

    @Override
    public String getResponse(String query, LANG origin, LANG target) throws Exception {

        HttpParams params = new HttpPostParams().put("from", langMap.get(origin)).put("to", langMap.get(target))
                .put("query", query).put("transtype", "translang").put("simple_means_flag", "3");

        return params.send2String("http://fanyi.baidu.com/v2transapi");
    }

    @Override
    protected String parseString(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray segments = jsonObject.getJSONObject("trans_result").getJSONArray("data");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < segments.size(); i++) {
            result.append(i == 0 ? "" : "\n");
            result.append(segments.getJSONObject(i).getString("dst"));
        }
        return new String(result);
    }
}
