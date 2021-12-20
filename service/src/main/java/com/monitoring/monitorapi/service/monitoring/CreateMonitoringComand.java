package com.monitoring.monitorapi.service.monitoring;

import com.monitoring.monitorapi.infrastructure.MonitoringData;
import com.monitoring.monitorapi.service.command.Command;
import com.monitoring.monitorapi.service.command.context.Context;
import lombok.Builder;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Builder
public class CreateMonitoringComand implements Command<UUID> {
    private Double temperature;

    @Override
    public Optional<UUID> execute(Context ctx) throws Throwable {
        var monitoringService = ctx.getBcAccountService();
        if (temperature != null) {
            var monitoringData = MonitoringData.builder()
                    .id(UUID.randomUUID())
                    .temperature(temperature)
                    .date(new Date().toString())
                    .build();
            var monitoringId = monitoringService.save(monitoringData).getId();
            return Optional.of(monitoringId);
        }
        return Optional.empty();
    }
}
