package com.ecobazar.main.ecommerce.repositories;

import com.ecobazar.main.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositry extends JpaRepository<Category, Long> {
    Category findByName(String name);

    @Query(
            "SELECT c FROM Category c WHERE c.name = :name AND c.parentCategory.name = :parentCategoryName"
    )
    Category findByNameAndParent(@Param("name") String name , @Param("parentCategoryName") String parentCategoryName);

}
