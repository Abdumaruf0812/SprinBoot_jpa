package com.test.sprinboot_jpa.controller;


import com.test.sprinboot_jpa.DTO.GuruhDto;
import com.test.sprinboot_jpa.Talaba_malumotlari.Fakulteti;
import com.test.sprinboot_jpa.Talaba_malumotlari.Guruh;
import com.test.sprinboot_jpa.bin_qilish.Fakultet_been;
import com.test.sprinboot_jpa.bin_qilish.Guruh_been;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GuruhController {

    @Autowired
    Guruh_been guruh_been;

    @Autowired
    Fakultet_been fakultet_been;

    //  Card placement

    @RequestMapping(value = "/guruh" , method = RequestMethod.POST)
    public String guruhQoshish(@RequestBody GuruhDto guruhDto){
        Optional<Fakulteti> fakultetiOptional = fakultet_been.findById(guruhDto.getFakultetId());
        if(!fakultetiOptional.isPresent()){
            return "Bu fakultet topilmadi";
        }
        List<Guruh> guruhList = guruh_been.findAll();
        for(Guruh i : guruhList){
            if(i.getGuruhNomi().equals(guruhDto.getGuruhNomi())){
                return "Bu guruh mavjud";
            }
        }
        Guruh guruh = new Guruh();
        guruh.setGuruhNomi(guruhDto.getGuruhNomi());
        guruh.setFakulteti(fakultetiOptional.get());
        guruh_been.save(guruh);
        return "Malumot joylandi";
    }

    // UPDATE

    @RequestMapping(value = "/guruh/{id}" , method = RequestMethod.PUT)
    public String updateGuruh(@PathVariable Integer id , @RequestBody GuruhDto guruhDto){
        Optional<Guruh> optionalGuruh = guruh_been.findById(id);
        if(optionalGuruh.isPresent()){
            List<Guruh> guruhList = guruh_been.findAll();
            for(Guruh i : guruhList){
                if(i.getGuruhNomi().equals(guruhDto.getGuruhNomi())){
                    return "Bunday guruh mavjud";
                }
            }
            Guruh guruh = optionalGuruh.get();
            guruh.setGuruhNomi(guruhDto.getGuruhNomi());
            guruh_been.save(guruh);
            return "Malumot o'zgartirildi";
        }
        return "Bu ID dagi guruh toplimadi";
    }

    // DELETE

    @RequestMapping(value = "/guruh/{id}" , method = RequestMethod.DELETE)
    public String deleteGuruh(@PathVariable Integer id){
        Optional<Guruh> optionalGuruh = guruh_been.findById(id);
        if(optionalGuruh.isPresent()){
            guruh_been.deleteById(id);
            fakultet_been.deleteById(id);
            return "Malumot ochirildi";
        }
        return "Malumot topilmadi";
    }


    // MALUMOT OLISH

    @RequestMapping (value = "/guruh/{id}", method = RequestMethod.GET)
    public List<Guruh> malumot_olish(@PathVariable Integer id){
        List<Guruh> guruhList = guruh_been.findAllByFakultetiId(id);
        return guruhList;
    }
}
