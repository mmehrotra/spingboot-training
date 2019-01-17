package com.publicis.sapient.api.training.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course implements Serializable {

    String id;

    String name;

    String description;

    String duration;

    String delivery;
}
