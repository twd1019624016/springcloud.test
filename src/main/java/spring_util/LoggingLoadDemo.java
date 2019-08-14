package spring_util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.logging.LoggingSystem;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * springboot 日志系统选择
 * @author 1
 *
 */
public class LoggingLoadDemo {
	
	private static final Map<String, String> SYSTEMS;

	static {
		Map<String, String> systems = new LinkedHashMap<String, String>();
		systems.put("ch.qos.logback.core.Appender",
				"org.springframework.boot.logging.logback.LogbackLoggingSystem");
		systems.put("org.apache.logging.log4j.core.impl.Log4jContextFactory",
				"org.springframework.boot.logging.log4j2.Log4J2LoggingSystem");
		systems.put("java.util.logging.LogManager",
				"org.springframework.boot.logging.java.JavaLoggingSystem");
		SYSTEMS = Collections.unmodifiableMap(systems);
	}
	
	public static LoggingSystem get(ClassLoader classLoader) {
		/*String loggingSystem = System.getProperty(SYSTEM_PROPERTY);
		if (StringUtils.hasLength(loggingSystem)) {
			if (NONE.equals(loggingSystem)) {
				return new NoOpLoggingSystem();
			}
			return get(classLoader, loggingSystem);
		}*/
		for (Map.Entry<String, String> entry : SYSTEMS.entrySet()) {
			if (ClassUtils.isPresent(entry.getKey(), classLoader)) {
				return get(classLoader, entry.getValue());
			}
		}
		throw new IllegalStateException("No suitable logging system located");
	}

	private static LoggingSystem get(ClassLoader classLoader, String loggingSystemClass) {
		try {
			Class<?> systemClass = ClassUtils.forName(loggingSystemClass, classLoader);
			return (LoggingSystem) systemClass.getConstructor(ClassLoader.class)
					.newInstance(classLoader);
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}


}
