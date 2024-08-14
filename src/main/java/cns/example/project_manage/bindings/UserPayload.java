package cns.example.project_manage.bindings;

import lombok.Data;

@Data
public class UserPayload {
    private String userName;
    private String email;
    private String password;
}
