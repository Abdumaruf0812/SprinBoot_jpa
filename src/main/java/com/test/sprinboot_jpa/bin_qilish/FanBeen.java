package com.test.sprinboot_jpa.bin_qilish;

import com.test.sprinboot_jpa.Talaba_malumotlari.Fanlar;
import com.test.sprinboot_jpa.model.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FanBeen extends JpaRepository<Fanlar, Integer> {
    //boolean existsByFannomi(String fannomi);
    public boolean existsByFannomi(String fannomi);
}
