package com.far.recrutement.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Configuration
public class FaviconConfiguration {

 @Bean
 public SimpleUrlHandlerMapping faviconHandlerMapping() {
  SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
  mapping.setOrder(Integer.MIN_VALUE);
  return mapping;
 }

 @Bean
 protected ResourceHttpRequestHandler faviconRequestHandler() {
  ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
  requestHandler.setLocations(Arrays
   . < Resource > asList(new ClassPathResource("/")));
  return requestHandler;
 }

}