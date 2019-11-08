package com.maple.demo.comtroller;

import com.maple.demo.entity.AccessTokenEntity;
import com.maple.demo.entity.GithubUserEntity;
import com.maple.demo.mapper.UserMapper;
import com.maple.demo.model.User;
import com.maple.demo.utils.GithubUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

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

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){



        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setClient_id(clientId);
        accessTokenEntity.setCode(code);
        accessTokenEntity.setState(state);
        accessTokenEntity.setRedirect_uri(redirectUri);
        accessTokenEntity.setClient_secret(clientSecret);
        String accessToken = githubUtil.getAccessToken(accessTokenEntity);
        GithubUserEntity githubUser = githubUtil.getUser(accessToken);
        if(githubUser!=null){
            //获取登陆信息
            //写入数据库
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.Insert(user);
            //写入cookie
            response.addCookie(new Cookie("token",token));

            return "redirect:/";

        }else{
            //登陆失败
            return "redirect:/";
        }


    }
}
