package com.acciojob.bookmyshowapplications.Repository;

import com.acciojob.bookmyshowapplications.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


    public User findUserByMobNo(String mobNo);
}
