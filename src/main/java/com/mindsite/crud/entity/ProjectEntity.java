package com.mindsite.crud.entity;

import com.mindsite.crud.bean.ProjectRequestBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "MIND_MAE_PROJECT")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = -4418331007494920056L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROJECT")
    private Long projectId;

    @Column(name = "TXT_PROJECT_NAME")
    private String txtProjectName;

    @Column(name = "TXT_DESCRIPTION")
    private String txtDescription;

    @Column(name = "FLG_STATUS_PROJECT")
    private Integer flgStatusProject;

    @Column(name = "FCH_PROJECT_START_DATE")
    private LocalDate fchProjectStartDate;

    @Column(name = "FCH_PROJECT_END_DATE")
    private LocalDate fchProjectEndDate;

    public ProjectEntity (ProjectRequestBean projectRequestBean) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        this.txtProjectName = projectRequestBean.getName();
        this.txtDescription = projectRequestBean.getDescription();
        this.fchProjectStartDate = LocalDate.parse(projectRequestBean.getStartDate(), formatter);
        this.fchProjectEndDate = LocalDate.parse(projectRequestBean.getEndDate(), formatter);
    }

}
