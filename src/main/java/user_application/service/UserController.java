package user_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }
    
    @Transactional
    @GetMapping("/insert/{id}")
    public User insert(@PathVariable Long id) {
    	User user = new User();
    	user.setId(7L);
    	user.setAge(8859);
    	
    	this.userRepository.save(user);
        User findOne = this.userRepository.findOne(7L);
        return findOne;
    }
}
