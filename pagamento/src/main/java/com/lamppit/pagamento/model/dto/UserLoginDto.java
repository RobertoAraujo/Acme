package com.lamppit.pagamento.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.lamppit.pagamento.model.entity.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    private User user;
    private String token;
}
