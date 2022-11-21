package com.TugasAkhirAPI.springapi.repository.User;

import com.TugasAkhirAPI.springapi.model.User.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends JpaRepository<BaseUser, String> {
    BaseUser findByUsername(String username);
}
