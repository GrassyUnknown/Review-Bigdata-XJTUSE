package com.hotel.service.impl;

import com.hotel.mapper.RoomMapper;
import com.hotel.pojo.Room;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Override
    public List<Room> list() {
        return roomMapper.list();
    }

    @Override
    public void delete(Integer roomId) {
        roomMapper.delete(roomId);
    }

    @Override
    public void update(Room room) {
        roomMapper.update(room);
    }

    @Override
    public void insert(Room room) {
        roomMapper.insert(room);
    }

    @Override
    public Room select(Integer roomId) {
        return roomMapper.select(roomId);
    }
}