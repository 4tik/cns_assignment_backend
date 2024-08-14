package cns.example.project_manage.bindings;

import lombok.Data;

@Data
public class LoginPayload {
    private String userName;
    private String password;
}
