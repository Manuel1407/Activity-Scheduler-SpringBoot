package com.ikechukwu.week8.dto;

import com.ikechukwu.week8.enums.Status;
import lombok.Data;

@Data
public class TaskDTO {

    private String title;
    private String description;
    private Status status;


}
