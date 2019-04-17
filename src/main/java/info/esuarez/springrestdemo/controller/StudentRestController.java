package info.esuarez.springrestdemo.controller;


import info.esuarez.springrestdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    public List<Student> sayHello() {

        List<Student> students = new ArrayList<>();

        students.add(new Student("John", "Smith"));
        students.add(new Student("Susan", "Chomsky"));
        students.add(new Student("Mary", "Garlic"));

        return students;
    }
}
