package com.mindsite.crud.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProjectRequestBean implements Serializable {

    private static final long serialVersionUID = 6369490277887569582L;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Size(min = 8, max = 8)
    @Pattern(regexp = "[0-9]+[\\\\.]?[0-9]*")
    private String startDate;

    @NotNull
    @Size(min = 8, max = 8)
    @Pattern(regexp = "[0-9]+[\\\\.]?[0-9]*")
    private String endDate;

}
