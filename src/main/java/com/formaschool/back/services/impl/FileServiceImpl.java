package com.formaschool.back.services.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.FileModel;
import com.formaschool.back.repositories.FileRepository;
import com.formaschool.back.services.FileService;
import com.formaschool.back.services.impl.enums.Folder;

public class FileServiceImpl extends CRUDServiceImpl<FileModel> implements FileService {

	private FileRepository repo;

	public FileServiceImpl(FileRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	// ====================================================================================================

	@Override
	public FileModel upload(Folder folder, MultipartFile file) {
		if (file == null)
			return null;
		FileModel model = repo.save(new FileModel(file.getOriginalFilename()));

		try {
			file.transferTo(folder.getFile(model.getPath()));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public byte[] download(Folder folder, String id) throws IOException {
		int dot = id.lastIndexOf(".");
		if (dot != -1)
			id = id.substring(0, dot);

		return new UrlResource(folder.getFile(opt(repo.findById(id)).getPath()).toURI()).getInputStream()
				.readAllBytes();
	}

	@Override
	public FileModel upload(Folder folder, String fileName, String fileBase64) {
		if (fileBase64 == null)
			return null;
		FileModel model = repo.save(new FileModel(fileName));

		try {
			Decoder decoder = Base64.getDecoder();
			byte[] decodedByte = decoder.decode(fileBase64.split(",")[1]);
			FileOutputStream fos = new FileOutputStream(folder.getFile(model.getPath()));
			fos.write(decodedByte);
			fos.close();

//			file.transferTo(folder.getFile(model.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
}
