package com.leopoo.translate.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.leopoo.http.HttpParams;
import com.leopoo.http.HttpPostParams;
import com.leopoo.translate.AbstractOnlineTranslator;
import com.leopoo.translate.enums.LANG;
import com.leopoo.translate.util.Proxy;
import com.leopoo.translate.enums.Trans;
import com.leopoo.translate.annotation.TranslatorComponent;

import com.leopoo.translate.util.TranslationResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpHost;

@TranslatorComponent(id = Trans.Google)
public final class GoogleTranslator extends AbstractOnlineTranslator implements Proxy {

    private final String scriptPath = "tk.js";

    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    private String script = "";

    private HttpHost proxy;

    public GoogleTranslator() {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(scriptPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                script += line;
                line = reader.readLine();
            }
            engine.eval(script);
        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        }
    }

    public GoogleTranslator(String host, int port) {
        this(host, port, null, null);
    }

    public GoogleTranslator(String host, int port, String user, String password) {
        this();
        this.proxy = new HttpHost(host, port);
    }

    @Override
    protected String getResponse(String query, LANG origin, LANG target) throws Exception {
        // HttpHost proxy = new HttpHost("127.0.0.1", 1080);

        HttpParams params = new HttpPostParams(proxy); // 统一采用post，若字符长度小于999用get也可以的
        String tk = tk(query);
        params.put("client", "t").put("sl", "auto").put("tl", target.getGoogle()).put("hl", origin.getGoogle())
                .put("dt", "at").put("dt", "bd").put("dt", "ex").put("dt", "ld").put("dt", "md").put("dt", "qca")
                .put("dt", "rw").put("dt", "rm").put("dt", "ss").put("dt", "t").put("ie", "UTF-8").put("oe", "UTF-8")
                .put("source", "btn").put("ssel", "3").put("tsel", "0").put("kc", "0").put("tk", tk).put("q", query);

        return params.send2String(Trans.Google.getUrl());
    }

    @Override
    protected TranslationResult parse(String jsonString) {
        TranslationResult result = new TranslationResult();
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONArray segments = jsonArray.getJSONArray(0);
        List<String> dst = new ArrayList<>();
        for (int i = 0, len = segments.size(); i < len; i++) {
            dst.add(segments.getJSONArray(i).getString(0));
        }
        result.setDst(dst);
        return result;

    }

    private String tk(String val) throws Exception {
        Invocable inv = (Invocable) engine;
        return (String) inv.invokeFunction("tk", val);
    }

    @Override
    public void setProxy(HttpHost proxy) {
        this.proxy = proxy;
    }
}