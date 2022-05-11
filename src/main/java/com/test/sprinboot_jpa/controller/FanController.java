package com.test.sprinboot_jpa.controller;

import com.test.sprinboot_jpa.Talaba_malumotlari.Fanlar;
import com.test.sprinboot_jpa.bin_qilish.FanBeen;
import com.test.sprinboot_jpa.model.Ishchi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class FanController {


    @Autowired
    FanBeen fanBeen;

// texirlash
    @RequestMapping(value = "/fanlar" , method = RequestMethod.POST)
    public String malumot_joylash(@RequestBody Fanlar fanlar){

        boolean holat = fanBeen.existsByFannomi(fanlar.getFannomi());
        if(holat){
            return "Bunday fan mavjud";
        }
//        fanBeen.save(fanlar);
//        return "Malumot joylandi";
//        List<Fanlar> fanlarList = fanBeen.findAll();
//        fanBeen.save(fanlar);
//        for(Fanlar fanlar1 : fanlarList){
//            if(fanlar1.getFannomi().equals(fanlar.getFannomi())){
//                return "Bunday fan mavjud";
//            }
//            else{
//                fanBeen.save(fanlar);
//                return "Malumot joylandi";
//            }
//        }
        fanBeen.save(fanlar);
        return "malumot joylandi!";
    }

    @RequestMapping(value = "/fanlar/{id}" , method = RequestMethod.DELETE)
    public String malumot_delete(@PathVariable Integer id){
        Optional<Fanlar> ishchiOptional = fanBeen.findById(id);
        if(ishchiOptional.isPresent()){
            fanBeen.deleteById(id);
            return "malumot ochirildi";
        }
        return "null";
    }

    @RequestMapping(value = "/fanlar/{id}" , method = RequestMethod.PUT)
    public String edit(@PathVariable Integer id , @RequestBody Fanlar fanlar){
        Optional<Fanlar> fanlarOptional = fanBeen.findById(id);
        boolean holat = false;
        if(fanlarOptional.isPresent()){
            Fanlar fanlar1 = fanlarOptional.get();
            fanlar1.setFannomi(fanlar.getFannomi());
            holat = true;
            fanBeen.save(fanlar1);
        }
        return holat? "Malumot yangilandi" : "malumot topilmadi";
    }



    @RequestMapping(value = "/fanlar" , method = RequestMethod.GET)
    public List<Fanlar> ishchi_malumotlar(){

        List<Fanlar> fanlarList  = fanBeen.findAll();

        return fanlarList;
    }

}
