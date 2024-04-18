package com.post.harvust.controller;

import com.post.harvust.entity.Cultivars;
import com.post.harvust.entity.GrainQuality;
import com.post.harvust.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CropController {
    @Autowired
    CropService cropService;

    @PostMapping("/api/cultivars")
    public String addCultivars(@RequestBody Cultivars cultivars){
        Cultivars saved = cropService.addCultivars(cultivars);
        if(saved!=null){
            return "saved successfully";
        }
        return "controller: some error occured";
    }

    @GetMapping("/api/cultivars")
    public ResponseEntity<List<Cultivars>> getCultivars(){
        return ResponseEntity.ok(cropService.getCultivars());
    }
    @GetMapping("/api/cultivars/name")
    public ResponseEntity<List<String>> getCultivarsName(){
        return ResponseEntity.ok(cropService.getCultivarsNameList());
    }
    @GetMapping("/api/cultivars/type")
    public ResponseEntity<List<String>> getCultivarsType(){
        return ResponseEntity.ok(cropService.getCultivarsTypeList());
    }


    @PostMapping("/api/grain-quality")
    public String addGrainDetails(@RequestBody GrainQuality grains){
        GrainQuality saved = cropService.addGrainDetails(grains);
        if(saved!=null){
            return "saved successfully";
        }
        return "some error occured";
    }


    @GetMapping("/api/grain-quality")
    public ResponseEntity<?> getGrains(
            @RequestParam(required = false) Long cid,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String cname,
            @RequestParam(required = false) String ctype){

        int count = 0;
        if (cid != null) count++;
        if (year != null) count++;
        if (cname != null) count++;
        if (ctype != null) count++;

        if (count > 1) {
            // More than one parameter is not null, throw an exception
            throw new IllegalArgumentException("More than one parameter is not allowed.");
        }

        List<GrainQuality> list = cropService.getGrains(year, cid, cname, ctype);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/api/grain-quality/years")
    public ResponseEntity<List<Integer>> getYears(){
        return ResponseEntity.ok(cropService.getYearList());
    }
}
