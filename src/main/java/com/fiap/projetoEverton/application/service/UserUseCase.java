package com.fiap.techchallenge1_G13.application.service;

import com.fiap.techchallenge1_G13.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserUseCase {

    User create(User user);
    User findById(Long id);
    Page<User> searchByName(String name, Pageable pageable);
    User update(Long id, User user);
    void changePassword(Long id, String currentPassword, String newPassword);
    void delete(Long id);

}
