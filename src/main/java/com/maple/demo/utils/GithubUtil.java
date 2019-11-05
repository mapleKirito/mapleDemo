package com.maple.demo.utils;

import com.alibaba.fastjson.JSON;
import com.maple.demo.entity.AccessTokenEntity;
import com.maple.demo.entity.GithubUserEntity;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubUtil {
    public String getAccessToken(AccessTokenEntity accessTokenEntity){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenEntity));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .addHeader("Accept","application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str= response.body().string();
            String accessToken=JSON.parseObject(str,AccessTokenEntity.class).getAccess_token();
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public GithubUserEntity getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .addHeader("Authorization","token "+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String str= response.body().string();
            GithubUserEntity githubUserEntity = JSON.parseObject(str, GithubUserEntity.class);
            return githubUserEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
