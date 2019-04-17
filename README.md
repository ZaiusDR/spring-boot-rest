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

## Exception Handling

It seems new versions of Spring can convert automatically the exceptions to JSON. But for customize it:

* Create a Error Response POJO:

```
public class StudentErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

    public StudentErrorResponse() {
    }

    // Getters / Setters
}
```

It will be converted to JSON as usual with Jackson

* Create a custom Exception:

```
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }
}
```

* Throw exception in the controller:

```
@GetMapping("/students/{studentId}")
public Student getStudent(@PathVariable int studentId) {

    if (studentId >= students.size() || studentId < 0) {
        throw new StudentNotFoundException("Student id not found - " + studentId);
    }
    return students.get(studentId);
}
```

* Add an Exception Handler in the Controller:

```
@ExceptionHandler
public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
    StudentErrorResponse error = new StudentErrorResponse();

    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
```

The `ResponseEntity` object, provides a fine control for the Controller response, such as
the Status Code, Body, etc...


## Advice Controllers (AOP)

They are useful for functionality common to many controllers. Like the Exception Handling.
Just use the `@ControllerAdvice` annotation, and it will intercept all the requests and responses.

```
@ControllerAdvice
public class StudentRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse();
    ...
    ...

```

**This is a Best Practice**


## Basics on API Design

### Understanding Usage

- Who is going to use the API
- How they will use it.
- Design based on requirements.

### API Design Process

- Review API Requirements (I.e: CRUD ops for an entity).
- Identify main resource/entity - The convention is to use plural nouns for endpoints (/customers).
- Use HTTP methods to assign action on resource:

<table>
    <tr>
        <th>HTTP Method</th>
        <th>Endpoint</th>
        <th>CRUD ACtion</th>
    </tr>
    <tr>
        <td>POST</th>
        <td>/api/customers</th>
        <td>Create new customer</th>
    </tr>
    <tr>
        <td>GET</th>
        <td>/api/customers</th>
        <td>Read a list of customers</th>
    </tr>
    <tr>
        <td>GET</th>
        <td>/api/customers/{customerId}</th>
        <td>Read a single customer</th>
    </tr>
    <tr>
        <td>PUT</th>
        <td>/api/customers</th>
        <td>Update an existing customer</th>
    </tr>
    <tr>
        <td>DELETE</th>
        <td>/api/customers/{customerId}</th>
        <td>Delete an existing customer</th>
    </tr>
</table>


### REST Anti-patterns

Don't use endpoints for CRUD Ops (Like: /deleteCustomer, /addCustomer). Use HTTP Methods.



### REST Implementation

Continues on https://github.com/ZaiusDR/spring-web-customer-tracker