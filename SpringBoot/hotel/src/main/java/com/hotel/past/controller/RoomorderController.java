package com.hotel.controller;

import com.hotel.utils.Result;
import com.hotel.pojo.Roomorder;
import com.hotel.service.RoomorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/roomorder")
public class RoomorderController {
    @Autowired
    private RoomorderService roomorderService;

    @GetMapping
    public Result list(){
        List<Roomorder> roomorderList = roomorderService.list();
        return Result.success(roomorderList);
    }

    @DeleteMapping("/{roomorderId}")
    public Result delete(@PathVariable Integer roomorderId){
        roomorderService.delete(roomorderId);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Roomorder roomorder){
        Roomorder r = roomorderService.check(roomorder);
        if(r == null){
            roomorderService.insert(roomorder);
            return Result.success();
        }else {
            return Result.error("订房失败");
        }
    }

    @PutMapping
    public Result update(@RequestBody Roomorder roomorder){
        roomorderService.update(roomorder);
        return Result.success();
    }

    @GetMapping("/{roomorderId}")
    public Result select(@PathVariable Integer roomorderId){
        Roomorder roomorder = roomorderService.select(roomorderId);
        return Result.success(roomorder);
    }
}