package com.example.warehouse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "techniques")
public class Technique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Вид техники не может быть пустым")
    private String techniqueType;

    private String techniqueGroup;

    @NotNull(message = "Дата ввоза не может быть пустой")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate importDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate exportDate;
    
    private String driverName;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTechniqueType() { return techniqueType; }
    public void setTechniqueType(String techniqueType) { this.techniqueType = techniqueType; }
    public String getTechniqueGroup() { return techniqueGroup; }
    public void setTechniqueGroup(String techniqueGroup) { this.techniqueGroup = techniqueGroup; }
    public LocalDate getImportDate() { return importDate; }
    public void setImportDate(LocalDate importDate) { this.importDate = importDate; }
    public LocalDate getExportDate() { return exportDate; }
    public void setExportDate(LocalDate exportDate) { this.exportDate = exportDate; }
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
}
