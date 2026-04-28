package com.mycareer.controller;

import com.mycareer.model.Assessment;
import com.mycareer.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assessments")
@CrossOrigin(origins = "http://localhost:5173")
public class AssessmentController {
    
    @Autowired
    private AssessmentService assessmentService;
    
    // Create a new assessment
    @PostMapping
    public ResponseEntity<?> createAssessment(@RequestBody Assessment assessment) {
        try {
          
            // Remove this line - Assessment model doesn't have updatedAt
            // assessment.setUpdatedAt(LocalDateTime.now());
            Assessment savedAssessment = assessmentService.saveAssessment(assessment);
            return new ResponseEntity<>(savedAssessment, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create assessment: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    // Get all assessments
    @GetMapping
    public ResponseEntity<List<Assessment>> getAllAssessments() {
        List<Assessment> assessments = assessmentService.getAllAssessments();
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }
    
    // Get assessment by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAssessmentById(@PathVariable Long id) {
        try {
            Assessment assessment = assessmentService.getAssessmentById(id);
            if (assessment != null) {
                return new ResponseEntity<>(assessment, HttpStatus.OK);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Assessment not found with id: " + id);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to retrieve assessment: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Update assessment
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAssessment(@PathVariable Long id, @RequestBody Assessment assessmentDetails) {
        try {
            Assessment existingAssessment = assessmentService.getAssessmentById(id);
            if (existingAssessment == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Assessment not found with id: " + id);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            
            // Update fields - FIXED: Removed setDescription() and setUpdatedAt()
          
            // Remove these lines:
            // existingAssessment.setDescription(assessmentDetails.getDescription());
            // existingAssessment.setUpdatedAt(LocalDateTime.now());
            
            Assessment updatedAssessment = assessmentService.saveAssessment(existingAssessment);
            return new ResponseEntity<>(updatedAssessment, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to update assessment: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    // Delete assessment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssessment(@PathVariable Long id) {
        try {
            Assessment assessment = assessmentService.getAssessmentById(id);
            if (assessment == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Assessment not found with id: " + id);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            
            assessmentService.deleteAssessment(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Assessment deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete assessment: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}