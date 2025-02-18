package com.example.CRUD;

import com.example.CRUD.controller.StudentController;
import com.example.CRUD.entity.Student;
import com.example.CRUD.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @Mock
    private Model model;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentService.getAllStudents()).thenReturn(students);

        String viewName = studentController.getAll(model);

        verify(model, times(1)).addAttribute(eq("students"), anyList());
        assertEquals("students", viewName);
    }

    @Test
    void testShowAddStudentForm() {
        String viewName = studentController.showAddStudentForm(model);
        verify(model, times(1)).addAttribute(eq("student"), any(Student.class));
        assertEquals("student-form", viewName);
    }
}
