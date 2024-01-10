package com.hotel.controller;

import com.hotel.utils.Result;
import com.hotel.Facility;
import com.hotel.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public Result list(){
        List<Facility> facilityList = facilityService.list();
        return Result.success(facilityList);
    }

    @DeleteMapping("/{facilityId}")
    public Result delete(@PathVariable Integer facilityId){
        facilityService.delete(facilityId);
        return Result.success();
    }

    @PutMapping
    public Result updateFacility(@RequestBody Facility facility){
        facilityService.update(facility);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Facility facility){
        facilityService.insert(facility);
        return Result.success();
    }

    @GetMapping("/{facilityId}")
    public Result select(@PathVariable Integer facilityId){
        Facility facility = facilityService.select(facilityId);
        return Result.success(facility);
    }
}