package com.hotel.service;

import com.hotel.pojo.Room;

import java.util.List;

public interface RoomService {
    List<Room> list();

    void delete(Integer roomId);

    void update(Room room);

    void insert(Room room);

    Room select(Integer roomId);
}