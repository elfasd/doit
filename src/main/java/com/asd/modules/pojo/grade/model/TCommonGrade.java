package com.asd.modules.pojo.grade.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "T_COMMON_GRADE")
public class TCommonGrade  implements java.io.Serializable{
	private static final long serialVersionUID = -7961791567610164528L;

	private BigDecimal g_id;
	private String gradecode; 
	private String gradecname; 
	private String gradetname; 
	private String gradeename; 
	private String gradetemplid;
	private Date createdate;
	private String creatorcode;
	private Date updatedate;
	private String updatercode;
	private String validstatus;
	private String flag;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "g_id" )
	public BigDecimal getG_id() {
		return g_id;
	}
	public void setG_id(BigDecimal gId) {
		g_id = gId;
	}
	@Column(name = "gradecode" )
	public String getGradecode() {
		return gradecode;
	}
	public void setGradecode(String gradecode) {
		this.gradecode = gradecode;
	}
	@Column(name = "gradecname" )
	public String getGradecname() {
		return gradecname;
	}
	public void setGradecname(String gradecname) {
		this.gradecname = gradecname;
	}
	@Column(name = "gradetname" )
	public String getGradetname() {
		return gradetname;
	}
	public void setGradetname(String gradetname) {
		this.gradetname = gradetname;
	}
	@Column(name = "gradetemplid" )
	public String getGradetemplid() {
		return gradetemplid;
	}
	public void setGradetemplid(String gradetemplid) {
		this.gradetemplid = gradetemplid;
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
	@Column(name = "flag" )
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "gradeename" )
	public String getGradeename() {
		return gradeename;
	}
	public void setGradeename(String gradeename) {
		this.gradeename = gradeename;
	}
	
}
