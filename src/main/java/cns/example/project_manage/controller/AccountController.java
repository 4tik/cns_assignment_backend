package cns.example.project_manage.controller;

import cns.example.project_manage.bindings.LoginPayload;
import cns.example.project_manage.bindings.UserPayload;
import cns.example.project_manage.response.CustomResponse;
import cns.example.project_manage.response.ResponseModel;
import cns.example.project_manage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;
    private final CustomResponse customResponse;

    @PostMapping("/registration")
    public ResponseModel registration(@RequestBody UserPayload userPayload){
        try{
            userService.store(userPayload);
            return customResponse.success(null);
        }
        catch (Exception ex){
            return customResponse.error(ex);
        }
    }

    @PostMapping("/login")
    public ResponseModel registration(@RequestBody LoginPayload loginPayload){
        try{
            return customResponse.success(userService.login(loginPayload));
        }
        catch (Exception ex){
            return customResponse.error(ex);
        }
    }


}
