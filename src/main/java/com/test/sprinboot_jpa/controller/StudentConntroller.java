package com.test.sprinboot_jpa.controller;


import com.test.sprinboot_jpa.DTO.StudentDto;
import com.test.sprinboot_jpa.Talaba_malumotlari.*;
import com.test.sprinboot_jpa.bin_qilish.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentConntroller {
    @Autowired
    StudentBeen studentBeen;

    @Autowired
    StudentManzilBeen studentManzilBeen;

    @Autowired
    FanBeen fanBeen;

    @Autowired
    Guruh_been guruhBeen;

    //Malumot o'qish / SELECT

    @RequestMapping(value = "/student" , method = RequestMethod.GET)
    public List<Student> malumotOqish(){

        List<Student> studentList = studentBeen.findAll();
        return studentList;
    }

    // INSERT

    @RequestMapping(value = "/student" , method = RequestMethod.POST)
    public String insertStudent(@RequestBody StudentDto studentDto){
        Optional<Guruh> optionalGuruh = guruhBeen.findById(studentDto.getGuruhId());
        if(!optionalGuruh.isPresent()) return "Bunday guruh yoq!!!";
        List<Fanlar> fanlarList = new ArrayList<>();
        for(Integer i : studentDto.getFanId()){
            if(fanBeen.existsById(i)){
                if(!fanlarList.contains(fanBeen.getById(i)))
                    fanlarList.add(fanBeen.getById(i));
                else
                    return "Bu fan Ikki narta tanlandi";
            }
            else
                return "Bunday fan yoq";
        }

        List<Student> studentList= studentBeen.findAll();
        for(Student i:studentList){
            if(i.getTelRaqami().equals(studentDto.getTelRaqami())){
                return "Bunday telefon nomeri mavjud";
            }
        }

//        if(studentBeen.existsByTel_raqami(studentDto.getTelRaqami()))
//            return "Bunday telefon nomeri mavjud";
//        else {
            StudentManzil studentManzil = new StudentManzil();
            studentManzil.setViloyati(studentDto.getViloyati());
            studentManzil.setTumani(studentDto.getTumani());
            studentManzil.setKoshasi(studentDto.getKoshasi());

            StudentManzil studentManzil1 = studentManzilBeen.save(studentManzil);

            Student student = new Student();
            student.setFamilyasi(studentDto.getFamilyasi());
            student.setIsmi(studentDto.getIsmi());
            student.setTelRaqami(studentDto.getTelRaqami());

            student.setStudentManzil(studentManzil1);

            student.setFanlarList(fanlarList);
            student.setGuruh(optionalGuruh.get());


            studentBeen.save(student);
            return "Malumot joylandi!!!";
        }

//    }







}
