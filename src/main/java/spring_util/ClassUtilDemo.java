package spring_util;

import org.springframework.util.ClassUtils;

/**
 * 判断一个类是否存在
 * 
 * @author 1
 *
 */
public class ClassUtilDemo {

	private static final String[] WEB_ENVIRONMENT_CLASSES = { "javax.servlet.Servlet",
			"org.springframework.web.context.ConfigurableWebApplicationContext" };

	public static void main(String[] args) {
		System.out.println("999999999999999");
		for (String className : WEB_ENVIRONMENT_CLASSES) {
			if (ClassUtils.isPresent(className, null)) {
				System.out.println(String.format("className:%s 存在", className));
			} else {
				System.err.println(String.format("className:%s 不存在", className));
			}
		}

	}
}
