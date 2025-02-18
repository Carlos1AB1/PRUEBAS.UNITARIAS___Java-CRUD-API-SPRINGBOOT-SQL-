package com.example.CRUD;

import com.example.CRUD.entity.Student;
import com.example.CRUD.repository.StudentRepository;
import com.example.CRUD.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentId(1L);
        student.setPrimerNombre("Juan");
        student.setSegundoNombre("Perez");
        student.setEmail("juan.perez@example.com");
    }

    @Test
    void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));
        List<Student> students = studentService.getAllStudents();
        assertFalse(students.isEmpty());
        assertEquals(1, students.size());
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Optional<Student> foundStudent = studentService.getStudent(1L);
        assertTrue(foundStudent.isPresent());
        assertEquals("Juan", foundStudent.get().getPrimerNombre());
    }

    @Test
    void testSaveStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student savedStudent = studentService.saveStudent(student);
        assertNotNull(savedStudent);
        assertEquals("Juan", savedStudent.getPrimerNombre());
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1L);
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}
