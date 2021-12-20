package com.monitoring.monitorapi.service.monitoring;

import com.monitoring.monitorapi.infrastructure.MonitoringData;
import com.monitoring.monitorapi.service.command.Command;
import com.monitoring.monitorapi.service.command.context.Context;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

@Builder
public class GetMonitoringCommand implements Command<List<MonitoringData>> {

    @Override
    public Optional<List<MonitoringData>> execute(final Context ctx) throws Throwable {
        var monitoringService = ctx.getBcAccountService();
        var monitoringData = monitoringService.getAllMonitoringData();

        return monitoringData.isEmpty() ? Optional.empty() : Optional.of(monitoringData);
    }
}
