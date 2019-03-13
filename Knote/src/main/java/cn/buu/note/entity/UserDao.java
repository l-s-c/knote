package cn.buu.note.entity;

import java.io.Serializable;
import java.util.Date;
//用户�?
public class UserDao implements Serializable{
    private Long id;

	private Integer phone;

	private String pwd;

	private String openId;

	private Date createTime;

	private Date modifyTime;

	private int isAclivate;
	
	private String headPath;

	private String remark;		//备注

	
	

	@Override
	public String toString() {
		return "UserDao [id=" + id + ", phone=" + phone + ", pwd=" + pwd + ", openId=" + openId + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", isAclivate=" + isAclivate + ", headPath=" + headPath
				+ ", remark=" + remark + "]";
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
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

	public int getIsAclivate() {
		return isAclivate;
	}

	public void setIsAclivate(int isAclivate) {
		this.isAclivate = isAclivate;
	}

	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}