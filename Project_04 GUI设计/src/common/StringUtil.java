package common;

public class StringUtil {
	
	// �ж��ַ����Ƿ�Ϊ��
	// ȥ���� trim()
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

}