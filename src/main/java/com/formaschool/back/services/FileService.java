package com.formaschool.back.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.formaschool.back.models.FileModel;

public interface FileService extends CRUDService<FileModel> {

	public FileModel saveMultiPartFile(MultipartFile file);

	public byte[] download(String id) throws IOException;
}
