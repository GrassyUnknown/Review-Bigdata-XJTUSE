package com.hotel.service.impl;

import com.hotel.mapper.RoomorderMapper;
import com.hotel.pojo.Roomorder;
import com.hotel.service.RoomorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomorderServiceImpl implements RoomorderService {
    @Autowired
    private RoomorderMapper roomorderMapper;
    @Override
    public List<Roomorder> list() {
        return roomorderMapper.list();
    }

    @Override
    public void delete(Integer roomorderId) {
        roomorderMapper.delete(roomorderId);
    }

    @Override
    public void insert(Roomorder roomorder) {
        roomorderMapper.insert(roomorder);
    }

    @Override
    public Roomorder select(Integer roomorderId) {
        return roomorderMapper.select(roomorderId);
    }

    @Override
    public Roomorder check(Roomorder roomorder) {
        return roomorderMapper.check(roomorder);
    }

    @Override
    public void update(Roomorder roomorder) {
        roomorderMapper.update(roomorder);
    }
}