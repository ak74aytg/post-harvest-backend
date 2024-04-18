package com.post.harvust.service;

import com.post.harvust.entity.Cultivars;
import com.post.harvust.entity.GrainQuality;
import com.post.harvust.repository.CultivarsRepository;
import com.post.harvust.repository.GrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CropService {
    @Autowired
    CultivarsRepository cultivarsRepository;
    @Autowired
    GrainRepository grainRepository;

    public Cultivars addCultivars(Cultivars cultivars){
        Cultivars saveCultivar = cultivarsRepository.save(cultivars);
        System.out.println(saveCultivar);
        System.out.println("service: saved succesfully");
        return saveCultivar;
    }
    public List<Cultivars> getCultivars(){
        return cultivarsRepository.findAll();
    }
    public List<String> getCultivarsNameList(){
        return cultivarsRepository.distictName();
    }
    public List<String> getCultivarsTypeList(){
        return cultivarsRepository.distictType();
    }

    public GrainQuality addGrainDetails(GrainQuality grain_detail){
        GrainQuality savedGrainDetails = grainRepository.save(grain_detail);
        return savedGrainDetails;
    }


    public List<GrainQuality> getGrains(Integer year, Long cid, String name, String type){
        if (year != null) {
            return this.getGrainDetailsByYear(year);
        } else if (cid != null) {
            return this.getGrainDetailsByCultivarsId(cid);
        }else if(name!=null){
            return getGrainDetailsByCultivarsName(name);
        }else if(type!=null){
            return getGrainDetailsByCultivarsType(type);
        }else {
            return this.getAllGrains();
        }
    }

    public List<GrainQuality> getGrainDetailsByYear(int year){
        return grainRepository.findByYear(year);
    }

    public List<GrainQuality> getGrainDetailsByCultivarsId(long cid){
        return grainRepository.findByCultivarsId(cid);
    }
    public List<GrainQuality> getGrainDetailsByCultivarsName(String name){
        return grainRepository.findByCultivarsName(name);
    }
    public List<GrainQuality> getGrainDetailsByCultivarsType(String type){
        return grainRepository.findByCultivarsType(type);
    }

    public List<GrainQuality> getAllGrains(){
        return grainRepository.findAll();
    }

    public List<Integer> getYearList(){
        return grainRepository.distictYears();
    }
}
