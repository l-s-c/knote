package cn.buu.note.service.version.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.buu.note.dao.VersionDaoMapper;
import cn.buu.note.entity.VersionDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.version.VersionService;
@Service
public class VersionServiceImpl implements VersionService{

	@Resource 
	private VersionDaoMapper versionDaoMapper;
	@Override
	public String checkVersion(String version) {
		VersionDao versionDao = null;
		try {
			versionDao = versionDaoMapper.getNewVersion();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(versionDao.getVersionId().equals(version)) {
			//版本相同
			return null;
		}else {
			//版本不同
			return versionDao.getPath();
		}
	}
	@Override
	public List<VersionDao> loadVersion() throws Exception {
		List<VersionDao> list = null;
		try {
			 list = versionDaoMapper.selectAllVersion();
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		return list;
	}
	@Override
	public void uploadAPK(MultipartFile file, VersionDao versionDao) throws Exception {
		try {
			System.out.println("vdao:"+versionDao);
			System.out.println("file:"+file.getOriginalFilename());
			String path = "/www/knoteServer/apache-tomcat-8.5.38/webapps/Knote/file/apk/"+file.getOriginalFilename();
			//String path = "D:/"+file.getOriginalFilename();
			File newFile = new File(path);
			file.transferTo(newFile);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.ILL_PARAMETER_ERROR,"文件上传失败");
		}
		//保存数据
		try {
			versionDao.setPath("file/apk/"+file.getOriginalFilename());
			versionDaoMapper.insertSelective(versionDao);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
	}

}
