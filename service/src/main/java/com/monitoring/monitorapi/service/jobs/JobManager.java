package com.monitoring.monitorapi.service.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Component
public class JobManager {

    @Value("${job.max.attempts:10}")
    private int MAX_ATTEMPTS;

    @Value("${job.thread.number:10}")
    private int JOBS_THREAD_CORE;

    private final ScheduledExecutorService jobExecutorService = Executors.newScheduledThreadPool(JOBS_THREAD_CORE);
    private final ConcurrentMap<String, Job> jobs = new ConcurrentHashMap<>();
    private Map<String, ScheduledFuture> scheduledFutures = new HashMap<>();

    public void scheduleJob(final Job job) {
        if (jobs.putIfAbsent(job.getId(), job) == null) {
            scheduleJobWithAttempts(job);
        }
    }

    public void stopJob(final String jobId) {
        scheduledFutures.get(jobId).cancel(true);
        jobs.remove(jobId);
    }

    private void scheduleJobWithAttempts(final Job job) {
        scheduleJobWithAttempts(job, 0);
    }

    private void scheduleJobWithAttempts(final Job job, final int attempt) {
        if (attempt >= MAX_ATTEMPTS) {
            jobs.remove(job.getId());
            return;
        }

        var delay = Math.round(Math.E * attempt * 10);
        var futureJob = jobExecutorService.schedule(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (job.isReady()) {
                                                try {
                                                    job.execute();
                                                } catch (final Exception e) {
                                                    log.info("Failed to execute job {}", job.getId(), e);
                                                }
                                                jobs.remove(job.getId());
                                            } else {
                                                scheduleJobWithAttempts(job, attempt + 1);
                                            }
                                        }
                                    }
                , delay, TimeUnit.SECONDS);

        scheduledFutures.put(job.getId(),futureJob);
    }
}

