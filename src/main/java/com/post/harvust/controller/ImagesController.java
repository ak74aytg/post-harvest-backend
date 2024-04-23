package com.post.harvust.controller;

import com.post.harvust.entity.CropImages;
import com.post.harvust.payload.ApiResponse;
import com.post.harvust.payload.ImageApiResponse;
import com.post.harvust.service.CropImageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class ImagesController {
    @Autowired
    CropImageService cropImageService;
    @Value("${project.images}")
    private String path;


    @PostMapping("api/images/upload")
    public ResponseEntity<ApiResponse> uploadImageHandler(
            @RequestParam("image")MultipartFile image,
            @RequestParam("category") String category){
        try {
            cropImageService.uploadImages(path, image, category);
            ApiResponse response = ApiResponse
                    .builder()
                    .message("image uploaded succesfully")
                    .status(HttpStatus.CREATED)
                    .success(true)
                    .build();
            return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
        }catch (IOException ex){
            ApiResponse response = ApiResponse
                    .builder()
                    .message("No image found!")
                    .status(HttpStatus.NOT_FOUND)
                    .success(false)
                    .build();
            return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            ApiResponse response = ApiResponse
                    .builder()
                    .message("Internal Server Error")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .success(false)
                    .build();
            return new ResponseEntity<ApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/api/images")
    public ResponseEntity<List<ImageApiResponse>> GetImagesHandler(){
        List<ImageApiResponse> images = cropImageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/api/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = cropImageService.getResources(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
