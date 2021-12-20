package com.monitoring.monitorapi.presentation.api;

import com.monitoring.monitorapi.presentation.api.mapper.MonitoringResponseMapper;
import com.monitoring.monitorapi.presentation.api.response.GetMonitoringResponse;
import com.monitoring.monitorapi.service.command.CommandManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monitoring-api")
public class MonitoringApiController {
    private final CommandManager commandManager;

    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetMonitoringResponse> getData() {
        var getMonitoringCommand = MonitoringCommandFactory.getMonitoringCommand();

        return commandManager
                .createPromise()
                .executeCommand(getMonitoringCommand)
                .map(MonitoringResponseMapper::toResponse).execute()
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Monitoring Data not found!"));
    }

    @PostMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public UUID postData(@RequestParam(required = true) String temperature) {
        var createBusinessChatCommand = MonitoringCommandFactory.createBusinessChatCommand(temperature);

        return commandManager
                .createPromise()
                .executeCommand(createBusinessChatCommand)
                .map(MonitoringResponseMapper::toResponse).execute()
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Monitoring Data can not be created!"));
    }
}
