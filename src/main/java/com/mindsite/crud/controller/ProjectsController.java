package com.mindsite.crud.controller;

import com.mindsite.crud.bean.ProjectRequestBean;
import com.mindsite.crud.bean.ProjectResponseBean;
import com.mindsite.crud.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation(value = "Alta de un nuevo proyecto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 201, response = ProjectResponseBean.class)
    @PostMapping
    public ResponseEntity<ProjectResponseBean> saveProject (@Valid @RequestBody ProjectRequestBean projectRequestBean) {
        HttpStatus httpStatus = HttpStatus.CREATED;

        ProjectResponseBean projectResponseBean = projectService.createProject(projectRequestBean);

        if (projectResponseBean == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(projectResponseBean);
    }

    @ApiOperation(value = "Consulta todos los proyectos existentes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200, response = ProjectResponseBean.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No hay elementos")})
    @GetMapping
    public ResponseEntity<List<ProjectResponseBean>> getAllProject (){
        HttpStatus httpStatus = HttpStatus.OK;

        List<ProjectResponseBean> projectResponseBeanList = projectService.readAllProject();

        if (projectResponseBeanList.isEmpty()){
            httpStatus = HttpStatus.NO_CONTENT;
            projectResponseBeanList = Collections.emptyList();
        }
        return ResponseEntity.status(httpStatus).body(projectResponseBeanList);
    }

    @ApiOperation(value = "Consulta todos los proyectos existentes por su estatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200, response = ProjectResponseBean.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No hay elementos")})
    @GetMapping(value = "/status/{status}")
    public ResponseEntity<List<ProjectResponseBean>> getProjectByStatus (@PathVariable(name = "status") Integer status){
        HttpStatus httpStatus = HttpStatus.OK;

        List<ProjectResponseBean> projectResponseBeanList = projectService.readProjectByStatus(status);

        if (projectResponseBeanList.isEmpty()){
            httpStatus = HttpStatus.NO_CONTENT;
            projectResponseBeanList = Collections.emptyList();
        }
        return ResponseEntity.status(httpStatus).body(projectResponseBeanList);
    }
    @ApiOperation(value = "Consulta de projecto por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200, response = ProjectResponseBean.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No se encontro el proyecto")})
    @GetMapping(value = "/{projectId}")
    public ResponseEntity<ProjectResponseBean> getProjectById (@PathVariable(name = "projectId") Long projectId){
        HttpStatus httpStatus = HttpStatus.OK;

        ProjectResponseBean projectResponseBean = projectService.readProjectById(projectId);

        if (projectResponseBean == null){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).body(projectResponseBean);
    }

    @ApiOperation(value = "Actualiza un projecto por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No se encontro el proyecto")})
    @PutMapping(value = "/{projectId}")
    public ResponseEntity projectUpdate (@Valid @RequestBody ProjectRequestBean projectRequestBean,
                                         @PathVariable(name = "projectId") Long projectId) {
        HttpStatus httpStatus = HttpStatus.OK;
        Boolean isUpdated = projectService.updateProject(projectId, projectRequestBean);

        if (!isUpdated){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).build();
    }

    @ApiOperation(value = "Actualiza un projecto por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No se encontro el proyecto")})
    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity projectDelete (@PathVariable(name = "projectId") Long projectId) {
        HttpStatus httpStatus = HttpStatus.OK;
        Boolean isDeleted = projectService.deleteProject(projectId);

        if (!isDeleted){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).build();
    }

}
