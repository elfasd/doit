package com.asd.modules.pojo.task.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TCommonTaskVo {

	private BigDecimal task_id;
	private String svrcode; 
	private String taskcode; 
	private String parentcode; 
	private String taskcname;
	private String tasktname; 
	private String taskename; 
	private Date createdate;
	private String creatorcode;
	private Date updatedate;
	private String updatercode;
	private String validstatus;
	private BigDecimal lx;
	
	
	public BigDecimal getTask_id() {
		return task_id;
	}
	public void setTask_id(BigDecimal task_id) {
		this.task_id = task_id;
	}
	public String getSvrcode() {
		return svrcode;
	}
	public void setSvrcode(String svrcode) {
		this.svrcode = svrcode;
	}
	public String getTaskcode() {
		return taskcode;
	}
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	public String getTaskcname() {
		return taskcname;
	}
	public void setTaskcname(String taskcname) {
		this.taskcname = taskcname;
	}
	public String getTasktname() {
		return tasktname;
	}
	public void setTasktname(String tasktname) {
		this.tasktname = tasktname;
	}
	public String getTaskename() {
		return taskename;
	}
	public void setTaskename(String taskename) {
		this.taskename = taskename;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getCreatorcode() {
		return creatorcode;
	}
	public void setCreatorcode(String creatorcode) {
		this.creatorcode = creatorcode;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdatercode() {
		return updatercode;
	}
	public void setUpdatercode(String updatercode) {
		this.updatercode = updatercode;
	}
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	public BigDecimal getLx() {
		return lx;
	}
	public void setLx(BigDecimal lx) {
		this.lx = lx;
	} 
	
	
}
