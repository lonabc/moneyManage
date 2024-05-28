package com.example.yinfuxi.configer;


import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigertion {

    @Bean
    public RestTemplate restTemplate()//配置restTemplate对httpclient的简便化
    {
        return new RestTemplate();
    }
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory()
    {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory=new HttpComponentsClientHttpRequestFactory();
//        //httpClient的创建器
//        clientHttpRequestFactory.setHttpClient(httpClient);
//        //连接超时时间/毫秒（连接上服务器（握手成功）的时间，超出抛出connect timeout）
//        clientHttpRequestFactory.setConnectTimeout(5*1000);
//        //数据读取时间（socketTimeout)/毫秒（服务器返回数据的时间，超过抛出read timeout）
//        clientHttpRequestFactory.setReadTimeout(10*1000);
//        //连接池获取请求连接的超时时间，不宜过长，必须设置/毫秒
//        clientHttpRequestFactory.setConnectionRequestTimeout(10*1000);

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//单位为ms
        factory.setConnectTimeout(5000);//单位为ms

        return factory;

    }
}
