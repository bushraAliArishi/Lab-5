package com.example.lab5_student.Controller;

import com.example.lab5_student.ApiResponse.ApiResponse;
import com.example.lab5_student.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.io.Writer;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/display")
    public ArrayList<Student> display() {
        return students;
    }

    @GetMapping("classify/{key}")
    //key for choosing the honor class from 1 to 3
    public ApiResponse classify(@PathVariable int key) {
        ArrayList<Student> studentss = new ArrayList<>();
        for (Student student : students) {
            switch (key) {
                case 1:
                    if (student.getGPA() > 4 && student.getGPA() <= 5) {
                        studentss.add(student);
                    }
                    break;
                case 2:
                    if (student.getGPA() > 3 && student.getGPA() < 4) {
                        studentss.add(student);
                    }
                    break;
                case 3:
                    if (student.getGPA() > 2 && student.getGPA() < 3) {
                        studentss.add(student);
                    }
            }
        }


        if (key == 1) {
            return new ApiResponse("the 1st class Student are \n" + studentss.toString() + "\n");
        } else if (key == 2) {
            return new ApiResponse("the 2nd class Student are\n " + studentss.toString() + "\n");
        } else {
            return new ApiResponse("the 3ed class Student are \n" + studentss.toString() + "\n");
        }
    }

    @GetMapping("classify_student/{key}")
    //key is student id
    public ApiResponse classify_student(@PathVariable int key) {
        if (students.get(key).getGPA() > 4.3) {
            return new ApiResponse("the student " + students.get(key).getName() + " is 1st class honor  \n");
        } else if (students.get(key).getGPA() > 3.3 && students.get(key).getGPA() <= 4) {
            return new ApiResponse("the student " + students.get(key).getName() + " is 2nd class honor  \n");
        } else if (students.get(key).getGPA() > 2.3 && students.get(key).getGPA() <= 3) {
            return new ApiResponse("the student " + students.get(key).getName() + " is 3ed class honor  \n");
        } else return new ApiResponse("the student " + students.get(key).getName() + " has no  honor class\n");

    }

    @GetMapping("greater")
    public ArrayList<Student> greater_Average() {
        ArrayList<Student> greater_Average = new ArrayList<>();
        double averageGPA = 0.0;
        for (Student student : students) {
            averageGPA = averageGPA + student.getGPA();
        }
        averageGPA = averageGPA / students.size();

        for (Student student : students) {
            if (student.getGPA() > averageGPA) {
                greater_Average.add(student);
            }
        }
        return greater_Average;
    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student) {
        students.set(index, student);
        return new ApiResponse("updated successfully");
    }

    @DeleteMapping("delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        students.remove(index);
        return new ApiResponse("deleted successfully");
    }

}
