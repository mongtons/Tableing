package kr.hallym.Table_counting.domain.repository;

import kr.hallym.Table_counting.domain.entity.EmbeddableId;
import kr.hallym.Table_counting.domain.entity.Tableing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TableingRepository extends JpaRepository<Tableing, Long> {
    @Override
    ArrayList<Tableing> findAll();
    public ArrayList<Tableing> findByTimeBetween(String startTime, String endTime);
    public ArrayList<Tableing> findByPk(EmbeddableId id);
}
