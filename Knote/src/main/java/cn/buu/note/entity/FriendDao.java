package cn.buu.note.entity;

import java.util.Date;

public class FriendDao {
    private Long id;

    private Long myPhone;

    private Long frPhone;

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

    public Long getMyPhone() {
        return myPhone;
    }

    public void setMyPhone(Long myPhone) {
        this.myPhone = myPhone;
    }

    public Long getFrPhone() {
        return frPhone;
    }

    public void setFrPhone(Long frPhone) {
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