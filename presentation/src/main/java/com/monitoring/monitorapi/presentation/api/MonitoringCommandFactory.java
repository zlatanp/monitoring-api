package com.monitoring.monitorapi.presentation.api;

import com.monitoring.monitorapi.service.monitoring.CreateMonitoringComand;
import com.monitoring.monitorapi.service.monitoring.GetMonitoringCommand;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonitoringCommandFactory {

    public GetMonitoringCommand getMonitoringCommand() {
        return GetMonitoringCommand.builder().build();
    }

    public static CreateMonitoringComand createBusinessChatCommand(String temperature) {
        return CreateMonitoringComand.builder()
                .temperature(Double.valueOf(temperature))
                .build();
    }
}
