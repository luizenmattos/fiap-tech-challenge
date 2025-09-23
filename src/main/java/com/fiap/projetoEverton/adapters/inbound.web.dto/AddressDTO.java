package com.fiap.techchallenge1_G13.adapters.inbound.web.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String cep;

}
