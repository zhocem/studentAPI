package com.dovi.studentapi.service;

import com.dovi.studentapi.dto.AddressDTO;
import com.dovi.studentapi.dto.StudentDTO;
import com.dovi.studentapi.entity.Student;
import com.dovi.studentapi.mapper.StudentMapper;
import com.dovi.studentapi.repository.StudentRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

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
        return students.stream().map(this::getStudentDTO).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        return getStudentDTO(student);
    }


    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentRepository.save(studentMapper.mapToStudent(studentDTO));
        return studentMapper.mapToStudentDTO(student);
    }

    private AddressDTO getAddressById(Long id) {
        Mono<AddressDTO> addressDTOMono = addressWebClient.get().uri("/" + id)
                .retrieve().bodyToMono(AddressDTO.class);

        return addressDTOMono.block();
    }

    private StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = studentMapper.mapToStudentDTO(student);
        AddressDTO addressDTO = getAddressById(student.getAddressId());
        studentDTO.setAddressDTO(addressDTO);
        return studentDTO;
    }
}
