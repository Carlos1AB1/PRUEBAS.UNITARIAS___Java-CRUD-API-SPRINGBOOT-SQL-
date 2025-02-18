package com.example.CRUD.service;

import com.example.CRUD.entity.Student;
import com.example.CRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Obtener un estudiante por ID
    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    // Guardar o actualizar un estudiante
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Eliminar un estudiante
    public void deleteStudent(long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
    }
}
