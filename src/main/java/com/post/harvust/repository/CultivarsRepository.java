package com.post.harvust.repository;

import com.post.harvust.entity.Cultivars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultivarsRepository extends JpaRepository<Cultivars, Long> {
    @Query("SELECT DISTINCT type FROM Cultivars")
    List<String> distictType();
    @Query("SELECT DISTINCT name FROM Cultivars")
    List<String> distictName();
}
