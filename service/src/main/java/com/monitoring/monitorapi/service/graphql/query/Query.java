package com.monitoring.monitorapi.service.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.monitoring.monitorapi.infrastructure.MonitoringRepository;
import com.monitoring.monitorapi.service.graphql.response.MonitoringResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {

    private MonitoringRepository monitoringRepository;

    public List<MonitoringResponse> getAllMonitoringData(){
        var monitoringData = monitoringRepository.getAllMonitoringData();
        return monitoringData.stream().map(MonitoringResponse::new).collect(Collectors.toList());
    }
}
