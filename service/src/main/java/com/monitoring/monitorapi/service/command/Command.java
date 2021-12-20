package com.monitoring.monitorapi.service.command;


import com.monitoring.monitorapi.service.command.context.Context;

import java.util.Optional;

public interface Command<RESULT> {
    Optional<RESULT> execute(Context ctx) throws Throwable;
}

