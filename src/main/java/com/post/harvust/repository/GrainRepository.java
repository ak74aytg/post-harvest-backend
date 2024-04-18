package com.post.harvust.repository;

import com.post.harvust.entity.Cultivars;
import com.post.harvust.entity.GrainQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrainRepository extends JpaRepository<GrainQuality, Long> {
    List<GrainQuality> findByYear(int year);

    List<GrainQuality> findByCultivarsId(long cid);

    List<GrainQuality> findByCultivarsName(String name);

    List<GrainQuality> findByCultivarsType(String type);
    @Query("SELECT DISTINCT year FROM GrainQuality")
    List<Integer> distictYears();
}
