package cns.example.project_manage.controller;

import cns.example.project_manage.bindings.UserPayload;
import cns.example.project_manage.model.Users;
import cns.example.project_manage.projection.UserProjection;
import cns.example.project_manage.response.CustomResponse;
import cns.example.project_manage.response.ResponseModel;
import cns.example.project_manage.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProjectionFactory projectionFactory;
    private final CustomResponse customResponse;

    @GetMapping("/get-id-and-name")
    @Operation(summary = "get user Id and email list information")
    @ApiResponse(responseCode = "200", description = "", content = {@Content(schema = @Schema(implementation = UserProjection.class))})
    public ResponseModel getIdAndEmail(){
        try{
            return customResponse.success(
                    userService.getIdAndEmail().stream().map(
                            users -> projectionFactory.createProjection(UserProjection.class, users))
                            .collect(Collectors.toList())
            );
        }
        catch (Exception ex){
            log.error("getIdAndEmail : {0}", ex);
            return customResponse.error(ex);
        }
    }
}
