package com.mindsite.crud.service;

import com.mindsite.crud.bean.ProjectRequestBean;
import com.mindsite.crud.bean.ProjectResponseBean;

import java.util.List;

public interface ProjectService {

    ProjectResponseBean createProject(ProjectRequestBean project);

    List<ProjectResponseBean> readAllProject();

    List<ProjectResponseBean> readProjectByStatus(Integer status);

    ProjectResponseBean readProjectById(Long projectId);

    Boolean updateProject(Long projectId, ProjectRequestBean project);

    Boolean deleteProject(Long projectId);

}
