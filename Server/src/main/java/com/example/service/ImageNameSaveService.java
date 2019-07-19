package com.example.service;

public class ImageNameSaveService {
	
	private static ImageNameSaveService instance;
	
	private String SingleImage = null;
	private String CheckImage = null;
	private String MainImage = null;
	
	public String getCheckImage() {
		return CheckImage;
	}
	public String getMainImage() {
		return MainImage;
	}
	public void setCheckImage(String checkImage) {
		CheckImage = checkImage;
	}
	public void setMainImage(String mainImage) {
		MainImage = mainImage;
	}
	public String getSingleImage() {
		return SingleImage;
	}
	public void setSingleImage(String singleImage) {
		SingleImage = singleImage;
	}
	
	private ImageNameSaveService() {}
	
	public static ImageNameSaveService getImageService() {
		if(instance == null) {
			instance = new ImageNameSaveService();
		}
		return instance;
	}
	
}
