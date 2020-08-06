package com.jt.manager.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult; 

@Service
public class FileServiceImpl implements FileService {

	@Value("${image.localPath}")
	private String localPath;
	@Value("image.urlPath")
	private String urlPath;
	
	/**
	 * 	为提高效率需要份文件存储，一般分为3级。
	 * 
	 * 
	 */
	@Override
	public PicUploadResult fileUpload(MultipartFile uploadFile) {
		PicUploadResult uploadResult = new PicUploadResult();
		// 1.校验,判读图片类型
		String fileName = uploadFile.getOriginalFilename();
		fileName=fileName.toLowerCase();
		if (!fileName.matches("^.*\\.(jpg|png|jpeg|gif|)$")) {
			uploadResult.setError(1);
			return uploadResult;
		}
		// 2.病毒检测
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int height = bufferedImage.getHeight();
			int width = bufferedImage.getWidth();
			if (height == 0 || width == 0) {
				uploadResult.setError(1);
				return uploadResult;
			}
			// 文件存储
			String filrPath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String dirPath = localPath + filrPath;
			File file = new File(dirPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 计算文件名
			String uuid = UUID.randomUUID().toString().replace("-", "");
			int randomNum = new Random().nextInt(1000);
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String imageFileName = uuid + randomNum + fileType;
			// 写入磁盘
			uploadFile.transferTo(new File(dirPath + "/" + imageFileName));
			System.out.println("----------------------------------------------------------------------文件上传成功");
			uploadResult.setWidth(width + "");
			uploadResult.setHeight(height + "");
			
			String imageUrlPath=urlPath+filrPath+"/"+imageFileName;
			uploadResult.setUrl(imageUrlPath);
		} catch (IOException e) {
			e.printStackTrace();
			uploadResult.setError(1);
			return uploadResult;
		}
		return uploadResult;

	}

}
