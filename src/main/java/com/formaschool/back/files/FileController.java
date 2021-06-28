package com.formaschool.back.files;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formaschool.back._crud.CRUDController;
import com.formaschool.back._crud.CRUDService;

@RestController
@RequestMapping("files")
@CrossOrigin
public class FileController implements CRUDController<FileModel> {

	@Autowired
	private FileService service;

	@Override
	public CRUDService<FileModel> getGenericService() {
		return service;
	}

	// ====================================================================================================

//	@PostMapping()
	public FileModel upload(@RequestParam(value = "file") MultipartFile file) {
		return service.upload(null, file);
	}

	@GetMapping(value = "{type}/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] download(@PathVariable("type") String type, @PathVariable("id") String id)
			throws IOException {
		return service.download(Folder.fromString(type), id);
	}
}
