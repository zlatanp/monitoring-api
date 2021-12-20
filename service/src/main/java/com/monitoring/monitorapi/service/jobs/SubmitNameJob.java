package com.monitoring.monitorapi.service.jobs;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class SubmitNameJob extends Job {

    public SubmitNameJob(final String id) {
        super(id);
    }

    @Override
    public boolean isReady() {
        try {
            Random random = new Random();
            var bool = random.nextBoolean();
            log.info("Job Is Ready: " + bool);
            return bool;
        } catch (final Exception e) {
            log.info("Failed to check if proces is submittable {}", id, e);
            return false;
        }
    }

    @Override
    public void execute() throws Exception {
        log.info("Submit result for name: {}", id);
    }
}
