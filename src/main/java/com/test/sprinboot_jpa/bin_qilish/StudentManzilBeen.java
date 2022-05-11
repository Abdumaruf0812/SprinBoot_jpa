package com.test.sprinboot_jpa.bin_qilish;

import com.test.sprinboot_jpa.Talaba_malumotlari.StudentManzil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentManzilBeen extends JpaRepository<StudentManzil , Integer> {
}
