package com.test.sprinboot_jpa.controller;

import com.test.sprinboot_jpa.bin_qilish.Ishxhi_repozts;
import com.test.sprinboot_jpa.model.Ishchi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@RestController
public class Ishchi_controller {

    @Autowired
    Ishxhi_repozts ishxhiRepozts;

    @RequestMapping(value = "/ishchi" , method = RequestMethod.GET)
    public List<Ishchi> ishchi_malumotlar(){

        List<Ishchi> ishchiList  = ishxhiRepozts.findAll();

        return ishchiList;
    }


    @RequestMapping(value = "/ishchi" , method = RequestMethod.POST)
    public String malumot_joylash(@RequestBody Ishchi ishchi){

        ishxhiRepozts.save(ishchi);

        return "Malumot joylandi";
    }


    @RequestMapping(value = "/ishchi/{id}" , method = RequestMethod.GET)
    public Ishchi malumot_olish(@PathVariable Integer id ){

        Optional<Ishchi> ishchiOptional = ishxhiRepozts.findById(id);
        if(ishchiOptional.isPresent()){
            Ishchi ishchi = ishchiOptional.get();
            return ishchi;
        }

        return new Ishchi();

    }


    @RequestMapping(value = "/ishchi/{id}" , method = RequestMethod.DELETE)
    public String malumot_delete(@PathVariable Integer id){
        Optional<Ishchi> ishchiOptional = ishxhiRepozts.findById(id);
        if(ishchiOptional.isPresent()){
            ishxhiRepozts.deleteById(id);
            return "malumot ochirildi";
        }

        return "null";
    }


    @RequestMapping(value = "/ishchi/{id}" , method = RequestMethod.PUT)
    public String edit(@PathVariable Integer id , @RequestBody Ishchi ishchi){
        Optional<Ishchi> ishchiOptional = ishxhiRepozts.findById(id);
        boolean holat = false;
        if(ishchiOptional.isPresent()){
            Ishchi i = ishchiOptional.get();
                i.setFamilyasi(ishchi.getFamilyasi());
                i.setIsm(ishchi.getIsm());
                i.setMaoshi(ishchi.getMaoshi());
                i.setPhonenumber(ishchi.getPhonenumber());
                holat = true;
                 ishxhiRepozts.save(i);

        }
        return holat? "Malumot yangilandi" : "malumot topilmadi";
    }


}
