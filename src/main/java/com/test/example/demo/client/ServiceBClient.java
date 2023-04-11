package com.test.example.demo.client;

import com.test.example.demo.model.SessionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Slf4j
@Service
public class ServiceBClient {

    private final RestTemplate restTemplate;

    public ServiceBClient(
            final RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
    }

    public SessionType sessionTypeById(final Long sessionId) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", APPLICATION_JSON_VALUE);
        httpHeaders.add("Accept", APPLICATION_JSON_VALUE);

        final HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<SessionType> response = null;

        try {
            // Long response time up to 3 seconds
            response = restTemplate.exchange("http://api.flix-tech.test", GET, requestEntity, SessionType.class);
        } catch (final RuntimeException exception) {
            log.error("Error when trying getting session type.");
        }

        if (response != null) {
            log.info("Response: {}", response.getBody());
            return response.getBody();
        }

        return null;
    }

}
