package com.fiap.fiap_tech_challenge.application.service;

import com.fiap.fiap_tech_challenge.application.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserUseCase {

    User create(User user);
    User findById(Long id);
    Page<User> searchByName(String name, Pageable pageable);
    User update(Long id, User user);
    void changePassword(Long id, String currentPassword, String newPassword); // Implementar no UserController
    void delete(Long id);

}
