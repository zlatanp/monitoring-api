package com.monitoring.monitorapi.service.command.promise;



import com.monitoring.monitorapi.service.command.Command;

import java.util.Optional;
import java.util.function.Function;

public interface ExecutionPromise<RESULT> extends Promise {
  <NEXT_RESULT> ExecutionPromise<NEXT_RESULT> map(Function<RESULT, NEXT_RESULT> mapCallback);

  <NEXT_RESULT> ExecutionPromise<NEXT_RESULT> executeCommandCallback(
      Function<Optional<RESULT>, Command<NEXT_RESULT>> callback);

  Optional<RESULT> execute();
}
