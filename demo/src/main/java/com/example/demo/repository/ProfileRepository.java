package com.example.demo.repository;



import com.example.demo.entity.ProfileEntity;
import com.example.demo.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,String> {

    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> getProfileById(Integer id);
}
