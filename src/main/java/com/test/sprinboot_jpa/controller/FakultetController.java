package com.test.sprinboot_jpa.controller;


import com.test.sprinboot_jpa.DTO.Fakultet_dto;
import com.test.sprinboot_jpa.Talaba_malumotlari.Fakulteti;
import com.test.sprinboot_jpa.Talaba_malumotlari.Unversitet;
import com.test.sprinboot_jpa.bin_qilish.Fakultet_been;
import com.test.sprinboot_jpa.bin_qilish.Unverset_been;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class FakultetController {

    @Autowired
    Fakultet_been fakultet_been;

    @Autowired
    Unverset_been unverset_been;


    // Malumot joylash

    @RequestMapping(value = "/fakultet" , method = RequestMethod.POST)
    public String fakultet_qoshish(@RequestBody Fakultet_dto fakultet_dto){

//        boolean holat = fakultet_been.existsByFakultetNomiAndUnversitetId(fakultet_dto.getFakultetNomi() , fakultet_dto.getUnversetId());
//        if(holat){
//            return "Bunday fan mavjud";
//        }
        Optional<Unversitet> unversitetOptional = unverset_been.findById(fakultet_dto.getUnversetId());
        if(!unversitetOptional.isPresent()){
            return "bunday unversitet topilmadi!!!";
        }
        List<Fakulteti> fakultetiList = fakultet_been.findAll();
        for(Fakulteti f: fakultetiList){
            if(f.getFakultetNomi().equals(fakultet_dto.getFakultetNomi()) && f.getUnversitet().getId().equals(fakultet_dto.getUnversetId())){
                return "Bu unverseta bu fakultet mavjud";
            }
        }
        Fakulteti fakulteti = new Fakulteti();
        fakulteti.setFakultetNomi(fakultet_dto.getFakultetNomi());
        fakulteti.setUnversitet(unversitetOptional.get());
        fakultet_been.save(fakulteti);
        return "Malumot joylandi";
    }


    //Malumotni tahirlash

    @RequestMapping(value = "/fakultet/{id}",method = RequestMethod.PUT)
    public String tahirlash(@PathVariable Integer id , @RequestBody Fakultet_dto fakultet_dto){
        Optional<Fakulteti> fakultetiOptional = fakultet_been.findById(id);
        if(fakultetiOptional.isPresent()){

        List<Fakulteti> fakultetiList = fakultet_been.findAll();

        for(Fakulteti i : fakultetiList){
            if(i.getFakultetNomi().equals(fakultet_dto.getFakultetNomi())){
                return "Bunday fakultet mavjud!";
            }
        }
            Fakulteti fakulteti = fakultetiOptional.get();
            fakulteti.setFakultetNomi(fakultet_dto.getFakultetNomi());
            fakultet_been.save(fakulteti);

            return "O'zgartirildi";
        }
        return "Malumot topilmadi";
    }



    // DELETE

    @RequestMapping (value = "/fakultet/{id}", method = RequestMethod.DELETE)
    public String deleteFakultet(@PathVariable Integer id){
        Optional<Fakulteti> fakultetiOptional = fakultet_been.findById(id);
        if(fakultetiOptional.isPresent()){
            unverset_been.deleteById(id);
            fakultet_been.deleteById(id);
            return "Malumot o'chirildi!!!";
        }
        return "Malumot topilmadi";
    }

    // Malumot  olish

    @RequestMapping (value = "/fakultet/{id}", method = RequestMethod.GET)
    public List<Fakulteti> malumotOlish(@PathVariable Integer id){
         List<Fakulteti> fakultetiList = fakultet_been.findAllByUnversitetId(id);
         return fakultetiList;
    }
}
