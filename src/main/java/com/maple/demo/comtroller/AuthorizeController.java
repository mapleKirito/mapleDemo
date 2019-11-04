package com.maple.demo.comtroller;

import com.maple.demo.entity.AccessTokenEntity;
import com.maple.demo.entity.GithubUserEntity;
import com.maple.demo.utils.GithubUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubUtil githubUtil;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setClient_id("91069c8f973100bbe46a");
        accessTokenEntity.setCode(code);
        accessTokenEntity.setState(state);
        accessTokenEntity.setRedirect_uri("http://localhost:8080/callback");
        accessTokenEntity.setClient_secret("e3b1d565788fe83437ae0cd0ade6a5b9b6454282");
        String accessToken = githubUtil.getAccessToken(accessTokenEntity);
        GithubUserEntity user = githubUtil.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
