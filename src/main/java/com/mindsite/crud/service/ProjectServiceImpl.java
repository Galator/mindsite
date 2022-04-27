package com.mindsite.crud.service;

import com.mindsite.crud.bean.ProjectRequestBean;
import com.mindsite.crud.bean.ProjectResponseBean;
import com.mindsite.crud.entity.ProjectEntity;
import com.mindsite.crud.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectResponseBean createProject(ProjectRequestBean project) {

        ProjectEntity projectEntity = new ProjectEntity(project);
        projectEntity.setFlgStatusProject(1);

        ProjectEntity projectEntityPersist = projectRepository.save(projectEntity);
        return new ProjectResponseBean(projectEntityPersist);
    }

    @Override
    public List<ProjectResponseBean> readAllProject() {
        List<ProjectResponseBean> projectResponseBeans = new ArrayList<>();
        projectRepository.findAll().stream().forEach(projectEntity -> projectResponseBeans.add(new ProjectResponseBean(projectEntity)));
        return projectResponseBeans;
    }

    @Override
    public List<ProjectResponseBean> readProjectByStatus(Integer status) {
        List<ProjectResponseBean> projectResponseBeans = new ArrayList<>();
        projectRepository.findAllByFlgStatusProject(status).stream().forEach(projectEntity -> new ProjectResponseBean(projectEntity));
        return projectResponseBeans;
    }

    @Override
    public ProjectResponseBean readProjectById(Long projectId) {
        return projectRepository.findById(projectId).map(projectEntity -> new ProjectResponseBean(projectEntity)).orElse(null);
    }

    @Override
    public Boolean updateProject(Long projectId, ProjectRequestBean project) {

        return projectRepository.findById(projectId).map(projectEntity -> {
            ProjectEntity projectUpd = new ProjectEntity(project);
            projectUpd.setProjectId(projectId);

            projectRepository.save(projectUpd);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }

    @Override
    public Boolean deleteProject(Long projectId) {
        Boolean isDeleted = Boolean.FALSE;
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
            isDeleted = Boolean.TRUE;
        }

        return isDeleted;
    }


}
