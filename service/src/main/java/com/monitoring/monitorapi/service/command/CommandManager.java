package com.monitoring.monitorapi.service.command;


import com.monitoring.monitorapi.service.command.promise.Promise;
import com.monitoring.monitorapi.service.command.result.CommandResult;

public interface CommandManager {
  <RESULT> CommandResult<RESULT> execute(Command<RESULT> cmd);

  Promise createPromise();
}
