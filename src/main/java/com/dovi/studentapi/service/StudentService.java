package com.dovi.studentapi.service;

import com.dovi.studentapi.dto.AddressDTO;
import com.dovi.studentapi.dto.StudentDTO;
import com.dovi.studentapi.entity.Student;
import com.dovi.studentapi.feignClients.FeignClient;
import com.dovi.studentapi.mapper.StudentMapper;
import com.dovi.studentapi.repository.StudentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    StudentRepository studentRepository;
    @Resource
    StudentMapper studentMapper;

    CommonService commonService;
    FeignClient feignClient;

    public StudentService(StudentRepository studentRepository,
                          FeignClient feignClient,
                          CommonService commonService) {
        this.studentRepository = studentRepository;
        this.feignClient = feignClient;
        this.commonService = commonService;
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


    private StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = studentMapper.mapToStudentDTO(student);
        studentDTO.setAddressDTO(commonService.getAddressById(student.getAddressId()));
        return studentDTO;
    }

//    @CircuitBreaker(name = "addressService",fallbackMethod = "fallbackGetAddressById")
//    private AddressDTO getAddressById(long addressId) {
//        return feignClient.getAddressById(addressId);
//    }
//
//    public AddressDTO fallbackGetAddressById(long addressId, Throwable throwable){
//        return new AddressDTO();
//    }
}
