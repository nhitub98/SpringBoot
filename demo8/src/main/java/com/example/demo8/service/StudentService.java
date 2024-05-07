package com.example.demo8.service;

import com.example.demo8.dto.StudentDTO;

import com.example.demo8.dto.SubjectDTO;
import com.example.demo8.entities.Clazz;
import com.example.demo8.entities.Student;
import com.example.demo8.entities.Subject;
import com.example.demo8.mapper.ClazzMapper;
import com.example.demo8.mapper.StudentMapper;
import com.example.demo8.mapper.SubjectMapper;
import com.example.demo8.projection.IAvgPoint;
import com.example.demo8.projection.IStudentPoint;
import com.example.demo8.repository.ClazzRepository;
import com.example.demo8.repository.StudentRepository;
import com.example.demo8.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentMapper studentMapper;
    private final ClazzMapper clazzMapper;
    private final SubjectMapper subjectMapper;
    private final ClazzRepository clazzRepository;


    public List<StudentDTO> findAll() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getFirstName() + " " + student.getLastName())
                        .address(student.getAddress())
                        .clazz(clazzMapper.toDto(student.getClazz()))
                        .subjects(subjectMapper.toDto(student.getSubjects()))
                        .build())
                .collect(Collectors.toList());
        return studentDTOList;
    }

    public List<SubjectDTO> getAllSubject(int id) {
        List<Subject> subjects = subjectRepository.findAll();
        return subjectMapper.toDto(subjects);
    }

    public StudentDTO findById(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return studentMapper.toDto(student);
    }

    public StudentDTO getStudentById(int id) {
        return findById(id);
    }


    public List<StudentDTO> findByAddress(String address) {
        List<Student> students = studentRepository.findByAddress(address);
        return studentMapper.toDto(students);
    }

    public List<StudentDTO> findHighestPoint() {
        List<StudentDTO> studentDTOList = studentRepository.findHighestPoint()
                .stream()
                .map(student -> {
                    StudentDTO dto = studentMapper.toDto(student);
//                    dto.setClazz(clazzMapper.toDto(student.getClazz()));
                    return dto;
                })
                .collect(Collectors.toList());
        return studentDTOList;
    }


    public List<StudentDTO> findPointGreaterThan5() {
        List<Student> studentList = studentRepository.findPointGreaterThan5();
        List<StudentDTO> studentDTOList = studentList.stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getFirstName() + " " + student.getLastName())
//                        .clazz(clazzMapper.toDto(student.getClazz()))
                        .address(student.getAddress())
                        .build())
                .collect(Collectors.toList());
        return studentDTOList;
    }


    public String saveStudent(StudentDTO studentDTO) {
        Clazz clazz = clazzMapper.toEntity(studentDTO.getClazz());
        clazz = clazzRepository.save(clazz);
        Student student = studentMapper.toEntity(studentDTO);
        student.setClazz(clazz);
        studentRepository.save(student);
        return "Thêm thành công";
//        Student student = studentMapper.toEntity(studentDTO);
//        studentRepository.save(student);
//        return "Thêm thành công";
    }


    public String updateStudent(int id, StudentDTO studentDTO) {
        // Kiểm tra xem sinh viên có tồn tại không
        boolean existsStudent = studentRepository.existsById(id);
        // Tìm kiếm lớp theo ID từ StudentDTO, hoặc tạo một đối tượng Clazz mới nếu không tìm thấy
        Clazz clazz = clazzRepository.findById(studentDTO.getClazz().getId()).orElse(new Clazz());
        if (!existsStudent) {
            return "Không có sinh viên có id = " + id;
        }

        if (clazz.getId() == null) {
            return "Không có lớp có id =" + id;
        }
        // Tách chuỗi tên thành họ và tên
        String[] strings = studentDTO.getName().split(" ");
        // Nếu chuỗi tên không chứa ít nhất 2 phần, trả về thông báo lỗi
        if (strings.length < 2) {
            return "Tên không đúng yêu cầu";
        }
        Student student = new Student();
        student.setId(id);
        student.setLastName(strings[0]);
        student.setFirstName(strings[1]);
        student.setAddress(studentDTO.getAddress());
        student.setClazz(clazz);
        student.setPoint(studentDTO.getPoint());
        studentRepository.save(student);
        return "cập nhật thành công";
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<IStudentPoint> findStudentById2() {
        List<IStudentPoint> iStudentPoints = studentRepository.findStudentById2();
        if (iStudentPoints == null) {
            return null;
        }
        return iStudentPoints;
    }

    public List<IStudentPoint> findStudentById3(int id) {
        List<IStudentPoint> iStudentPoints = studentRepository.findStudentById3(id);
        if (iStudentPoints == null) {
            return null;
        }
        return iStudentPoints;
    }

    public List<SubjectDTO> getAllSubject() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjectMapper.toDto(subjects);
    }
    public void save(StudentDTO studentDTO){
        Student student = studentMapper.toEntity(studentDTO);
        studentRepository.save(student);
    }

    public StudentDTO groupBySubject(int id) {
        List<IStudentPoint> iStudentPoints = studentRepository.groupSubject(id);
        StudentDTO dto = new StudentDTO();
        if (!iStudentPoints.isEmpty()) {
            dto.setId(iStudentPoints.get(0).getId());
            dto.setName(iStudentPoints.get(0).getName());
//            dto.setClazz(iStudentPoints.get(0).getClazz());
            dto.setAddress(iStudentPoints.get(0).getAddress());
            List<SubjectDTO> subjectDTOs = new ArrayList<>();
            iStudentPoints.forEach(item -> {
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setId(item.getId());
                subjectDTO.setName(item.getSubjectName());
                subjectDTO.setPoint(item.getPoint());
                subjectDTOs.add(subjectDTO);
            });

            dto.setSubjects(subjectDTOs);
        }
        return dto;
    }

    public List<IAvgPoint> findAvgPoint(int id) {
        return studentRepository.findAvgPoint(id);
    }
}

