package spring_util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
/**
 * 
 * @author 1
 * 从当前工程的 及所有依赖 的 META-INF/spring.factories 文件中 查找并实例化制定的 类
 */
public class SpringFactoriesInstancesDemo {
	String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

	
	
	public static void main(String[] args) {
		
		Collection<? extends ApplicationContextInitializer> springFactoriesInstances = getSpringFactoriesInstances(ApplicationContextInitializer.class,new Class<?>[] {});
		for (ApplicationContextInitializer applicationContextInitializer : springFactoriesInstances) {
			System.out.println(applicationContextInitializer.getClass().getName());
		}
	}

	private static <T> Collection<? extends T> getSpringFactoriesInstances(Class<T> type,
			Class<?>[] parameterTypes, Object... args) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// Use names and ensure unique to protect against duplicates
		Set<String> names = new LinkedHashSet<String>(
				SpringFactoriesLoader.loadFactoryNames(type, classLoader));
		List<T> instances = createSpringFactoriesInstances(type, parameterTypes,
				classLoader, args, names);
		AnnotationAwareOrderComparator.sort(instances);
		return instances;
	}
	
	private static <T> List<T> createSpringFactoriesInstances(Class<T> type,
			Class<?>[] parameterTypes, ClassLoader classLoader, Object[] args,
			Set<String> names) {
		List<T> instances = new ArrayList<T>(names.size());
		for (String name : names) {
			try {
				Class<?> instanceClass = ClassUtils.forName(name, classLoader);
				Assert.isAssignable(type, instanceClass);
				Constructor<?> constructor = instanceClass
						.getDeclaredConstructor(parameterTypes);
				T instance = (T) BeanUtils.instantiateClass(constructor, args);
				instances.add(instance);
			}
			catch (Throwable ex) {
				throw new IllegalArgumentException(
						"Cannot instantiate " + type + " : " + name, ex);
			}
		}
		return instances;
	}
	
}
class CustomApplicationContextInitializer implements ApplicationContextInitializer{

	
	public CustomApplicationContextInitializer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		// TODO Auto-generated method stub
		
	}
	
}