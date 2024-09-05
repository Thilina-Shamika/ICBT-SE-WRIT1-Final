package AbcRestaurantApp.service;

import AbcRestaurantApp.entity.User;
import AbcRestaurantApp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User postUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user){
        Optional<User> optionalUser=userRepository.findById(id);

        if (optionalUser.isPresent()){
            User exsistingUser = optionalUser.get();

            exsistingUser.setEmail(user.getEmail());
            exsistingUser.setName(user.getName());
            exsistingUser.setPhone(user.getPhone());
            exsistingUser.setRole(user.getPhone());

            return userRepository.save(exsistingUser);
        }
        return null;
    }
}
