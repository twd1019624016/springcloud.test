package movie_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

/**
 * @author 1
 *
 */
@RestController
public class MovieController {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient balancerClient;
	
	@Autowired
	private UserFeignClient userFeignClient ;
	
	//@HystrixCommand(fallbackMethod="findByIdFallback")
	@GetMapping("user/{id}")
	public User findById(@PathVariable Long id) {
		//return this.restTemplate.getForObject("http://localhost:8000/"+id , User.class);
		//return this.restTemplate.getForObject("http://microservice-provider-user/"+id , User.class);
return this.userFeignClient.findById(id);
	}
	
	/**查询服务microservice-provider-user信息 
	 * @return
	 */
	@GetMapping("user-instance")
	public List<ServiceInstance> showInfo() {
		return this.discoveryClient.getInstances("microservice-provider-user");
	}
	@Autowired
	MovieService movieService;
	
	@GetMapping("log-instance")
	public ServiceInstance logUserInstance() {
		
		movieService.testMovie();
		
		Long long1 = UserContextAspect.threadLocal.get();
		
		System.out.println("不应该有值----------:"+long1);
		
		ServiceInstance choose = this.balancerClient.choose("microservice-provider-user");
		return choose;
	}
	
	
	public User findByIdFallback(Long id) {
		User user = new User();
		user.setId(-1l);
		user.setName("默认用户");
		return user;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
