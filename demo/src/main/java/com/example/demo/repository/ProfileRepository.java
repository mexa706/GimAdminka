package com.example.demo.repository;



import com.example.demo.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,String> {

    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> getProfileById(Integer id);

    Iterable<ProfileEntity> findAll();
}
