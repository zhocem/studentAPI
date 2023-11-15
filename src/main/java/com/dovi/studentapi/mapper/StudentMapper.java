package com.dovi.studentapi.mapper;

import com.dovi.studentapi.dto.StudentDTO;
import com.dovi.studentapi.entity.Student;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface StudentMapper {

    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

    StudentDTO mapToStudentDTO(Student student);

    Student mapToStudent(StudentDTO studentDTO);

    List<StudentDTO> mapToStudentDtoList(List<Student> studentList);
}
