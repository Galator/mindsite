package com.mindsite.crud.bean;

import com.mindsite.crud.entity.ProjectEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectResponseBean implements Serializable {
    private static final long serialVersionUID = -1605821494463775711L;

    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    private String startDate;
    private String endDate;

    public ProjectResponseBean(ProjectEntity projectEntity) {
        this.id = projectEntity.getProjectId();
        this.name = projectEntity.getTxtProjectName();
        this.description = projectEntity.getTxtDescription();
        this.isActive = projectEntity.getFlgStatusProject() == 0 ? Boolean.FALSE : Boolean.TRUE;
        this.startDate = projectEntity.getFchProjectStartDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.endDate = projectEntity.getFchProjectEndDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
