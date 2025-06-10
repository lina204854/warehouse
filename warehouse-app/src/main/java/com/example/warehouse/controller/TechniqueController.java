package com.example.warehouse.controller;

import com.example.warehouse.entity.Technique;
import com.example.warehouse.repository.TechniqueRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TechniqueController {

    @Autowired
    private TechniqueRepository techniqueRepository;

    @GetMapping("/techniques")
    public String listTechniques(Model model,
                                 @RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(value = "sort", defaultValue = "importDate") String sortField) {
        List<Technique> techniques;
        if (keyword != null && !keyword.trim().isEmpty()) {
            techniques = techniqueRepository.findByTechniqueTypeContainingIgnoreCaseOrTechniqueGroupContainingIgnoreCaseOrDriverNameContainingIgnoreCase(keyword, keyword, keyword);
            model.addAttribute("keyword", keyword);
        } else {
            Sort.Direction direction = sortField.endsWith(",desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            String property = sortField.split(",")[0];
            techniques = techniqueRepository.findAll(Sort.by(direction, property));
        }
        model.addAttribute("techniques", techniques);
        model.addAttribute("sortField", sortField);
        return "technique-list";
    }

    @GetMapping("/techniques/new")
    public String showCreateForm(Model model) {
        model.addAttribute("technique", new Technique());
        return "technique-form";
    }

    @PostMapping("/techniques/save")
    public String saveTechnique(@Valid @ModelAttribute("technique") Technique technique, BindingResult result) {
        if (result.hasErrors()) {
            return "technique-form";
        }
        techniqueRepository.save(technique);
        return "redirect:/techniques";
    }

    @GetMapping("/techniques/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Technique technique = techniqueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid technique Id:" + id));
        model.addAttribute("technique", technique);
        return "technique-form";
    }

    @GetMapping("/techniques/delete/{id}")
    public String deleteTechnique(@PathVariable("id") Long id) {
        techniqueRepository.deleteById(id);
        return "redirect:/techniques";
    }

    @GetMapping("/api/techniques/shipped-stats")
    @ResponseBody
    public List<Map<String, Object>> getShippedStats() {
        return techniqueRepository.countShippedTechniquesByDay().stream()
                .map(record -> Map.of("exportDate", record[0], "techniqueCount", record[1]))
                .collect(Collectors.toList());
    }
}
