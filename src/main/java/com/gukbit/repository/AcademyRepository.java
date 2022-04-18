package com.gukbit.repository;

import com.gukbit.domain.Academy;
import com.gukbit.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {

    Academy findByCode(String code);

}
