package edu.criandoapi.criandoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.criandoapi.criandoapi.domain.repository.UserRepository;

@Service
public class ExamService {

    @Autowired
    private UserRepository userRepository;

    private boolean isAllowed(String requesterId, String ownerId) {

        return false;
    }
}
