package com.hjh.practice.item.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws IOException{
        UUID uuid = UUID.randomUUID();
        String extention = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extention;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        return savedFileName;
    }
    
}
