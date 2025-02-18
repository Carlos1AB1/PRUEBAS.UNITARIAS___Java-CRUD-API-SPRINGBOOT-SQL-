package com.example.CRUD.controller;

import com.example.CRUD.entity.Student;
import com.example.CRUD.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Listar estudiantes
    @GetMapping
    public String getAll(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    // Formulario para agregar estudiante
    @GetMapping("/new")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // Guardar o actualizar estudiante
    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/student"; // Redirigir a la lista de estudiantes
    }

    // Formulario para editar estudiante
    @GetMapping("/edit/{id}")
    public String showEditStudentForm(@PathVariable("id") Long id, Model model) {
        Optional<Student> student = studentService.getStudent(id);
        model.addAttribute("student", student.orElse(new Student()));
        return "student-form";
    }

    // Eliminar estudiante
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student";
    }
}
