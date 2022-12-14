package com.student.project.controller;

import com.student.project.model.Student;
import com.student.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent( @RequestBody Student student){
        Integer id = service.saveStudent(student);
        return  new ResponseEntity<String>("Mahasiswa dengan id '"+id+"' berhasil disimpan", HttpStatus.OK);

    }
    @GetMapping("/list")
    public ResponseEntity<List<Student>> getAllStudentDetails(){
        List<Student> list = service.getAllStudents();
        return new ResponseEntity<List<Student>>(list,HttpStatus.OK);
    }

    @GetMapping("/get/{sno}")
    public ResponseEntity<Student> getStudentById( @PathVariable("sno")  Integer sno){
        Student std = service.getStudentById(sno);
        return new ResponseEntity<Student>(std,HttpStatus.OK);
    }
    @PutMapping("/update/{sno}")
    public ResponseEntity<String> updateStudent( @PathVariable("sno") Integer sno,  @RequestBody Student student){
        Student stdFromDb = service.getStudentById(sno);
        stdFromDb.setStdName(student.getStdName());
        stdFromDb.setStdClass(student.getStdClass());
        stdFromDb.setSchoolName(student.getSchoolName());
        Integer id = service.saveStudent(stdFromDb);
        return new ResponseEntity<String>("Mahasiswa dengan id '"+id+"' berhasil diupdate",HttpStatus.OK);

    }
    @DeleteMapping("delete/{sno}")
    public ResponseEntity<String> deleteStudent(@PathVariable("sno") Integer sno){
        service.deleteStudent(sno);
        return new ResponseEntity<String>("Mahasiswa dengan id '"+sno+"' berhasil dihapus",HttpStatus.OK);
    }
}
