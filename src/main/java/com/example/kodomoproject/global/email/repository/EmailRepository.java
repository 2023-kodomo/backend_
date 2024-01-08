package com.example.kodomoproject.global.email.repository;

import com.example.kodomoproject.global.email.entity.UserEmail;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<UserEmail, String> {
}
