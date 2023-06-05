package com.lamppit.pagamento.service;

import com.lamppit.pagamento.model.dto.UserLoginDto;
import com.lamppit.pagamento.model.entity.User;
import com.lamppit.pagamento.repository.UserRepository;
import com.lamppit.pagamento.util.JsonMapperUtil;
import com.lamppit.pagamento.util.JwtUtilities;
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

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (user == null) {
            throw new RuntimeException("Usuário não cadastrado");
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        UserLoginDto userLoginDto = new UserLoginDto();

        userLoginDto.setUser(user);

        String jsonReturn = JsonMapperUtil.mapToJson(userLoginDto);

        String token = jwtUtilities.createJWT(user.getId().toString(), user.getEmail(), jsonReturn,
                new Date().getTime() + 3600000);

        userLoginDto.setToken(token);

        return userLoginDto;

    }
}
