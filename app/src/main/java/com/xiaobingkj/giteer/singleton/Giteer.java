package com.xiaobingkj.giteer.singleton;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.xiaobingkj.giteer.entry.OAuthEntry;
import com.xiaobingkj.giteer.entry.UserEntry;

public class Giteer {
    private static Giteer instance;

    private Giteer() {

    }

    public static synchronized Giteer getInstance() {
        if (instance == null) {
            instance = new Giteer();
        }
        return instance;
    }

    public OAuthEntry getToken() {
        String json = MMKV.defaultMMKV().getString("token", "");
        if (json.isEmpty()) {
            return new OAuthEntry();
        }
        Gson gson = new Gson();
        OAuthEntry entry = gson.fromJson(json, OAuthEntry.class);
        return entry;
    }

    public void setToken(String token) {
        MMKV.defaultMMKV().putString("token", token);
    }

    public UserEntry getUser() {
        String json = MMKV.defaultMMKV().getString("user", "");
        if (json.isEmpty()) {
            return new UserEntry();
        }
        Gson gson = new Gson();
        UserEntry entry = gson.fromJson(json, UserEntry.class);
        return entry;
    }

    public void setUser(String json) {
        MMKV.defaultMMKV().putString("user", json);
    }

}
