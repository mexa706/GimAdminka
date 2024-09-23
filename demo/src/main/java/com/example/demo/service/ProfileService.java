package com.example.demo.service;

import com.example.demo.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;


}
