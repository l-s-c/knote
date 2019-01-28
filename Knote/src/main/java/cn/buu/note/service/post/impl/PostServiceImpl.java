package cn.buu.note.service.post.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.buu.note.dao.PostAgainMapper;
import cn.buu.note.dao.PostDaoMapper;
import cn.buu.note.entity.PostDao;
import cn.buu.note.exception.CustomException;
import cn.buu.note.exception.ErrorEnum;
import cn.buu.note.service.post.PostService;

@Service
public class PostServiceImpl implements PostService{
	@Resource
	private PostDaoMapper postDaoMapper;
	@Resource
	private PostAgainMapper postAgainMapper;
	/**
	 * 生成id 保存評論
	 * 返回用户  和  这条评论id  方便删除用
	 */
	@Override
	public Long savePostToDb(PostDao post) throws Exception {
		try {
			Long id = postDaoMapper.selectMaxId();
			post.setId(id);
			postDaoMapper.insertSelective(post);
			return id;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		
	}
	@Override
	public void delPostFromDb(String id) throws Exception {
		System.out.println("id："+id);
		try {
			//删除楼层评论
			postDaoMapper.deleteByPrimaryKey(Long.parseLong(id));
			//删除楼层中的子评论
			postAgainMapper.deleteByPostId(Long.parseLong(id));
		}catch(Exception e) {
			e.printStackTrace();
			throw new CustomException(ErrorEnum.DB_CONNECT_ERROR);
		}
		
	}

}
