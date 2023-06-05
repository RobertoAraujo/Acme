package com.lamppit.vitrine.service;

import com.lamppit.vitrine.model.dto.UserLoginDto;
import com.lamppit.vitrine.model.entity.UserEntity;
import com.lamppit.vitrine.repository.UserRepository;
import com.lamppit.vitrine.util.JsonMapperUtil;
import com.lamppit.vitrine.util.JwtUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class AuthenticateUserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtilities jwtUtilities;

    public UserLoginDto login(String username, String password) throws RuntimeException {

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (userEntity == null) {
            throw new RuntimeException("Usuário não cadastrado");
        }

        if (!encoder.matches(password, userEntity.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        UserLoginDto userLoginDto = new UserLoginDto();

        userLoginDto.setUserEntity(userEntity);

        String jsonReturn = JsonMapperUtil.mapToJson(userLoginDto);

        String token = jwtUtilities.createJWT(userEntity.getId().toString(), userEntity.getEmail(), jsonReturn,
                new Date().getTime() + 3600000);

        userLoginDto.setToken(token);

        return userLoginDto;

    }
}
