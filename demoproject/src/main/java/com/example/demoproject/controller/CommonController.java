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
    // Hiển thị trang index khi truy cập vào đường dẫn gốc của ứng dụng
    @GetMapping("")
    public String showIndex() {
        return "layout/index";
    }

    // Đường dẫn đến thư mục lưu trữ các tệp ảnh được tải lên
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") +
            "/src/main/resources/static/img";

    // Hiển thị form tải lên ảnh và URL ảnh mặc định
    @GetMapping("/uploadimage")
    public String displayUploadForm(Model model) {
        model.addAttribute("msg", ""); // Thêm một thuộc tính msg vào model với giá trị rỗng
        model.addAttribute("url", "https://www.concretepage.com/images/favicon.png"); // Thêm một thuộc tính url vào model với một URL ảnh mặc định
        return "layout/upload"; // Trả về tên của file view để hiển thị form tải lên ảnh
    }

    // Xử lý việc tải lên ảnh từ form và hiển thị thông báo tải lên
    @PostMapping("/upload")
    public String uploadImage(Model model,
                              @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder(); // Tạo một StringBuilder để lưu trữ tên tệp đã tải lên
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename()); // Tạo một Path tới tệp được tải lên
        fileNames.append(file.getOriginalFilename()); // Thêm tên tệp đã tải lên vào StringBuilder

        Files.write(fileNameAndPath, file.getBytes()); // Lưu trữ tệp đã tải lên vào đường dẫn đã chỉ định

        String url = "/img/" + fileNames.toString(); // Tạo URL đến tệp đã tải lên

        model.addAttribute("msg", "Uploaded images: " + fileNames.toString()); // Thêm thông báo tải lên vào model
        model.addAttribute("url", url); // Thêm URL của ảnh đã tải lên vào model
        return "layout/upload"; // Trả về tên của file view để hiển thị thông báo tải lên và form tải lên ảnh
    }
}
