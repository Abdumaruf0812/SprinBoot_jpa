package com.test.sprinboot_jpa.bin_qilish;

import com.test.sprinboot_jpa.Talaba_malumotlari.Guruh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Guruh_been extends JpaRepository<Guruh , Integer> {
    List<Guruh> findAllByFakultetiId(Integer fakultetId);
}
