package com.formaschool.back.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.formaschool.back.models.FileModel;
import com.formaschool.back.services.impl.enums.Folder;

public interface FileService extends CRUDService<FileModel> {

	public FileModel upload(Folder folder, MultipartFile file);

	public byte[] download(Folder folder, String id) throws IOException;
}
