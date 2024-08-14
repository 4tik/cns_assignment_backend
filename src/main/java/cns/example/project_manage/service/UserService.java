package cns.example.project_manage.service;

import cns.example.project_manage.bindings.LoginPayload;
import cns.example.project_manage.bindings.UserPayload;
import cns.example.project_manage.model.Users;
import cns.example.project_manage.response.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void store(UserPayload userPayload);
    public AuthResponse login(LoginPayload loginPayload);
    public List<Users> getIdAndEmail();
//    public UserDetails loadUserByUsername(String userName);
}
