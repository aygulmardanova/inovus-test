package com.inovus.services;

import com.inovus.models.PagesStatistic;
import com.inovus.repositories.PagesStatisticRepository;
import com.inovus.services.interfaces.PagesStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagesStatisticServiceImpl implements PagesStatisticService {

    @Autowired
    PagesStatisticRepository pagesStatisticRepository;

    @Override
    public PagesStatistic getByPage(String page) {
        System.out.println("rep: " + pagesStatisticRepository.findByPage(page));
        System.out.println("rep count: " + pagesStatisticRepository.findByPage(page).getCount());
        return pagesStatisticRepository.findByPage(page);
    }

    @Override
    public int getCountByPage(String page) {
        return getByPage(page).getCount();
    }

    @Override
    public void updateCountByPage(String page) {
        PagesStatistic pagesStatistic = pagesStatisticRepository.findByPage(page);
        if (pagesStatistic != null) {
            pagesStatistic.setCount(pagesStatistic.getCount() + 1);
            pagesStatisticRepository.save(pagesStatistic);
        } else {
            savePage(page);
        }
    }

    @Override
    public void savePage(String page) {
        PagesStatistic pagesStatistic = new PagesStatistic(page, 1);
        pagesStatisticRepository.save(pagesStatistic);
    }
}
