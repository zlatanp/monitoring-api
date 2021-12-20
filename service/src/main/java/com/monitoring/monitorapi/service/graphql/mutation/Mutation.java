package com.monitoring.monitorapi.service.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.monitoring.monitorapi.infrastructure.MonitoringData;
import com.monitoring.monitorapi.infrastructure.MonitoringRepository;
import com.monitoring.monitorapi.service.jobs.JobManager;
import com.monitoring.monitorapi.service.jobs.SubmitNameJob;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    private MonitoringRepository monitoringRepository;
    private final JobManager jobManager;

    public String addMonitoringData(Double temperature) {
        String id = null;
        if (temperature != null) {
            var monitoringData = MonitoringData.builder()
                    .id(UUID.randomUUID())
                    .temperature(temperature)
                    .date(new Date().toString())
                    .build();
            id = monitoringRepository.save(monitoringData).getId().toString();
        }
        return id;
    }

    public String startJob(String name){
        jobManager.scheduleJob(new SubmitNameJob(name));
        return "JOB_STARTED";
    }

    public String stopJob(String name){
        jobManager.stopJob(name);
        return "JOB_STOPPED";
    }
}
