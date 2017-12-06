package com.inovus.repositories;

import com.inovus.models.PagesStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PagesStatisticRepository extends JpaRepository <PagesStatistic, Integer> {

    @Override
    List<PagesStatistic> findAll();

    PagesStatistic findByPage(String page);
}
