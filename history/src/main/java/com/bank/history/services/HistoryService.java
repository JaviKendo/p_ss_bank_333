package com.bank.history.services;

import com.bank.history.models.History;

import java.util.List;

public interface HistoryService {
    History saveHistory(History history);

    History findOne(Long id);

    void update(History history);

    List<History> findAll();

    void deleteHistoryById(Long id);
}
