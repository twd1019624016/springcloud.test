package client_config_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 1
 * 启动时 需要去掉 
 *      <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
 *
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,
		SecurityAutoConfiguration.class,
       ManagementWebSecurityAutoConfiguration.class})
@EnableDiscoveryClient
public class ConfigClientApplication {
	public static void main(String[] args) {
		args = new String[] {"--spring.profiles.active=config_client"};
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}
