package cns.example.project_manage.service.impl;

import cns.example.project_manage.bindings.LoginPayload;
import cns.example.project_manage.bindings.UserPayload;
import cns.example.project_manage.config.SecurityUtils;
import cns.example.project_manage.model.Users;
import cns.example.project_manage.repository.UsersRepository;
import cns.example.project_manage.response.AuthResponse;
import cns.example.project_manage.service.JwtService;
import cns.example.project_manage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final JwtService jwtService;

    @Override
    public void store(UserPayload userPayload) {
        Users user = Users.builder()
                .userName(userPayload.getUserName())
                .email(userPayload.getEmail())
                .password(SecurityUtils.hashPassword(userPayload.getPassword()))
                .createdOn(new Date(System.currentTimeMillis()))
                .build();
        usersRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginPayload loginPayload) {
        Users user = usersRepository.findByUserName(loginPayload.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(user!=null && SecurityUtils.checkPasswords(loginPayload.getPassword(), user.getPassword())){
            return getAuthToken(user);
        }
        return null;
    }

    @Override
    public List<Users> getIdAndEmail() {
        return usersRepository.findAll();
    }

//    @Override
//    public Users loadUserByUsername(String userName) {
//        return usersRepository.findByUserName(userName).orElse(null);
//    }


    private AuthResponse getAuthToken(Users user){
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer ")
                .userId(user.getId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
