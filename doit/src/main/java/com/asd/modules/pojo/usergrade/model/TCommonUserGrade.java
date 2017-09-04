package com.asd.modules.pojo.usergrade.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T_COMMON_USERGRADE")
public class TCommonUserGrade  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1226169968656257401L;

	private BigDecimal ug_id;
	private BigDecimal u_id;
	private BigDecimal gradeid; 
	private Date invaliddate; 
	private String validstatus;
	private String roamingtype; 
	private Date createdate; 
	private String creatorcode; 
	private Date updatedate; 
	private String updatercode; 
	private String flag; 

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ug_id" )
	public BigDecimal getUg_id() {
		return ug_id;
	}

	public void setUg_id(BigDecimal ugId) {
		ug_id = ugId;
	}
	@Column(name = "gradeid" )
	public BigDecimal getGradeid() {
		return gradeid;
	}

	public void setGradeid(BigDecimal gradeid) {
		this.gradeid = gradeid;
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
	@Column(name = "roamingtype" )
	public String getRoamingtype() {
		return roamingtype;
	}

	public void setRoamingtype(String roamingtype) {
		this.roamingtype = roamingtype;
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
	@Column(name = "u_id" )
	public BigDecimal getU_id() {
		return u_id;
	}

	public void setU_id(BigDecimal u_id) {
		this.u_id = u_id;
	}

	@Column(name = "flag" )
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
}
