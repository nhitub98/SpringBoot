package com.example.demoproject.repository;

import com.example.demoproject.entities.Category;
import com.example.demoproject.projection.ICategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

//    @Query(value = "SELECT id, idparent, name, notes, icon, created_date, updated_date, created_by, updated_by, isactive " +
//            "FROM category " +
//            "WHERE ID = :id AND IDPARENT IS NULL", nativeQuery = true)
//   Optional<Category> findCategoryByIdParent(int id);
//
//    @Query(value = "SELECT id, idparent, name, notes, icon, created_date, updated_date, created_by, updated_by, isactive\n" +
//            "FROM category\n" +
//            "WHERE IDPARENT IS NULL;", nativeQuery = true)
//    List<Category> findAllCategory();


}



