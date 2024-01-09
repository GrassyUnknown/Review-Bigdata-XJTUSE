package com.hotel.controller;

import com.hotel.utils.Result;
import com.hotel.pojo.Room;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public Result list(){
        List<Room> roomList = roomService.list();
        return Result.success(roomList);
    }

    @DeleteMapping("/{roomId}")
    public Result delete(@PathVariable Integer roomId){
        roomService.delete(roomId);
        return Result.success();
    }

    @PutMapping
    public Result updateRoom(@RequestBody Room room){
        roomService.update(room);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Room room){
        roomService.insert(room);
        return Result.success();
    }

    @GetMapping("/{roomId}")
    public Result select(@PathVariable Integer roomId){
        Room room = roomService.select(roomId);
        return Result.success(room);
    }
}