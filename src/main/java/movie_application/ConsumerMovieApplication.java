package movie_application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.StandardServletEnvironment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import user_application.ProviderUserApplication;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class})	
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@RestController
public class ConsumerMovieApplication {

	@Bean
	//@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		//SpringApplication.run(ConsumerMovieApplication.class,args);
		
		SpringApplication applicaton = new SpringApplication(ConsumerMovieApplication.class);
		//args = new String[] {"--spring.profiles.active=movie00000000000000000"}; 
		@SuppressWarnings("unused")
		ConfigurableApplicationContext run = applicaton.run(args);
				
	}
	@CrossOrigin(origins = {"http://localhost:9000", "null"})
	@RequestMapping("/psm/add_zhanzhangfankui")
	public StoreFeedBackResponse ffff() {
		
		StoreFeedBackResponse ss = new StoreFeedBackResponse();
		ss.setCode(1);
		ss.setAttach("测试上次额");
		return ss;
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class StoreFeedBackResponse {

	    private Integer code; //code 1 表示成功
	    private Boolean success = Boolean.FALSE;
	    private String attach;


	    public Integer getCode() {
	        return code;
	    }

	    public void setCode(Integer code) {
	        this.code = code;
	        if(Integer.valueOf(1).equals(code)) {
	            success = Boolean.TRUE;
	        }
	    }

	    public Boolean getSuccess() {
	        return success;
	    }


	    public String getAttach() {
	        return attach;
	    }

	    public void setAttach(String attach) {
	        this.attach = attach;
	    }

	    @Override
	    public String toString() {
	        return "ResponseStoreFeedBack{" +
	                "code=" + code +
	                ", success=" + success +
	                ", attach='" + attach +
	                '}';
	    }
	}
}
