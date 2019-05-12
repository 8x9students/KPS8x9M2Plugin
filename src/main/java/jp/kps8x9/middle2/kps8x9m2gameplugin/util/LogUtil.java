package jp.kps8x9.middle2.kps8x9m2gameplugin.util;

public class LogUtil {
    public static String arrayToString(String[] array) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");

        for (int i = 0; array != null && i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }

        sb.append("]");

        return sb.toString();
    }
}
