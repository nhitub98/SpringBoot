package com.example.demoproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class CommonController {
    @GetMapping("")
    public String showIndex() {
        return "layout/index";
    }


//    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") +
//            "/src/main/resources/static/img";
//    @GetMapping("/uploadimage")
//    public String displayUploadForm(Model model) {
//        model.addAttribute("msg", "");
//        model.addAttribute("url", "https://www.concretepage.com/images/favicon.png"); // Thêm một thuộc tính url vào model với một URL ảnh mặc định
//        return "layout/upload";
//    }
//    @PostMapping("/upload")
//    public String uploadImage(Model model,
//                              @RequestParam("image") MultipartFile file) throws IOException {
//        StringBuilder fileNames = new StringBuilder();
//        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//        fileNames.append(file.getOriginalFilename());
//        Files.write(fileNameAndPath, file.getBytes());
//        String url = "/img/" + fileNames.toString();
//        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
//        model.addAttribute("url", url);
//        return "layout/upload1";
//    }
}
