package com.asd.modules.pojo.user.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T_COMMON_USER")
public class TCommonUser implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7504260132029833480L;

	private BigDecimal u_id;
	private String usercode;
	private String username; 
	private String userpwd;
	private String comcode; 
	private String identifynumber; 
	private String sex;
	private BigDecimal age; 
	private String remark; 
	private String flag;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "u_id" )
	public BigDecimal getU_id() {
		return u_id;
	}

	public void setU_id(BigDecimal u_id) {
		this.u_id = u_id;
	}
	
	@Column(name = "usercode" )
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	@Column(name = "username" )
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "comcode" )
	public String getComcode() {
		return comcode;
	}

	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	@Column(name = "identifynumber" )
	public String getIdentifynumber() {
		return identifynumber;
	}

	public void setIdentifynumber(String identifynumber) {
		this.identifynumber = identifynumber;
	}
	@Column(name = "sex" )
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "age" )
	public BigDecimal getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}
	@Column(name = "remark" )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "flag" )
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "userpwd" )
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	
}
