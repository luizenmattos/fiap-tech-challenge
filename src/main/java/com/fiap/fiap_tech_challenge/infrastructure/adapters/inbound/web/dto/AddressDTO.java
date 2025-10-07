package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String cep;

}
