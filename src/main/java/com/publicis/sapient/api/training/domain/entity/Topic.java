package com.publicis.sapient.api.training.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "topics")
public class Topic {

    @Id
    String id;

    String name;

    String description;

    List<Course> courses;
}
