# Spring Boot REST Notes

Some notes took during the tutorial

## Setting a REST Controller

Just annotate it with `@RestController`

```
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
```