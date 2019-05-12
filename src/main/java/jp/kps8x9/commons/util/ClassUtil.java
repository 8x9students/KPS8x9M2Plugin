package jp.kps8x9.commons.util;

public class ClassUtil {

    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[2].getClassName();
    }

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static String getLogInfo() {
        return Thread.currentThread().getStackTrace()[2].getClassName() + "#" +
                Thread.currentThread().getStackTrace()[2].getMethodName() + " ";
    }
}
