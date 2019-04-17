# Spring Boot REST Notes

Some notes took during the tutorial

## Setting a REST Controller

Just annotate it with `@RestController`

```
@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    public List<Student> getStudents() {

        List<Student> students = new ArrayList<>();

        students.add(new Student("John", "Smith"));
        students.add(new Student("Susan", "Chomsky"));
        students.add(new Student("Mary", "Garlic"));

        return students;
    }
}
```

## Path Variables

Such as `/api/students/0`

```
@GetMapping("/students/{studentId}")
public Student getStudent(@PathVariable int studentId) {
    return students.get(studentId);
}
```