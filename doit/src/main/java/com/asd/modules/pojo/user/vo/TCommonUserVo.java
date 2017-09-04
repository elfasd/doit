package com.asd.modules.pojo.user.vo;

import java.math.BigDecimal;



public class TCommonUserVo  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7504260132029833480L;

	private BigDecimal u_id;
	private BigDecimal gradeid;
	private String usercode;
	private String username; 
	private String userpwd;
	private String comcode; 
	private String identifynumber; 
	private String sex;
	private BigDecimal age; 
	private String remark; 
	private String flag;
	public BigDecimal getU_id() {
		return u_id;
	}
	public void setU_id(BigDecimal u_id) {
		this.u_id = u_id;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getComcode() {
		return comcode;
	}
	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	public String getIdentifynumber() {
		return identifynumber;
	}
	public void setIdentifynumber(String identifynumber) {
		this.identifynumber = identifynumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public BigDecimal getAge() {
		return age;
	}
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public BigDecimal getGradeid() {
		return gradeid;
	}
	public void setGradeid(BigDecimal gradeid) {
		this.gradeid = gradeid;
	}
	
	
}
