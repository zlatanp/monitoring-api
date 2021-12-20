package com.monitoring.monitorapi.service.command.promise;


import com.monitoring.monitorapi.service.command.Command;

public interface Promise {
  <NEXT_RESULT> ExecutionPromise<NEXT_RESULT> executeCommand(Command<NEXT_RESULT> command);
}
