package com.publicis.sapient.api.training.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "topics")
public class Topic implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
    private List<Course> courses;

    public Topic() {}

    public Topic(String id, String name, String description, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.courses = courses;
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private List<Course> courses;

        public Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCourses(List<Course> courses) {
            this.courses = courses;
            return this;
        }

        public Topic build() {
            return new Topic(id, name, description, courses);
        }
    }
}
