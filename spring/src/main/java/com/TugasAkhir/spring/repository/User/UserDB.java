package com.TugasAkhir.spring.repository.User;

import com.TugasAkhir.spring.model.User.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends JpaRepository<BaseUser, String> {
}
