package com.maple.demo.comtroller;

import com.maple.demo.entity.AccessTokenEntity;
import com.maple.demo.entity.GithubUserEntity;
import com.maple.demo.utils.GithubUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubUtil githubUtil;

    @Value("${Github.client.id}")
    private String clientId;
    @Value("${Github.client.secret}")
    private String clientSecret;
    @Value("${Github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){



        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setClient_id(clientId);
        accessTokenEntity.setCode(code);
        accessTokenEntity.setState(state);
        accessTokenEntity.setRedirect_uri(redirectUri);
        accessTokenEntity.setClient_secret(clientSecret);
        String accessToken = githubUtil.getAccessToken(accessTokenEntity);
        GithubUserEntity user = githubUtil.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
