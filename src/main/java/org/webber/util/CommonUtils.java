package org.webber.util;

import java.util.Date;

public class CommonUtils {
	public static synchronized String getCorrelationID() {
		String time = String.valueOf(new Date().getTime());
		return time;
	}
}
