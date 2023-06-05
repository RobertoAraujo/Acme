package com.lamppit.pedido.model.dto;

import com.lamppit.vitrine.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    private User user;
    private String token;
}
