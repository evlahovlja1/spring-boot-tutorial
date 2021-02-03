package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken!");
        }
        else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (exists) {
            studentRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("No student with id " + id);
        }
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " not found!"));
        if (name != null && name.length() > 0 && !student.getName().equals(name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !student.getEmail().equals(email)) {
            if (studentRepository.findStudentByEmail(email).isPresent()) {
                throw new IllegalArgumentException("email taken");
            }
            student.setEmail(email);
        }
    }
}
