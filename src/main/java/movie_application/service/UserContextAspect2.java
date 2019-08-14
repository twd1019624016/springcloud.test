package movie_application.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-1)
public class UserContextAspect2 {

	
	@Pointcut("@annotation(movie_application.service.CanAop)")
    private  void pointCutPackage() {

    }


    @Around("pointCutPackage() ")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
         System.out.println("2222--------进入");
         UserContextAspect.threadLocal.set(99L);
    	 pjp.proceed();
    	 System.out.println("22222--------退出");
    	 return null;
    }
}
