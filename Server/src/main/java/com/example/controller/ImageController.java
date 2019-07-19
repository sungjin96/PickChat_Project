package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.ImageNameSaveService;

@Controller
public class ImageController {

	ImageNameSaveService imageName = ImageNameSaveService.getImageService();
	Api api = new Api();

	@CrossOrigin("*")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String file_upload_save(@RequestParam("uploadfile") MultipartFile uploadfile, ModelMap modelMap) {

		OutputStream out = null;
		PrintWriter printWriter = null;

		try {
			// 파일명 얻기
			String fileName = uploadfile.getOriginalFilename();
			String trueName = genSaveFileName(fileName);
			imageName.setSingleImage(trueName);
			// 파일의 바이트 정보 얻기
			byte[] bytes = uploadfile.getBytes();
			// 파일의 저장 경로 얻기
			String uploadPath = getDestinationLocation() + trueName;

			// 파일 객체 생성
			File file = new File(uploadPath);
			// 상위 폴더 존재 여부 확인

			if (!file.getParentFile().exists()) {
				// 상위 폴더가 존재 하지 않는 경우 상위 폴더 생성
				file.getParentFile().mkdirs();
			}

			// 파일 아웃풋 스트림 생성
			out = new FileOutputStream(file);
			// 파일 아웃풋 스트림에 파일의 바이트 쓰기
			out.write(bytes);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "/file_upload_form";
	}

	private String genSaveFileName(String extName) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

	private String getDestinationLocation() {
		return "/home/hosting_users/sungjin5891/img/";
	}

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping(value = "/upload1", method = RequestMethod.POST)
	public String android(@RequestParam("uploadfile") MultipartFile uploadfile, ModelMap modelMap) throws Exception {
		String uploadPath = "";
		OutputStream out = null;
		PrintWriter printWriter = null;

		String trueName = null;
		// String trueName1 = null;
		

		try {
			// 파일명 얻기
			String fileName = uploadfile.getOriginalFilename();

			trueName = genSaveFileName(fileName);

			if (fileName.equals("checkimg.png")) {
				imageName.setCheckImage(trueName);
			} else {
				trueName += ".png";
				imageName.setMainImage(trueName);
			}
			// 파일의 바이트 정보 얻기
			byte[] bytes = uploadfile.getBytes();

			// 파일의 저장 경로 얻기
			uploadPath = getDestinationLocation() + trueName;
			// 파일 객체 생성
			File file = new File(uploadPath);

			// 상위 폴더 존재 여부 확인
			if (!file.getParentFile().exists()) {
				// 상위 폴더가 존재 하지 않는 경우 상위 폴더 생성
				file.getParentFile().mkdirs();
			}

			// 파일 아웃풋 스트림 생성
			out = new FileOutputStream(file);
			// 파일 아웃풋 스트림에 파일의 바이트 쓰기
			out.write(bytes);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return "http://sungjin5891.cafe24.com/img/" + trueName;
	}

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public int check(ModelMap modelMap) throws Exception {
//		if (api.api(imageName.getCheckImage(), imageName.getMainImage()) == 1) {
//			return "http://sungjin5891.cafe24.com/img/" + imageName.getMainImage() + ".............................//"
//					+ api.api(imageName.getCheckImage(), imageName.getMainImage());
//		} else {
//			return imageName.getMainImage() + ".............." + imageName.getCheckImage()
//					+ ".....error..............................//"
//					+ api.api(imageName.getCheckImage(), imageName.getMainImage());
//		}
		return api.api(imageName.getCheckImage(), imageName.getMainImage());
	}

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping(value = "/upload2", method = RequestMethod.POST)
	public String android2(@RequestParam("uploadfile") MultipartFile uploadfile, ModelMap modelMap) throws Exception {
		String uploadPath = "";
		OutputStream out = null;
		PrintWriter printWriter = null;

		String trueName = null;

		try {
			// 파일명 얻기
			String fileName = uploadfile.getOriginalFilename();

			trueName = genSaveFileName(fileName);
			imageName.setSingleImage(trueName);

			// 파일의 바이트 정보 얻기
			byte[] bytes = uploadfile.getBytes();

			// 파일의 저장 경로 얻기
			uploadPath = getDestinationLocation() + trueName;
			// 파일 객체 생성
			File file = new File(uploadPath);

			// 상위 폴더 존재 여부 확인
			if (!file.getParentFile().exists()) {
				// 상위 폴더가 존재 하지 않는 경우 상위 폴더 생성
				file.getParentFile().mkdirs();
			}

			// 파일 아웃풋 스트림 생성
			out = new FileOutputStream(file);
			// 파일 아웃풋 스트림에 파일의 바이트 쓰기
			out.write(bytes);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "http://sungjin5891.cafe24.com/img/" + trueName;
	}
}
