package com.example.controller;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.FileUploadService;

public class ImageController {
	@Inject
	FileUploadService fileUploadService;

	@RequestMapping("/upload")
	public String upload(Model model, @RequestParam("email") String email, @RequestParam("file1") MultipartFile file) {

		String url = fileUploadService.restore(file);
		model.addAttribute("url", url);
		return "result";
	}
}
