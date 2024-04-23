package com.post.harvust.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ImageApiResponse {
    String category;
    List<String> images = new ArrayList<>();
}
