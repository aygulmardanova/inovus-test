package com.inovus.services.interfaces;

import com.inovus.models.PagesStatistic;

public interface PagesStatisticService {

    PagesStatistic getByPage(String page);

    int getCountByPage(String page);

    void updateCountByPage(String page);

    void savePage(String page);

}
