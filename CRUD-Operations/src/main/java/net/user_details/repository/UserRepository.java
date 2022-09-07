package net.user_details.repository;


import net.user_details.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailid(String emailid);
    Boolean existsByEmailid(String emailid);
    List<User> findByRole(String role);


    List<User> findByApprovedStatusAndIdNot(String approved, long id );




}
