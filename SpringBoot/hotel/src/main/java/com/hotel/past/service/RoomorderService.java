package com.hotel.service;

import com.hotel.pojo.Roomorder;

import java.util.List;

public interface RoomorderService {
    List<Roomorder> list();

    void delete(Integer roomorderId);

    void insert(Roomorder roomorder);

    Roomorder select(Integer roomorderId);

    Roomorder check(Roomorder roomorder);

    void update(Roomorder roomorder);
}