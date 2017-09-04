package com.asd.modules.pojo.gradetask.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "T_COMMON_GRADETASK")
public class TCommonGradeTask  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3793377166010751677L;
	private BigDecimal gt_id;
	private BigDecimal gradeid; 
	private BigDecimal taskid; 
	private String intranetvalue; 
	private String internetvalue;
	private Date invaliddate;
	private String validstatus;
	private Date createdate;
	private String creatorcode;
	private Date updatedate;
	private String flag;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "gt_id" )
	public BigDecimal getGt_id() {
		return gt_id;
	}
	public void setGt_id(BigDecimal gtId) {
		gt_id = gtId;
	}
	@Column(name = "gradeid" )
	public BigDecimal getGradeid() {
		return gradeid;
	}
	public void setGradeid(BigDecimal gradeid) {
		this.gradeid = gradeid;
	}
	@Column(name = "taskid" )
	public BigDecimal getTaskid() {
		return taskid;
	}
	public void setTaskid(BigDecimal taskid) {
		this.taskid = taskid;
	}
	@Column(name = "intranetvalue" )
	public String getIntranetvalue() {
		return intranetvalue;
	}
	public void setIntranetvalue(String intranetvalue) {
		this.intranetvalue = intranetvalue;
	}
	@Column(name = "internetvalue" )
	public String getInternetvalue() {
		return internetvalue;
	}
	public void setInternetvalue(String internetvalue) {
		this.internetvalue = internetvalue;
	}
	@Column(name = "invaliddate" )
	public Date getInvaliddate() {
		return invaliddate;
	}
	public void setInvaliddate(Date invaliddate) {
		this.invaliddate = invaliddate;
	}
	@Column(name = "validstatus" )
	public String getValidstatus() {
		return validstatus;
	}
	public void setValidstatus(String validstatus) {
		this.validstatus = validstatus;
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
	@Column(name = "flag" )
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
	
}
