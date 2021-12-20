package com.monitoring.monitorapi.service.command.promise;


import com.monitoring.monitorapi.service.command.Command;
import com.monitoring.monitorapi.service.command.context.Context;
import com.monitoring.monitorapi.service.command.exception.CommandException;

import java.util.Optional;
import java.util.function.Function;

public class FunctionPromise<RESULT, PREV_RESULT>
        extends AbstractExecutionPromise<RESULT, PREV_RESULT> {
    private final Function<Optional<PREV_RESULT>, Command<RESULT>> callback;

    public FunctionPromise(
            final Function<Optional<PREV_RESULT>, Command<RESULT>> callback,
            final ExecutionPromise<PREV_RESULT> previousExecutorPromise,
            final Context context) {
        super(previousExecutorPromise, context);
        this.callback = callback;
    }

    @Override
    public Optional<RESULT> execute() {
        var previousResult = getExecutionPromiseResult();
        var nextCommand = callback.apply(previousResult);
        try {
            return nextCommand.execute(context);
        } catch (final Throwable e) {
            throw new CommandException(nextCommand, e);
        }
    }
}
