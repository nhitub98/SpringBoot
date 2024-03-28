package com.example.demo.demo6.service;

import com.example.demo.demo6.dto.StudentDTO;
import com.example.demo.demo6.dto.StudentSubjectDTO;
import com.example.demo.demo6.dto.SubjectDTO;
import com.example.demo.demo6.entities.Clazz;
import com.example.demo.demo6.entities.Student;
import com.example.demo.demo6.entities.Subject;
import com.example.demo.demo6.mapper.ClazzMapper;
import com.example.demo.demo6.mapper.StudentMapper;
import com.example.demo.demo6.mapper.SubjectMapper;
import com.example.demo.demo6.projection.IAvgPoint;
import com.example.demo.demo6.projection.IStudentPoint;
import com.example.demo.demo6.repository.ClazzRepository;
import com.example.demo.demo6.repository.StudentRepository;
import com.example.demo.demo6.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
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
                    dto.setClazz(clazzMapper.toDto(student.getClazz()));
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
                        .clazz(clazzMapper.toDto(student.getClazz()))
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
    }

    public String updateStudent(int id, StudentDTO studentDTO) {
        boolean existsStudent = studentRepository.existsById(id);
        if (!existsStudent) return "Không có sinh viên có id = " + id;
        Student student = new Student();
        student.setId(id);
        String[] chuoi = studentDTO.getName().split(" ");
        String firstName = chuoi[0];
        String lastName = "";
        if (chuoi.length > 1) {
            lastName = chuoi[chuoi.length - 1];
        }
        student.setLastName(lastName);
        student.setAddress(studentDTO.getAddress());
        Clazz clazz = clazzMapper.toEntity(studentDTO.getClazz());
        student.setClazz(clazz);
        studentRepository.save(student);
        return "cập nhật thành công";
    }

    // public String update(int id, Student DTO studentDTO)
//    boolean existsStudent = studentRepository.existsById(id);
//    Clazz clazz= clazzRepository.findById(studentDTO.getClazz().getId()).orElse(new Clazz());
//    if(!exitsStudent) return "Không có sinh viên có id =" +id;
//    if(clazz.getId()==null) return "Không có lớp có id=" +id;
//    String [] strings = studentDTO.getName().split("");
//    if(strings.length<2) return "Tên không đúng yêu cầu";
//    Student student= new Student();
//    student.setId();
//    student.setAddress(studentDTO.getAddress());
//    student.setClazz(clazz);
//    studentRepository.save(student);
//        return "Cập nhật thành công";
//}
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

    public StudentDTO groupBySubject(int id) {
        List<IStudentPoint> iStudentPoints = studentRepository.groupSubject(id);
        StudentDTO dto = new StudentDTO();
        if (!iStudentPoints.isEmpty()) {
            dto.setName(iStudentPoints.get(0).getName());
            dto.setAddress(iStudentPoints.get(0).getAddress());
            dto.setClazz(iStudentPoints.get(0).getClazzName());
            List<SubjectDTO> subjectDTOs = new ArrayList<>();
            List<StudentSubjectDTO> studentSubjectDTOs = new ArrayList<>();

            iStudentPoints.forEach(item -> {
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setName(item.getSubjectName());
                subjectDTOs.add(subjectDTO);
                StudentSubjectDTO studentSubjectDTO = new StudentSubjectDTO();
                studentSubjectDTO.setPoint(item.getPoint());
                studentSubjectDTOs.add(studentSubjectDTO);
            });

            dto.setSubjects(subjectDTOs);

        }
        return dto;
    }

    public List<IAvgPoint> findAvgPoint(int id) {
        return studentRepository.findAvgPoint(id);
    }
}





















