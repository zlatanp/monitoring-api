package com.monitoring.monitorapi.domain;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class DateHelper {

    public static String getStringFromDate(final Date date){
        return date.toString();
    }
}
