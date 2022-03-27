package com.far.recrutement.controllers;/*
 * package com.far.recrutement.controllers;
 * 
 * import java.net.URI; import java.net.URISyntaxException;
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import com.far.recrutement.captcha.RecaptchaResponse;
 * 
 * @Controller public class CaptchaLoading {
 * 
 * @Autowired private RestTemplate restTemplate;
 * 
 * 
 * @GetMapping(value = "/captcha") public String captcha() {
 * 
 * return "captchaIndex"; }
 * 
 * @PostMapping(value = "/captchaValidate") public String captchaValidate(Model
 * model,@RequestParam(name = "g-recaptcha-response") String
 * captchaResponse,HttpServletRequest request) throws URISyntaxException {
 * String url = "https://www.google.com/recaptcha/api/siteverify"; String params
 * =
 * "?secret=6Ld21-EUAAAAAC_4FcBRjtfAy8p4qHj3Ud824n8j&response="+captchaResponse;
 * 
 * RecaptchaResponse recaptchaResponse =
 * restTemplate.exchange(url+params,HttpMethod.POST,null,RecaptchaResponse.class
 * ).getBody(); if(recaptchaResponse.isSuccess()) { URI uri = new
 * URI(request.getHeader("referer")); String a = uri.getPath(); if
 * (uri.getQuery() != null ) { a += "?"+uri.getQuery(); } return "redirect:"+a;
 * }else { return "redirect:/captcha"; } }
 * 
 * }
 */