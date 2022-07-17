package com.nongsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Controller
public class AgricultureController {

    @GetMapping("/agriculture/calculator")
    public String calculator() {
        return "agriculture/calculator";
    }

    @GetMapping("/agriculture/tech")
    public String tech() {
        return "agriculture/tech";
    }

    @ResponseBody
    @GetMapping("/agriculture/tech/callback")
    public String techCallback(HttpServletRequest request) {
        String queryString = request.getQueryString();
        String openapi_url = "http://api.nongsaro.go.kr/service/" + queryString;

        StringBuffer sbf = new StringBuffer();
        try {
            System.out.println(openapi_url);
            URL url = new URL(openapi_url);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) sbf.append(inputLine);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sbf.toString();
    }
}
