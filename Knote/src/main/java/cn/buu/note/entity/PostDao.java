package cn.buu.note.entity;

import java.util.Date;

public class PostDao {
    private Long id;

    private Integer phone;

    private Long noteId;

    private String post;

    private Date createTime;

    private Date modifyTime;

    
    @Override
	public String toString() {
		return "PostDao [id=" + id + ", phone=" + phone + ", noteId=" + noteId + ", post=" + post + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + "]";
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