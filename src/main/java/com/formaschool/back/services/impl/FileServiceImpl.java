package com.formaschool.back.services.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formaschool.back.models.FileModel;
import com.formaschool.back.repositories.FileRepository;
import com.formaschool.back.services.FileService;

public class FileServiceImpl extends CRUDServiceImpl<FileModel> implements FileService {

	private FileRepository repo;

	public FileServiceImpl(FileRepository repo, ObjectMapper mapper) {
		super(repo, mapper);
		this.repo = repo;
	}

	@Override
	public FileModel saveMultiPartFile(MultipartFile file) {
		if (file == null)
			return null;
		FileModel model = repo.save(new FileModel(file.getOriginalFilename()));

		String path = new File("src/main/resources/static/_uploads/" + model.getPath()).getAbsolutePath();

		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public byte[] download(String id) throws IOException {
		int dot = id.lastIndexOf(".");
		if (dot != -1)
			id = id.substring(0, dot);

		String path = new File("src/main/resources/static/_uploads/" + opt(repo.findById(id)).getPath())
				.getAbsolutePath();

		return new UrlResource(new File(path).toPath().toUri()).getInputStream().readAllBytes();
	}
}
