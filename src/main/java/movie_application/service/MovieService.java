package movie_application.service;

import org.springframework.stereotype.Service;

@Service
public class MovieService {

	@CanAop
	public void testMovie() {
		System.out.println("user------------------------");
	}
}
