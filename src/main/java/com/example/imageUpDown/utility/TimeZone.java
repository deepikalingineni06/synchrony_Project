package com.example.imageUpDown.utility;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZone {
    public TimeZone(ZoneId zoneId) {
		super();
		this.zoneId = zoneId;
	}

	ZoneId zoneId;

    public TimeZone() {
        zoneId = ZoneId.of("GMT");
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
