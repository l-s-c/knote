package cn.buu.note.entity;

import java.util.Date;

public class PostResult {
    private Long id;

    private Integer phone;

    private Long noteId;

    private String post;

    private Date createTime;

    private Date modifyTime;

    private String headPath;
    
    private String isHave;		//	是否有子评论
    
    
    

	@Override
	public String toString() {
		return "PostResult [id=" + id + ", phone=" + phone + ", noteId=" + noteId + ", post=" + post + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", headPath=" + headPath + ", isHave=" + isHave + "]";
	}

	public String getIsHave() {
		return isHave;
	}

	public void setIsHave(String isHave) {
		this.isHave = isHave;
	}


	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}