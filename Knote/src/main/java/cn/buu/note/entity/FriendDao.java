package cn.buu.note.entity;

import java.util.Date;

public class FriendDao {
    private Long id;

    private Integer myPhone;

    private Integer frPhone;

    private String remark;

    private Byte isFirst;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMyPhone() {
        return myPhone;
    }

    public void setMyPhone(Integer myPhone) {
        this.myPhone = myPhone;
    }

    public Integer getFrPhone() {
        return frPhone;
    }

    public void setFrPhone(Integer frPhone) {
        this.frPhone = frPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Byte isFirst) {
        this.isFirst = isFirst;
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

	@Override
	public String toString() {
		return "FriendDao [id=" + id + ", myPhone=" + myPhone + ", frPhone=" + frPhone + ", remark=" + remark
				+ ", isFirst=" + isFirst + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}
    
}