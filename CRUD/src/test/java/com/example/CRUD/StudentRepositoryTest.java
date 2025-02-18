package com.example.CRUD;

import com.example.CRUD.entity.Student;
import com.example.CRUD.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Solo si usas MySQL/PostgreSQL
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setPrimerNombre("Carlos");
        student.setSegundoNombre("Gómez");
        student.setEmail("carlos.gomez@example.com");

        Student savedStudent = studentRepository.save(student);
        assertNotNull(savedStudent);
        assertNotNull(savedStudent.getStudentId());
    }

    @Test
    void testFindById() {
        Student student = new Student();
        student.setPrimerNombre("Ana");
        student.setSegundoNombre("Martínez");
        student.setEmail("ana.martinez@example.com");

        Student savedStudent = studentRepository.save(student);
        Optional<Student> foundStudent = studentRepository.findById(savedStudent.getStudentId());

        assertTrue(foundStudent.isPresent());
        assertEquals("Ana", foundStudent.get().getPrimerNombre());
    }
}
