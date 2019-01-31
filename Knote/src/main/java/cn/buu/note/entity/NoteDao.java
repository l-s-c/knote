package cn.buu.note.entity;

import java.util.Date;

public class NoteDao {
    private Long id;

	private Integer phone;

	private String noteTitle;

	private String noteText;

	private int label;

	private Date createTime;

	private Date modifyTime;
	
	private String labelDesc;
	

	@Override
	public String toString() {
		return "NoteDao [id=" + id + ", phone=" + phone + ", noteTitle=" + noteTitle + ", noteText=" + noteText
				+ ", label=" + label + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", labelDesc="
				+ labelDesc + "]";
	}

	public String getLabelDesc() {
		return labelDesc;
	}

	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
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

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle == null ? null : noteTitle.trim();
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText == null ? null : noteText.trim();
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
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