package com.hotel.controller;

import com.hotel.pojo.Facility;
import com.hotel.utils.Result;
import com.hotel.pojo.Facilityorder;
import com.hotel.service.FacilityorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/facilityorder")
public class FacilityorderController {
    @Autowired
    private FacilityorderService facilityorderService;

    @GetMapping
    public Result list(){
        List<Facilityorder> facilityorderList = facilityorderService.list();
        return Result.success(facilityorderList);
    }

    @DeleteMapping("/{facilityorderId}")
    public Result delete(@PathVariable Integer facilityorderId){
        facilityorderService.delete(facilityorderId);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Facilityorder facilityorder){
        Facility f = facilityorderService.check(facilityorder);
        if(f.getFacilityCapacity() > f.getFacilityNow()){
        facilityorderService.insert(facilityorder);
        return Result.success();
        }else return Result.error("设施已满");
    }

    @GetMapping("/{facilityorderId}")
    public Result select(@PathVariable Integer facilityorderId){
        Facilityorder facilityorder = facilityorderService.select(facilityorderId);
        return Result.success(facilityorder);
    }
}