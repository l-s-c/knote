package cn.buu.note.entity;

import java.util.Date;

public class VersionDao {
    private Long id;

    private String versionId;

    private String versionDesc;

    private String path;

    private Date createTime;

    private Date modifyTime;
    
    
    

    @Override
	public String toString() {
		return "VersionDao [id=" + id + ", versionId=" + versionId + ", versionDesc=" + versionDesc + ", path=" + path
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc == null ? null : versionDesc.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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