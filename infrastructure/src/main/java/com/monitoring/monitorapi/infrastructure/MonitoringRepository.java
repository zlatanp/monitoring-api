package com.monitoring.monitorapi.infrastructure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MonitoringRepository extends PagingAndSortingRepository<MonitoringData, UUID> {

    @Query(value = "SELECT * FROM monitoring",
            nativeQuery = true)
    List<MonitoringData> getAllMonitoringData();
}
