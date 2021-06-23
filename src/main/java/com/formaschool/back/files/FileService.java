package com.formaschool.back.files;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.formaschool.back._crud.CRUDService;

public interface FileService extends CRUDService<FileModel> {

	public FileModel upload(Folder folder, MultipartFile file);

	public FileModel upload(Folder folder, String fileName, String fileBase64);

	public byte[] download(Folder folder, String id) throws IOException;
}
