package cn.buu.note.entity;

import java.util.Date;

public class ChatLog {
    private Long id;

    private Integer sendPhone;

    private Integer toPhone;

    private String text;

    private Boolean isSign;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(Integer sendPhone) {
        this.sendPhone = sendPhone;
    }

    public Integer getToPhone() {
        return toPhone;
    }

    public void setToPhone(Integer toPhone) {
        this.toPhone = toPhone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Boolean getIsSign() {
        return isSign;
    }

    public void setIsSign(Boolean isSign) {
        this.isSign = isSign;
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
		return "ChatLog [id=" + id + ", sendPhone=" + sendPhone + ", toPhone=" + toPhone + ", text=" + text
				+ ", isSign=" + isSign + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}
    
    
}