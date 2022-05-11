package com.test.sprinboot_jpa.controller;

import com.test.sprinboot_jpa.DTO.DTO;
import com.test.sprinboot_jpa.Talaba_malumotlari.Manzil;
import com.test.sprinboot_jpa.Talaba_malumotlari.Unversitet;
import com.test.sprinboot_jpa.bin_qilish.Manzil_been;
import com.test.sprinboot_jpa.bin_qilish.Unverset_been;
import com.test.sprinboot_jpa.model.Ishchi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class Unverset_controler {

    @Autowired
    Unverset_been unverset_been;

    @Autowired
    Manzil_been manzil_been;

    @RequestMapping(value = "unversitet" , method = RequestMethod.GET)
    public List<Unversitet> malumot_joylash(){

        List<Unversitet> unversitetList = unverset_been.findAll();
        return unversitetList;
    }


    @RequestMapping(value = "/unversitet" , method = RequestMethod.POST)
    public String malumot_joylash(@RequestBody DTO dto){

        Manzil manzil = new Manzil();
        manzil.setViloyati(dto.getViloyati());
        manzil.setKoshasi(dto.getKoshasi());
        manzil.setTumani(dto.getTumani());

        Manzil manzil_save = manzil_been.save(manzil);

        Unversitet unversitet = new Unversitet();

        unversitet.setUnversitet_nomi(dto.getUnversitet_nomi());
        unversitet.setManzil(manzil_save);
        unverset_been.save(unversitet);

        return "Malumot joylandi";
    }

    @RequestMapping(value = "/unversitet/{id}",method = RequestMethod.PUT)
    public String UniversitetUpdate(@RequestBody DTO universitetDTO,@PathVariable Integer id){
        Optional<Unversitet> optionalUnivesitet=unverset_been.findById(id);
        Optional<Manzil> optionalManzil=manzil_been.findById(id);
        if (optionalUnivesitet.isPresent()){
            Unversitet univesitet = optionalUnivesitet.get();
            Manzil manzil = univesitet.getManzil();

            univesitet.setUnversitet_nomi(universitetDTO.getUnversitet_nomi());
            manzil.setViloyati(universitetDTO.getViloyati());
            manzil.setTumani(universitetDTO.getTumani());
            manzil.setKoshasi(universitetDTO.getKoshasi());

            manzil_been.save(manzil);
            unverset_been.save(univesitet);

            return "Malumot Yangilandi!";
        }
        return "Malumot Topilmadi!!!";
    }


//    @RequestMapping(value = "/Unverst/{id}",method = RequestMethod.PUT)
//    public String tahrirlash(@PathVariable Integer id,@RequestBody Unversitet unverst_dto){
////        Optional<Manzil> optionalManzil=manzil_repozitori.findById(id);
//        Optional<Unversitet> optionalUnverst=unverset_been.findById(id);
//        if(optionalUnverst.isPresent()){
//             unverst=optionalUnverst.get();
//            unverst.setNomi(unverst_dto.getNomi());
//            Manzil manzil1=unverst.getManzil();
//            manzil1.setViloyat(unverst_dto.getViloyat());
//            manzil1.setTuman(unverst_dto.getTuman());
//            manzil1.setMahala(unverst_dto.getMahala());
//            manzil_repozitori.save(manzil1);
//            unversitet_repazitari.save(unverst);
//            return "moffaqiyatli o'zgartirildi";
//
//        }
//        return "Malumot topilmadi";
//    }

}
