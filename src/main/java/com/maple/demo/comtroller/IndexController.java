package com.maple.demo.comtroller;

import com.maple.demo.mapper.UserMapper;
import com.maple.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        HttpSession session){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                User user = userMapper.findByToken(token);
                session.setAttribute("user",user);
            }

        }
        return "index";
    }
}
