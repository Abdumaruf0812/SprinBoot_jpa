package com.test.sprinboot_jpa.controller;


import com.test.sprinboot_jpa.Talaba_malumotlari.Manzil;
import com.test.sprinboot_jpa.Talaba_malumotlari.Unversitet;
import com.test.sprinboot_jpa.bin_qilish.Manzil_been;
import com.test.sprinboot_jpa.bin_qilish.Unverset_been;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Manzil_controller {

    @Autowired
    Manzil_been manzil_been;

    @RequestMapping(value = "/manzil" , method = RequestMethod.POST)
    public String malumot_joylash(@RequestBody Manzil manzil){

        manzil_been.save(manzil);

        return "Malumot joylandi";
    }

}
