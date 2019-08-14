package movie_application.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-3)
public class UserContextAspect {

	public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    
	@Pointcut("@annotation(movie_application.service.CanAop)")
    private  void pointCutPackage() {

    }


    @Around("pointCutPackage() ")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
         System.out.println("111111111111111111111--------进入");
    	 pjp.proceed();
    	 threadLocal.remove();
    	 System.out.println("111111111111111111111--------退出");
    	 return null;
    }
}
