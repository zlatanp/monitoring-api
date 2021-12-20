package com.monitoring.monitorapi.presentation.api.mapper;

import com.monitoring.monitorapi.infrastructure.MonitoringData;
import com.monitoring.monitorapi.presentation.api.response.GetMonitoringResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class MonitoringResponseMapper {

    public static UUID toResponse(final UUID id) {
        return id;
    }

    public static GetMonitoringResponse toResponse(final MonitoringData monitoringData) {
        return GetMonitoringResponse.builder()
                .id(monitoringData.getId())
                .temperature(monitoringData.getTemperature())
                .date(monitoringData.getDate())
                .build();
    }

    public static List<GetMonitoringResponse> toResponse(final List<MonitoringData> monitoringData) {
        return monitoringData.stream().map(md -> {
            return GetMonitoringResponse.builder()
                    .id(md.getId())
                    .temperature(md.getTemperature())
                    .date(md.getDate())
                    .build();
        }).collect(Collectors.toList());
    }
}
