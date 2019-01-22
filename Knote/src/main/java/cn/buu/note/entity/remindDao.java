package cn.buu.note.entity;

public class remindDao {
    private Long id;

    private Integer phone;

    private String text;

    private Integer label;

    private String cron;

    private String startTime;

    private String endTime;

    private Byte rate;

    
    @Override
	public String toString() {
		return "remindDao [id=" + id + ", phone=" + phone + ", text=" + text + ", label=" + label + ", cron=" + cron
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", rate=" + rate + "]";
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public Byte getRate() {
        return rate;
    }

    public void setRate(Byte rate) {
        this.rate = rate;
    }
}