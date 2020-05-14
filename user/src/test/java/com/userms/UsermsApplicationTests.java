package com.userms;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
class UsermsApplicationTests {

	@Autowired
    private TestRestTemplate testRestTemplate;
	
	@Test
	public void testpost() {
	       HttpHeaders headers = new HttpHeaders();
	        headers.add("token", "11111");

	        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
	        param.add("username","abcdef");
	        param.add("password","1111111");
	        param.add("role","1");

	        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(param, headers);

	        ResponseEntity<String> resultEntity = testRestTemplate.postForEntity("/login", entity, String.class);
	        System.err.println("reuslt:" + resultEntity.getBody());
	        System.err.println("headers:" + resultEntity.getHeaders());
	}

	
	@Test
	public void testsigninAsBuyer() {
        String jsonStr = "{\"usernam\":\"zhangsan\",\"password\":\"111111\",\"email\":\"Joshua Bloch@111\",\"Mobile\":11123339.0}";
        HttpHeaders headers = new HttpHeaders();

        //设置contentType
        headers.setContentType(MediaType.valueOf("application/json;UTF-8"));

        HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);
        String result = testRestTemplate.postForObject("/signinbuyer", entity, String.class);
        System.err.println(result);
	}

	@Test
	public void testsigninAsSeller() {
        String jsonStr = "{\"usernam\":\"zhangsan\",\"password\":\"111111\",\"companyName\":\"Joshua Bloch@111\",\"gstin\":\"11\",\"briefAboutCompany\":\"Companay\",\"postalAddress\":\"11123339\",\"website\":\"www.11123339\",\"email\":\"11123339\"}";
        HttpHeaders headers = new HttpHeaders();

        //设置contentType
        headers.setContentType(MediaType.valueOf("application/json;UTF-8"));

        HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);
        String result = testRestTemplate.postForObject("/signinseller", entity, String.class);
        System.err.println(result);
	}
	
}
