package com.monitoring.monitorapi.service.command.context;


import com.monitoring.monitorapi.infrastructure.MonitoringRepository;
import com.monitoring.monitorapi.service.command.CommandManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ehcache.CacheManager;
import org.springframework.context.ApplicationContext;

@Getter
@RequiredArgsConstructor
public class DefaultContext implements Context {
    private final ApplicationContext applicationContext;
    private final CommandManager commandManager;

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public CacheManager getCacheManager() {
        return applicationContext.getBean(CacheManager.class);
    }

    @Override
    public MonitoringRepository getBcAccountService() {
        return applicationContext.getBean(MonitoringRepository.class);
    }
}
