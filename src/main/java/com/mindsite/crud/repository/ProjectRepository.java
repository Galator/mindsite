package com.mindsite.crud.repository;

import com.mindsite.crud.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

    List<ProjectEntity> findAll();

    List<ProjectEntity> findAllByFlgStatusProject(Integer flgStatusProject);
}
