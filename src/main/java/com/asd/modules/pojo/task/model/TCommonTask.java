package com.asd.modules.pojo.task.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_COMMON_TASK")
public class TCommonTask  implements java.io.Serializable{

	/**
	 * 资源权限表
	 */
	private static final long serialVersionUID = 4717018572254254863L;

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
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "task_id" )
	public BigDecimal getTask_id() {
		return task_id;
	}
	
	public void setTask_id(BigDecimal taskId) {
		task_id = taskId;
	}
	
	@Column(name = "svrcode" )
	public String getSvrcode() {
		return svrcode;
	}
	
	public void setSvrcode(String svrcode) {
		this.svrcode = svrcode;
	}
	
	@Column(name = "taskcode" )
	public String getTaskcode() {
		return taskcode;
	}
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	
	@Column(name = "parentcode" )
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	
	@Column(name = "taskcname" )
	public String getTaskcname() {
		return taskcname;
	}
	public void setTaskcname(String taskcname) {
		this.taskcname = taskcname;
	}
	
	@Column(name = "tasktname" )
	public String getTasktname() {
		return tasktname;
	}
	public void setTasktname(String tasktname) {
		this.tasktname = tasktname;
	}
	
	@Column(name = "taskename" )
	public String getTaskename() {
		return taskename;
	}
	public void setTaskename(String taskename) {
		this.taskename = taskename;
	}
	
	@Column(name = "createdate" )
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	@Column(name = "creatorcode" )
	public String getCreatorcode() {
		return creatorcode;
	}
	public void setCreatorcode(String creatorcode) {
		this.creatorcode = creatorcode;
	}
	
	@Column(name = "updatedate" )
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Column(name = "updatercode" )
	public String getUpdatercode() {
		return updatercode;
	}
	public void setUpdatercode(String updatercode) {
		this.updatercode = updatercode;
	}
	
	@Column(name = "validstatus" )
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
	}
	
	@Column(name = "lx" )
	public BigDecimal getLx() {
		return lx;
	}

	public void setLx(BigDecimal lx) {
		this.lx = lx;
	}
}
