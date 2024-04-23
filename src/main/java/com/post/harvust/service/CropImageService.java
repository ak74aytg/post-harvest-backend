package com.post.harvust.service;

import com.post.harvust.entity.CropImages;
import com.post.harvust.payload.ImageApiResponse;
import com.post.harvust.repository.CropImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CropImageService {
    @Autowired
    CropImagesRepository cropImagesRepository;
    public String uploadImages(String path, MultipartFile file, String category) throws IOException {
        String name = file.getOriginalFilename() + UUID.randomUUID().toString();
        String filePath = path+ File.separator + name;
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        CropImages newCropImage = new CropImages();
        newCropImage.setCategory(category);
        newCropImage.setImage(name);
        cropImagesRepository.save(newCropImage);
        return name;
    }



    public List<ImageApiResponse> getAllImages(){
        List<String> categories = cropImagesRepository.findDistinctCategories();
        List<ImageApiResponse> apiResponse = new ArrayList<>();
        for(String category : categories){
            ImageApiResponse response = new ImageApiResponse();
            response.setCategory(category);
            List<CropImages> imageList = cropImagesRepository.findByCategory(category);
            for(CropImages img : imageList){
                response.getImages().add(img.getImage());
            }
            apiResponse.add(response);
        }
        return apiResponse;
    }

    public InputStream getResources(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream io = new FileInputStream(fullPath);
        return io;
    }
}
