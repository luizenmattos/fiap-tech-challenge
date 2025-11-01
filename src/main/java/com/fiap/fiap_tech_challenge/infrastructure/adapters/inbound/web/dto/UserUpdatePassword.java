package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdatePasswordInput;
import lombok.Data;

@Data
public class UserUpdatePassword {

    private String newPassword;

    public UserUpdatePasswordInput toInput(){
        return new UserUpdatePasswordInput(
            this.newPassword
        );
    }
}
