package com.morningempire.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
    

import com.morningempire.models.LoginUser;
import com.morningempire.models.User;
import com.morningempire.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // TO-DO: Write register and login methods!
    // This method will be called from the controller
    // whenever a user submits a registration form.
    public User register(User newUser, BindingResult result) {
        // TO-DO: Additional validations!
    	// TO-DO - Reject values or register if no errors:
        
        // Reject if email is taken (present in database)
    	if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "An account already exists for this email.");
        }
        // Reject if password doesn't match confirmation
    	if (!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "error.user", "Password and Confirm Password do not match.");
        }
        // Return null if result has errors
    	if (result.hasErrors()) {
            return null;
        }
        // Hash and set password, save user to database
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
        return userRepository.save(newUser);
    }
    
 	// This method will be called from the controller
    // whenever a user submits a login form.
    public User login(LoginUser newLoginObject, BindingResult result) {
        // TO-DO: Additional validations!
    	// TO-DO - Reject values:
        
    	// Find user in the DB by email
    	Optional<User> potentialUser = userRepository.findByEmail(newLoginObject.getEmail());
        // Reject if NOT present
    	if (!potentialUser.isPresent()) {
            result.rejectValue("email", "error.user", "Invalid email");
            return null;
        }
        // Reject if BCrypt password match fails
    	User user = potentialUser.get();
    	if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
    	result.rejectValue("password", "error.user", "Invalid Password!");
    	return null;
    	}
        // Return null if result has errors
    	if (result.hasErrors()) {
            return null;
        }
        // Otherwise, return the user object
    	return user;
    }
    public User findUserById(Long id) {
        // Find the user by ID in the repository
        Optional<User> optionalUser = userRepository.findById(id);

        // Return the user if found, otherwise return null
        return optionalUser.orElse(null);
    }
}
