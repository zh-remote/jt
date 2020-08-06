package com.jt.manager.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manager.service.FileService;

@Controller
public class fileReciveController {
   
	@Autowired
	private FileService fileService;
	@RequestMapping("/file")
	public String fileUpload(MultipartFile image) {
		File newFile = new File("G:/upload");
		if (!newFile.exists()) {
			newFile.mkdir();
		}
		try {
			image.transferTo(new File("G:/upload/" + image.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult picUpload(MultipartFile dir) {
		return   fileService.fileUpload(dir);
	}

}
