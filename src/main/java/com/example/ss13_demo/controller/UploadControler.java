package com.example.ss13_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadControler {
    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    @RequestMapping("save-file")
    public void save(@RequestParam("img") MultipartFile fileImage, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("uploads/images");
        String fileName = fileImage.getOriginalFilename();
        System.out.println(fileName);

        File destination = new File(path + "/" + fileName);
        try {
            fileImage.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}