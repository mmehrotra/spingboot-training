package com.publicis.sapient.api.training.application.wrapper;

import com.publicis.sapient.api.training.domain.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseWrapper {

    private List<Course> data;
}
