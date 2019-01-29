package cn.buu.note.entity;

import java.util.Date;

public class PostAgain {
    private Long id;

	private Long postId;

	private Integer otherPhone;

	private String post;

	private Boolean isRead;

	private Date createTime;

	private Date modifyTime;

	private Integer sendPhone;

	

	@Override
	public String toString() {
		return "PostAgain [id=" + id + ", postId=" + postId + ", otherPhone=" + otherPhone + ", post=" + post
				+ ", isRead=" + isRead + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", sendPhone="
				+ sendPhone + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Integer getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(Integer otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post == null ? null : post.trim();
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
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

	public Integer getSendPhone() {
		return sendPhone;
	}

	public void setSendPhone(Integer sendPhone) {
		this.sendPhone = sendPhone;
	}


}