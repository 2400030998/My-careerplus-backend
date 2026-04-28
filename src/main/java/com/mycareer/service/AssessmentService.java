package com.mycareer.service;

import com.mycareer.model.Assessment;
import com.mycareer.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssessmentService {
    
    @Autowired
    private AssessmentRepository assessmentRepository;
    
    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }
    
    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }
    
    public Assessment getAssessmentById(Long id) {
        return assessmentRepository.findById(id).orElse(null);
    }
    
    public void deleteAssessment(Long id) {
        assessmentRepository.deleteById(id);
    }
}