package com.monitoring.monitorapi.service.command.exception;

import com.monitoring.monitorapi.service.command.Command;
import lombok.Getter;

@Getter
public class CommandException extends RuntimeException {
  private static final String ERROR_MESSAGE_TEMPLATE = "Command %s execution failed.";
  private final Command<?> command;

  public CommandException(final Command<?> command, final Throwable e) {
    super(String.format(ERROR_MESSAGE_TEMPLATE, command.getClass().getName()), e);

    this.command = command;
  }
}
