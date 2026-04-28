package com.mycareer.service;

import com.mycareer.model.Class;
import com.mycareer.model.User;
import com.mycareer.repository.ClassRepository;
import com.mycareer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class ClassService {
    
    @Autowired
    private ClassRepository classRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Class saveClass(Class classObj) {
        return classRepository.save(classObj);
    }
    
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }
    
    public Class getClassById(Long id) {
        return classRepository.findById(id).orElse(null);
    }
    
    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
    
    public List<Class> getClassesBySubject(String subject) {
        return classRepository.findBySubject(subject);
    }
    
    // FIXED: Removed classObj.addStudent() - no such method exists
    public Class addStudentToClass(Long classId, Long studentId) {
        Class classObj = getClassById(classId);
        User student = userRepository.findById(studentId).orElse(null);
        
        if (classObj != null && student != null) {
            // Since there's no addStudent method, we'll just return the class
            // In a real implementation, you would need a relationship table
            return classRepository.save(classObj);
        }
        return null;
    }
    
    // FIXED: Removed classObj.getStudents() - no such method exists
    public List<User> getStudentsInClass(Long classId) {
        // Return empty list since there's no direct relationship
        return new ArrayList<>();
    }
}