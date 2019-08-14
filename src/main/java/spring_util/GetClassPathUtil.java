package spring_util;

import java.net.URLClassLoader;
import java.util.Arrays;

public class GetClassPathUtil {

	public static void main(String[] args) {
		System.out.println(getClasspath());
	}
	private static String getClasspath() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader instanceof URLClassLoader) {
			return Arrays.toString(((URLClassLoader) classLoader).getURLs());
		}
		return "unknown";
	}

}
