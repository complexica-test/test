package com.complexica.test.controller;

import com.complexica.test.model.NameEntity;
import com.complexica.test.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NameService nameService;

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
        final List<NameEntity> names = nameService.findAllNames();
        if (CollectionUtils.isEmpty(names)) {
            return ResponseEntity.ok("No names found");
        }
        return ResponseEntity.ok(names.parallelStream().map(n -> n.getName()).collect(Collectors.joining(" and ")));
    }


}
