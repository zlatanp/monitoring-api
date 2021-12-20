package com.monitoring.monitorapi.service.command.promise;

import com.monitoring.monitorapi.service.command.Command;
import com.monitoring.monitorapi.service.command.context.Context;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class AbstractExecutionPromise<RESULT, PREV_RESULT>
    implements ExecutionPromise<RESULT> {
  protected final ExecutionPromise<PREV_RESULT> previousExecutorPromise;
  protected final Context context;

  public AbstractExecutionPromise(final Context context) {
    this(null, context);
  }

  protected Optional<PREV_RESULT> getExecutionPromiseResult() {
    return previousExecutorPromise == null ? Optional.empty() : previousExecutorPromise.execute();
  }

  @Override
  public <NEXT_RESULT> ExecutionPromise<NEXT_RESULT> map(
      final Function<RESULT, NEXT_RESULT> mapCallback) {
    return new MapPromise<>(mapCallback, this, context);
  }

  @Override
  public <NEXT_RESULT> ExecutionPromise<NEXT_RESULT> executeCommandCallback(
      final Function<Optional<RESULT>, Command<NEXT_RESULT>> callback) {
    return new FunctionPromise<>(callback, this, context);
  }

  @Override
  public <NEXT_RESULT> ExecutionPromise<NEXT_RESULT> executeCommand(
      final Command<NEXT_RESULT> command) {
    return new CommandPromise<>(command, this, context);
  }
}
