package com.example.demo.repository;



import com.example.demo.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,String> {
    Optional<ProfileEntity> findByEmailAndVisibleTrue(String username);



}
