package com.publicis.sapient.api.training.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Topic {

    String id;

    String name;

    String description;

    List<Course> courses;
}
