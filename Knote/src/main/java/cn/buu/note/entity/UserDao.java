package cn.buu.note.entity;

import java.io.Serializable;
import java.util.Date;
//用户类
public class UserDao implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer phone;

    private String pwd;

    private String openId;

    private Date createTime;

    private Date modifyTime;

    
    
    @Override
	public String toString() {
		return "UserDao [id=" + id + ", phone=" + phone + ", pwd=" + pwd + ", openId=" + openId + ", createTime="
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
}