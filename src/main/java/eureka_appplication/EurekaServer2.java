package eureka_appplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})	
@EnableEurekaServer
public class EurekaServer2 {

	public static void main(String[] args) {
		args = new String[] {"--spring.profiles.active=eureka_2"}; 
		SpringApplication.run(EurekaServer2.class, args);
	}
}
