package com.example.warehouse.repository;

import com.example.warehouse.entity.Technique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TechniqueRepository extends JpaRepository<Technique, Long> {
    
    List<Technique> findByTechniqueTypeContainingIgnoreCaseOrTechniqueGroupContainingIgnoreCaseOrDriverNameContainingIgnoreCase(String type, String group, String driver);

    
    @Query("SELECT t.exportDate, COUNT(t) FROM Technique t WHERE t.exportDate IS NOT NULL GROUP BY t.exportDate ORDER BY t.exportDate ASC")
    List<Object[]> countShippedTechniquesByDay();
}
