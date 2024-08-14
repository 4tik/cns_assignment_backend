package cns.example.project_manage.controller;

import cns.example.project_manage.bindings.ProjectPayload;
import cns.example.project_manage.model.Projects;
import cns.example.project_manage.response.CustomResponse;
import cns.example.project_manage.response.ResponseModel;
import cns.example.project_manage.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final CustomResponse customResponse;

    @GetMapping
    @Operation(summary = "get all projects")
    @ApiResponse(responseCode = "200", description = "", content = {@Content(schema = @Schema(implementation = Projects.class))})
    public ResponseModel getProjects(){
        try{
            return customResponse.success(projectService.getProjects());
        }
        catch (Exception ex){
            log.error("storeProject : {0}", ex);
            return customResponse.error(ex);
        }
    }

    @GetMapping("/get-user-id/{userId}")
    @Operation(summary = "get all projects by user id")
    @ApiResponse(responseCode = "200", description = "", content = {@Content(schema = @Schema(implementation = Projects.class))})
    public ResponseModel getByUserId(@PathVariable Long userId){
        try{
            return customResponse.success(projectService.getProjectsByUserId(userId));
        }
        catch (Exception ex){
            log.error("storeProject : {0}", ex);
            return customResponse.error(ex);
        }
    }

    @PostMapping
    @Operation(summary = "add project")
    @ApiResponse(responseCode = "201", description = "", content = {@Content(schema = @Schema(implementation = String.class))})
    public ResponseModel storeProject(@RequestBody ProjectPayload projectPayload){
        try{
            projectService.store(projectPayload);
            return customResponse.success(null);
        }
        catch (Exception ex){
            log.error("storeProject : {0}", ex);
            return customResponse.error(ex);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "get project by id")
    @ApiResponse(responseCode = "200", description = "", content = {@Content(schema = @Schema(implementation = Projects.class))})
    public ResponseModel getProjectById(@PathVariable Long id){
        try{
            return customResponse.success(projectService.getById(id));
        }
        catch (Exception ex){
            log.error("storeProject : {0}", ex);
            return customResponse.error(ex);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "update project by project id")
    @ApiResponse(responseCode = "200", description = "", content = {@Content(schema = @Schema(implementation = String.class))})
    public ResponseModel updateProject(@PathVariable Long id, @RequestBody ProjectPayload projectPayload){
        try{
            projectService.updateById(id,projectPayload);
            return customResponse.success(null);
        }
        catch (Exception ex){
            log.error("storeProject : {0}", ex);
            return customResponse.error(ex);
        }
    }

    @GetMapping("/get-report")
    @Operation(summary = "delete project by id")
    @ApiResponse(responseCode = "200", description = "", content = {@Content(schema = @Schema(implementation = String.class))})
    public ResponseEntity<String> getReport(){
        try{
            return new ResponseEntity<String>(projectService.getProjectsReport(), HttpStatus.OK);
        }
        catch (Exception ex){
            log.error("deleteById : {0}", ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete project by id")
    @ApiResponse(responseCode = "200", description = "", content = {@Content(schema = @Schema(implementation = String.class))})
    public ResponseModel deleteById(@PathVariable Long id){
        try{
            projectService.deleteById(id);
            return customResponse.success(null);
        }
        catch (Exception ex){
            log.error("deleteById : {0}", ex);
            return customResponse.error(ex);
        }
    }
}
