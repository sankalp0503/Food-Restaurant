package com.zero.restaurant.Repository;


import com.zero.restaurant.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;

public interface UserRepository  extends MongoRepository<Users,String> {

    Optional<Users> findUsersByUserEmail(String userEmail);

}
