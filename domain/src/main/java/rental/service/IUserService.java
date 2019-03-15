package rental.service;

import rental.model.User;

import java.util.List;
import java.util.Optional;


public interface IUserService {

	User save(User user);

	Optional<User> find(String id);

	Optional<User> findByUsername(String username);
	
}
