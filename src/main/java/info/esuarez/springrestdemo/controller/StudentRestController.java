package info.esuarez.springrestdemo.controller;


import info.esuarez.springrestdemo.entity.Student;
import info.esuarez.springrestdemo.error.StudentErrorResponse;
import info.esuarez.springrestdemo.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void InitStudents() {
        students = new ArrayList<>();
        students.add(new Student("John", "Smith"));
        students.add(new Student("Susan", "Chomsky"));
        students.add(new Student("Mary", "Garlic"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return students.get(studentId);
    }

}
