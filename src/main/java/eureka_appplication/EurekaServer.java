package eureka_appplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import zipkin.autoconfigure.ui.ZipkinUiAutoConfiguration;
import zipkin.server.ZipkinServerConfiguration;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class,ZipkinServerConfiguration.class,ZipkinUiAutoConfiguration.class})	
@EnableEurekaServer
public class EurekaServer {

	public static void main(String[] args) {
		//args = new String[] {"--spring.profiles.active=eureka_1"}; 
		SpringApplication.run(EurekaServer.class, args);
	}
}
