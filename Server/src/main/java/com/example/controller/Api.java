package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.IOUtils;

public class Api {
	int result = 0;
	float test = 0;
	String strtest= "";
	public int api(String checkimg, String mainimg) throws Exception {
		// Img img1 = new Img("C:\\Temp\\img\\jong.jpg");
		// Img img2 = new Img("C:\\Temp\\img\\y.jpg");
		try {
			
		
			Float similarityThreshold = 70F;
			String sourceImage = "/home/hosting_users/sungjin5891/img/" + checkimg;
			String targetImage = "/home/hosting_users/sungjin5891/img/" + mainimg;
			
			ByteBuffer sourceImageBytes = null;
			ByteBuffer targetImageBytes = null;
			

			AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

			// Load source and target images and create input parameters
			try (InputStream inputStream = new FileInputStream(new File(sourceImage))) {
				sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
			} catch (Exception e) {
				System.out.println("Failed to load source image " + sourceImage);
				return -1;
			}
			try (InputStream inputStream = new FileInputStream(new File(targetImage))) {
				targetImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
			} catch (Exception e) {
				System.out.println("Failed to load target images: " + targetImage);
				return -1;
			}

			Image source = new Image().withBytes(sourceImageBytes);
			Image target = new Image().withBytes(targetImageBytes);

			CompareFacesRequest request = new CompareFacesRequest().withSourceImage(source).withTargetImage(target)
					.withSimilarityThreshold(similarityThreshold);

			// Call operation
			CompareFacesResult compareFacesResult = rekognitionClient.compareFaces(request);

			// Display results
			List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
			 for (CompareFacesMatch match: faceDetails){
		         ComparedFace face= match.getFace();
		         BoundingBox position = face.getBoundingBox();
//		         strtest = "Face at " + position.getLeft().toString()
//			               + " " + position.getTop()
//			               + " matches with " + face.getConfidence().toString()
//			               + "% confidence.";
//		         System.out.println("Face at " + position.getLeft().toString()
//		               + " " + position.getTop()
//		               + " matches with " + face.getConfidence().toString()
//		               + "% confidence.");

		       }
		       List<ComparedFace> uncompared = compareFacesResult.getUnmatchedFaces();
		       return uncompared.size();
//		       strtest += "There was " + uncompared.size()
//	            + " face(s) that did not match";
//		       return strtest;
//			for (CompareFacesMatch match : faceDetails) {
//				ComparedFace face = match.getFace();
//				if (face.getConfidence() > 80) {
//					result = 1;
//				}
//				test = face.getConfidence();
//			}
//			if(result != 1) {
//				result = 0;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return test;
		} catch (Exception e) {
			return -1;
		}
	}
}