package com.farmer.Order.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @GetMapping
    public String home(){
        return "Welcome To Water Order Application.";
    }

    @GetMapping(path = "secured")
    public String login(){
//        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient("google", "your-username");
//        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        return "Successfully logged in.";
    }
}