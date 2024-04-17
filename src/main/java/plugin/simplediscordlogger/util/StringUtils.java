package plugin.simplediscordlogger.util;

import java.util.Objects;

public class StringUtils {

	public static boolean equals(String a, String b) {
		return Objects.equals(a, b);
	}

	public static boolean isEmpty(String a) {
		return a == null || a.isEmpty();
	}
}
