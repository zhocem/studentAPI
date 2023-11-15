package com.dovi.studentapi.service;

import com.dovi.studentapi.dto.StudentDTO;
import com.dovi.studentapi.entity.Student;
import com.dovi.studentapi.mapper.StudentMapper;
import com.dovi.studentapi.repository.StudentRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;
    @Resource
    StudentMapper studentMapper;
    WebClient addressWebClient;

    @Autowired
    public StudentService(StudentRepository studentRepository, WebClient addressWebClient) {
        this.studentRepository = studentRepository;
        this.addressWebClient = addressWebClient;
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.mapToStudentDtoList(students);
    }

    public StudentDTO getStudentById(Long studentId) {
        return studentMapper.mapToStudentDTO(studentRepository.findById(studentId).orElseThrow());
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentRepository.save(studentMapper.mapToStudent(studentDTO));
        return studentMapper.mapToStudentDTO(student);
    }
}
