package com.FindMyPc.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @GetMapping("/list/{productName}")
    public ResponseEntity<List<String>> listImages(@PathVariable String productName) {
        File folder = new File("src/main/resources/static/img/" + productName);
        File[] files = folder.listFiles();
        List<String> imageFiles = Arrays.stream(files)
                                        .filter(file -> !file.isDirectory())
                                        .map(file -> "/assets/img/" + productName + "/" + file.getName())
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(imageFiles);
    }
}
