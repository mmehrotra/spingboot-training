package com.publicis.sapient.api.training.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {

    String id;

    String name;

    String description;

    String duration;

    String delivery;
}
