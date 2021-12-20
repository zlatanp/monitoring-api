package com.monitoring.monitorapi.service.command.promise;



import com.monitoring.monitorapi.service.command.context.Context;

import java.util.Optional;
import java.util.function.Function;

public class MapPromise<NEW_RESULT, RESULT> extends AbstractExecutionPromise<NEW_RESULT, RESULT> {
  private final Function<RESULT, NEW_RESULT> mapCallback;

  public MapPromise(
      final Function<RESULT, NEW_RESULT> mapCallback,
      final ExecutionPromise<RESULT> previousExecutorPromise,
      final Context context) {
    super(previousExecutorPromise, context);

    this.mapCallback = mapCallback;
  }

  @Override
  public Optional<NEW_RESULT> execute() {
    var previousResult = getExecutionPromiseResult();
    return previousResult.map(mapCallback);
  }
}
