package com.monitoring.monitorapi.service.command.result;



import com.monitoring.monitorapi.service.command.Command;

import java.util.Optional;
import java.util.function.Function;

public interface CommandResult<RESULT> {
  Optional<RESULT> getResult();

  <NEW_RESULT> Optional<NEW_RESULT> map(Function<RESULT, NEW_RESULT> mapper);

  <NEXT_RESULT> CommandResult<NEXT_RESULT> thenExecute(
      Function<Optional<RESULT>, Command<NEXT_RESULT>> callback);

  <NEXT_RESULT> CommandResult<? extends NEXT_RESULT> thenExecuteCommand(
      Command<NEXT_RESULT> command);
}
