package com.monitoring.monitorapi.service.command.promise;

import com.monitoring.monitorapi.service.command.Command;
import com.monitoring.monitorapi.service.command.context.Context;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmptyPromise implements Promise {
  private final Context context;

  @Override
  public <NEXT_RESULT> ExecutionPromise<NEXT_RESULT> executeCommand(
      Command<NEXT_RESULT> command) {
    return new CommandPromise<>(command, context);
  }
}
