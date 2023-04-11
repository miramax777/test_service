package com.test.example.demo.service;

import com.test.example.demo.client.MonitoringService;
import com.test.example.demo.client.ServiceAClient;
import com.test.example.demo.client.ServiceBClient;
import com.test.example.demo.client.ServiceCClient;
import com.test.example.demo.client.ServiceDClient;
import com.test.example.demo.model.Session;
import com.test.example.demo.model.SessionProduct;
import com.test.example.demo.model.SessionType;
import com.test.example.demo.model.SkinIssues;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SessionService {

    private final ServiceAClient serviceAClient;
    private final ServiceBClient serviceBClient;
    private final ServiceCClient serviceCClient;
    private final ServiceDClient serviceDClient;
    private final MonitoringService monitoringService;

    public SessionService(
            final ServiceAClient serviceAClient,
            final ServiceBClient serviceBClient,
            final ServiceCClient serviceCClient,
            final ServiceDClient serviceDClient,
            final MonitoringService monitoringService
    ) {
        this.serviceAClient = serviceAClient;
        this.serviceBClient = serviceBClient;
        this.serviceCClient = serviceCClient;
        this.serviceDClient = serviceDClient;
        this.monitoringService = monitoringService;
    }

    public synchronized Session constructNewSession() {

        final Long sessionId = serviceAClient.getSessionId();

        final SessionType sessionType = serviceBClient.sessionTypeById(sessionId);
        final SkinIssues skinIssues = serviceCClient.getSessionIssuesById(sessionId);
        final Set<SessionProduct> sessionProduct = Set.of(serviceDClient.getSessionProductById(sessionId));

        final var session =  new Session(
                sessionId,
                sessionType,
                100L,
                10,
                15,
                Set.of(skinIssues),
                sessionProduct,
                true,
                true
        );

        monitoringService.sendSessionMetric(session);

        return session;
    }


}
