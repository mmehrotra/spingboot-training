package com.publicis.sapient.api.training.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course implements Serializable {

    private String id;
    private String name;
    private String description;
    private String duration;
    private String delivery;

    public Course(){}

    public Course(String id, String name, String description, String duration, String delivery) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.delivery = delivery;
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;
        private String duration;
        private String delivery;

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

        public Builder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder setDelivery(String delivery) {
            this.delivery = delivery;
            return this;
        }

        public Course build() {
            return new Course(id, name, description, duration, delivery);
        }
    }
}
