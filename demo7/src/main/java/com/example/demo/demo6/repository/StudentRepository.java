package com.example.demo.demo6.repository;

import com.example.demo.demo6.entities.Student;
import com.example.demo.demo6.projection.IAvgPoint;
import com.example.demo.demo6.projection.IStudentPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// viết query theo native
//    @Query(nativeQuery = true, value = "select * from student " +
//            "where first_name = :name")
//     List<Student> getByName(@Param("name") String Name);

// HQL
//@Query(value = "select s from Student s where  s.firstName like concat('%',:name,'%')")
//    List<Student> getByName(@Param("name") String name);

//    c3
//    List<Student> findAllByFirstName(String name);;
//@Query(value = "select a from Address a where  a.id = :id")
//    List<AddressDto> GetById(@Param("id") Integer id);


public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT s FROM Student s WHERE s.address LIKE CONCAT('%',:address,'%') ")
    List<Student> findByAddress(@Param("address") String address);

    @Query(value = "SELECT s FROM Student s WHERE s.point = (SELECT MAX(s2.point) FROM Student s2)")
    List<Student> findHighestPoint();

    @Query(value = "SELECT s FROM Student s WHERE s.point > 5")
    List<Student> findPointGreaterThan5();


    @Query(value = "select\n" +
            "       concat(s.last_name, '', s.first_name) as name,\n" +
            "       s.address address,\n" +
            "       c.name clazzName,\n" +
            "       s2.name subjectName,\n" +
            "       ss.point point\n" +
            "from student s\n" +
            "left join clazz c on s.id = s.clazz_id=c.id\n" +
            "left join student_subject ss on s.id = ss.id_student\n" +
            "left join subject s2 on s2.id = ss.id_subject\n" +
            "where s.id=6", nativeQuery = true)
    List<IStudentPoint> findStudentById();


    @Query(value = "select\n" +
            "       concat(s.last_name, '', s.first_name) as name,\n" +
            "       s.address address,\n" +
            "       c.name clazzName,\n" +
            "       s2.name subjectName,\n" +
            "       ss.point point\n" +
            "from student s\n" +
            "left join clazz c on s.id = s.clazz_id=c.id\n" +
            "left join student_subject ss on s.id = ss.id_student\n" +
            "left join subject s2 on s2.id = ss.id_subject\n" +
            "where s.id=", nativeQuery = true)
    List<IStudentPoint> findStudentById2();

    @Query(value = "select\n" +
            "       concat(s.last_name, '', s.first_name) as name,\n" +
            "       s.address address,\n" +
            "       c.name clazzName,\n" +
            "       s2.name subjectName,\n" +
            "       ss.point point\n" +
            "from student s\n" +
            "left join clazz c on s.id = s.clazz_id=c.id\n" +
            "left join student_subject ss on s.id = ss.id_student\n" +
            "left join subject s2 on s2.id = ss.id_subject\n" +
            "where s.id= :id", nativeQuery = true)
    List<IStudentPoint> findStudentById3(@Param("id") int id);




    @Query(value = "SELECT\n" +
            "    CONCAT(s.last_name, ' ', s.first_name) AS name,\n" +
            "    s.address AS address,\n" +
            "    c.name AS class,\n" +
            "    IFNULL(AVG(ss.point), 0) AS average_point,\n" +
            "    GROUP_CONCAT(sub.name) AS subjects\n" +
            "FROM\n" +
            "    student s\n" +
            "        LEFT JOIN\n" +
            "    clazz c ON s.clazz_id = c.id\n" +
            "        LEFT JOIN\n" +
            "    student_subject ss ON s.id = ss.id_student\n" +
            "        LEFT JOIN\n" +
            "    subject sub ON ss.id_subject = sub.id\n" +
            "WHERE\n" +
            "        s.id = :id\n" +
            "GROUP BY\n" +
            "    s.id, s.last_name, s.first_name, s.address, c.name;", nativeQuery = true)
    List<IAvgPoint> findAvgPoint(@Param("id") int id);

    @Query(value = "select\n" +
            "       concat(s.last_name, '', s.first_name) as name,\n" +
            "       s.address address,\n" +
            "       c.name clazzName,\n" +
            "       s2.name subjectName,\n" +
            "       ss.point point\n" +
            "from student s\n" +
            "left join clazz c on s.id = s.clazz_id=c.id\n" +
            "left join student_subject ss on s.id = ss.id_student\n" +
            "left join subject s2 on s2.id = ss.id_subject\n" +
            "where s.id= :id", nativeQuery = true)
    List<IStudentPoint> groupSubject(@Param("id") int id);
}




