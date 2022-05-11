package com.test.sprinboot_jpa.bin_qilish;

import com.test.sprinboot_jpa.Talaba_malumotlari.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentBeen extends JpaRepository<Student, Integer> {
     //List<Student> findAllByGuruhId(Integer id);
     //boolean existsByTel_raqami(String telRaqami);
}
