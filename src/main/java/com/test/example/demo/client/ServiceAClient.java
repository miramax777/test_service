package com.test.example.demo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.example.demo.model.SessionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Service
public class ServiceAClient {

    private final RestTemplate restTemplate;

    public ServiceAClient(
            final RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
    }

    public Long getSessionId() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", APPLICATION_JSON_VALUE);
        httpHeaders.add("Accept", APPLICATION_JSON_VALUE);

        final HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Long> response = null;

        try {
            // Long response time up to 3 seconds
            response = restTemplate.exchange("http://api.flix-tech.test", GET, requestEntity, Long.class);
        } catch (final RuntimeException exception) {
            log.error("Error getting session id ");
        }

        if (response != null) {
            log.info("Response: {}", response.getBody());
            return response.getBody();
        }

        return null;
    }

}
