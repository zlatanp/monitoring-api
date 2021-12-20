package com.monitoring.monitorapi.service.command.result;

import com.monitoring.monitorapi.service.command.Command;
import com.monitoring.monitorapi.service.command.CommandManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class EmptyCommandResult<RESULT> implements CommandResult<RESULT> {
  private final CommandManager commandManager;

  @Override
  public Optional<RESULT> getResult() {
    return Optional.empty();
  }

  @Override
  public <NEW_RESULT> Optional<NEW_RESULT> map(final Function<RESULT, NEW_RESULT> mapper) {
    return Optional.empty();
  }

  @Override
  public <NEXT_RESULT> CommandResult<NEXT_RESULT> thenExecute(
      Function<Optional<RESULT>, Command<NEXT_RESULT>> callback) {
    var nextCommand = callback.apply(getResult());
    return commandManager.execute(nextCommand);
  }

  @Override
  public <NEXT_RESULT> CommandResult<NEXT_RESULT> thenExecuteCommand(Command<NEXT_RESULT> command) {
    return commandManager.execute(command);
  }
}
