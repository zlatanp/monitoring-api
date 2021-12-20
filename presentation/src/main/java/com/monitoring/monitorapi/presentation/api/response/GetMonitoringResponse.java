package com.monitoring.monitorapi.presentation.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class GetMonitoringResponse {
    private UUID id;

    @JsonProperty("temperature")
    private Double temperature;

    @JsonProperty("date")
    private String date;
}
