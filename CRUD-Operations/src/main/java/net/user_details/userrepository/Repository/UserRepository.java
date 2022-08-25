package net.user_details.userrepository.Repository;


import net.user_details.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailid(String emailid);
    Boolean existsByEmailid(String emailid);





}
