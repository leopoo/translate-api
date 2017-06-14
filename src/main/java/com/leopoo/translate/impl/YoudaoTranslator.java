package com.leopoo.translate.impl;

import com.leopoo.http.HttpParams;
import com.leopoo.http.HttpPostParams;
import com.leopoo.translate.AbstractOnlineTranslator;
import com.leopoo.translate.LANG;
import com.leopoo.translate.Trans;
import com.leopoo.translate.annotation.TranslatorComponent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@TranslatorComponent(id = Trans.Youdao)
final public class YoudaoTranslator extends AbstractOnlineTranslator {

    public YoudaoTranslator() {
        langMap.put(LANG.EN, "EN");
        langMap.put(LANG.ZH, "ZH_CN");
    }

    @Override
    protected String getResponse(String query, LANG origin, LANG target) throws Exception {
        HttpParams params = new HttpPostParams().put("type", langMap.get(origin) + "2" + langMap.get(target))
                .put("i", query).put("doctype", "json").put("xmlVersion", "1.8").put("keyfrom", "fanyi.web")
                .put("ue", "UTF-8").put("action", "FY_BY_CLICKBUTTON").put("typoResult", "true");

        return params.send2String(
                "http://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule&smartresult=ugc&sessionFrom=https://www.baidu.com/link");
    }

    @Override
    protected String parseString(String jsonString) {
        StringBuilder result = new StringBuilder();
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray segments = jsonObject.getJSONArray("translateResult");

        for (int i = 0; i < segments.size(); i++) {
            result.append(i == 0 ? "" : "\r\n");
            JSONArray parts = jsonObject.getJSONArray("translateResult").getJSONArray(i);
            for (int j = 0; j < parts.size(); j++) {
                result.append(parts.getJSONObject(j).getString("tgt"));
            }
        }

        return new String(result);
    }
}
