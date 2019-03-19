package cn.buu.note.service.version;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.buu.note.entity.VersionDao;

public interface VersionService {

	String checkVersion(String version);

	List<VersionDao> loadVersion() throws Exception;

	void uploadAPK(MultipartFile file, VersionDao versionDao) throws Exception;

}
