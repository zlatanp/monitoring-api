package com.monitoring.monitorapi.service.graphql.response;

import com.monitoring.monitorapi.infrastructure.MonitoringData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MonitoringResponse {
    private UUID id;
    private Double temperature;
    private String date;

    public MonitoringResponse(MonitoringData monitoringData) {
        this.id = monitoringData.getId();
        this.temperature = monitoringData.getTemperature();
        this.date = monitoringData.getDate();
    }

}
