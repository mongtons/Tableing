package kr.hallym.Table_counting.domain.repository;

import kr.hallym.Table_counting.domain.entity.Output_Cal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;

public interface Output_CalRepository extends JpaRepository<Output_Cal, Long> {
    @Override
    ArrayList<Output_Cal> findAll();
    ArrayList<Output_Cal> findByTimeBetween(String startTime, String endTime);
}
