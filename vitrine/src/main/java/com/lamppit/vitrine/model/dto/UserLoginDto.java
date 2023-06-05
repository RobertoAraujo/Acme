package com.lamppit.vitrine.model.dto;

import com.lamppit.vitrine.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    private UserEntity userEntity;
    private String token;
}
