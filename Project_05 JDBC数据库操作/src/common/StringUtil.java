package common;

public class StringUtil {

	// ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	// È¥À¨ºÅ trim()
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	// ÅÐ¶Ï×Ö·û´®·Ç¿Õ
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

}