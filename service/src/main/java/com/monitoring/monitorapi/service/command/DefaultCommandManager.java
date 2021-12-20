package com.monitoring.monitorapi.service.command;


import com.monitoring.monitorapi.service.command.context.Context;
import com.monitoring.monitorapi.service.command.context.DefaultContext;
import com.monitoring.monitorapi.service.command.exception.CommandException;
import com.monitoring.monitorapi.service.command.promise.EmptyPromise;
import com.monitoring.monitorapi.service.command.promise.Promise;
import com.monitoring.monitorapi.service.command.result.CommandResult;
import com.monitoring.monitorapi.service.command.result.DefaultCommandResult;
import com.monitoring.monitorapi.service.command.result.EmptyCommandResult;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandManager implements CommandManager {
    private final Context context;

    public DefaultCommandManager(final ApplicationContext applicationContext) {
        this.context = new DefaultContext(applicationContext, this);
    }

    @Override
    public <RESULT> CommandResult<RESULT> execute(final Command<RESULT> cmd) {
        try {
            var commandResult = cmd.execute(context);

            return commandResult
                    .map(this::createDefaultCommandResult)
                    .orElseGet(this::createEmptyCommandResult);
        } catch (final Throwable e) {
            throw new CommandException(cmd, e);
        }
    }

    @Override
    public Promise createPromise() {
        return new EmptyPromise(context);
    }

    private <RESULT> CommandResult<RESULT> createDefaultCommandResult(final RESULT result) {
        return new DefaultCommandResult<>(result, this);
    }

    private <RESULT> CommandResult<RESULT> createEmptyCommandResult() {
        return new EmptyCommandResult<>(this);
    }
}
