package com.monitoring.monitorapi.service.jobs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Job {
	protected final String id;

	public abstract boolean isReady();

	public abstract void execute() throws Exception;
}
