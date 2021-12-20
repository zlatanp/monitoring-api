package com.monitoring.monitorapi.service.command.promise;


import com.monitoring.monitorapi.service.command.Command;
import com.monitoring.monitorapi.service.command.context.Context;
import com.monitoring.monitorapi.service.command.exception.CommandException;

import java.util.Optional;

public class CommandPromise<RESULT, PREV_RESULT>
    extends AbstractExecutionPromise<RESULT, PREV_RESULT> {
  private final Command<RESULT> command;

  public CommandPromise(final Command<RESULT> command, final Context context) {
    super(context);
    this.command = command;
  }

  public CommandPromise(
      final Command<RESULT> command,
      final ExecutionPromise<PREV_RESULT> previousExecutorPromise,
      final Context context) {
    super(previousExecutorPromise, context);
    this.command = command;
  }

  @Override
  public Optional<RESULT> execute() {
    try {
      return command.execute(context);
    } catch (final Throwable e) {
      throw new CommandException(command, e);
    }
  }
}
