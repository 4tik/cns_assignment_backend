package cns.example.project_manage.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String generateToken(UserDetails userDetails);
    public String generateRefreshToken(UserDetails userDetails);
    public String getUserName(String token);
    public boolean isTokenValid(String token, UserDetails userDetails);
    public boolean isTokenValidForRoleBase(String token);
    public UserDetails getUserDetailsByToken(String token);
}
