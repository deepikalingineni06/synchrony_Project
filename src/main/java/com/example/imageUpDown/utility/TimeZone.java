package com.example.imageUpDown.utility;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static TimeZone getDefaultTimeZone() {
        return TimeZone.getTimeZone("UTC");
    }

    public String getTimeNow(){
        ZonedDateTime date = ZonedDateTime.now(zoneId);
        return date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

	@Override
	public String toString() {
		return "TimeZone [zoneId=" + zoneId + "]";
	}
}
