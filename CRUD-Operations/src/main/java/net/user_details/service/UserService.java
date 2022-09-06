package net.user_details.service;


import net.user_details.model.User;
import net.user_details.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }



    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Object findAll() {
        return userRepository.findAll();
    }

    public  Optional<User> findByEmail(String emailid){
        return userRepository.findByEmailid(emailid);
}

    public Object getAllAdmin(){
        return userRepository.findByRole("Admin");
    }

    public List<User> getAllApprovedUser(String approved, long id){
        return userRepository.findByApprovedStatusAndIdNot(approved, id);

    }


}
