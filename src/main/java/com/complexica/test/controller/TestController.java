package com.complexica.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @RequestMapping("/")
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("homepage.html");
        return mav;
    }

    @RequestMapping("/post-list")
    @ResponseBody
    public ResponseEntity<?> getPostList() {
        final String url = "https://www.complexica.com/_hcms/postlisting?blogId=3598871758&maxLinks=5&listingType=recent&orderByViews=false&hs-expires=1604537484&hs-version=2&hs-signature=AJ2IBuFZ7xRmwgmRBgo0EfWM4qo0pSAzIA&currentUrl=https%3A%2F%2Fwww.complexica.com%2F";
        final ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @RequestMapping("/welcome-msg")
    @ResponseBody
    public ResponseEntity<?> getWelcomeMsg(){
        final String sql = "select name from test";
        final List<String> queryResult = namedParameterJdbcTemplate.queryForList(sql, new HashMap<>(0), String.class);
        if ( queryResult.size() == 0) {
            return ResponseEntity.ok("No welcome message");
        }
        return ResponseEntity.ok(String.join(", ", queryResult));
    }


}
