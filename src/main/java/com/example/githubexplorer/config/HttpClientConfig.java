package com.example.githubexplorer.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig implements DisposableBean {

    private CloseableHttpClient httpClient;

    @Bean
    public CloseableHttpClient httpClient() {
        this.httpClient = HttpClients.createDefault();
        return this.httpClient;
    }

    @Override
    public void destroy() throws Exception {
        if (httpClient != null) {
            httpClient.close();
        }
    }
}
