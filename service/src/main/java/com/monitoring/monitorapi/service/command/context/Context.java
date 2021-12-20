package com.monitoring.monitorapi.service.command.context;


import com.monitoring.monitorapi.infrastructure.MonitoringRepository;
import com.monitoring.monitorapi.service.command.CommandManager;
import org.ehcache.CacheManager;

public interface Context {
    CommandManager getCommandManager();

    CacheManager getCacheManager();

    MonitoringRepository getBcAccountService();
}
