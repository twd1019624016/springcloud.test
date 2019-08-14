package spring_util;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class GetResourcePathDemo {

	public static void main(String[] args) throws IOException {
		
		
		
		String str1 = "classpath*:/ttt/**/*.xml";
		String str2 = "classpath:/ttt/*.xml";
		
		

		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = patternResolver.getResources(str1);
		for (Resource resource : resources) {
			System.out.println(resource.getURL());
		}
		System.out.println("----------------------------------------");
		PathMatchingResourcePatternResolver patternResolver2 = new PathMatchingResourcePatternResolver();
		Resource[] resources266 = patternResolver2.getResources(str2);
		
		
		for (Resource resource : resources266) {
			System.out.println(resource.getURL());
		}

	}
}
