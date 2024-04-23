package com.post.harvust.repository;

import com.post.harvust.entity.CropImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropImagesRepository extends JpaRepository<CropImages, Integer> {
    public List<CropImages> findByCategory(String category);
    @Query("SELECT DISTINCT category FROM CropImages")
    public List<String> findDistinctCategories();
}
