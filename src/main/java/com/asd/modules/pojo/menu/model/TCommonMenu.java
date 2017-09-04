package com.asd.modules.pojo.menu.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "T_COMMON_MENU")
public class TCommonMenu implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204260871969683789L;

	private BigDecimal m_id;
	private BigDecimal upperid; 
	private BigDecimal menulevel; 
	private String systemcode; 
	private String menucname;
	private String menutname; 
	private String menuename; 
	private String actionurl;
	private String target;
	private BigDecimal displayno;
	private String image; 
	private String imageexpand;
	private String imagecollapse; 
	private String iconexpand; 
	private String iconcollapse;
	private String taskcode;
	private Date createtime;
	private String creatorcode;
	private Date updatetime;
	private String updatercode; 
	private String validind;
	private String remark; 
	private String permitriskclass; 
	private String exceptriskcode;
	private String exceptriskclass;
	private String permitriskcode; 
	private String checkclass; 
	private String flag;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "m_id" )
	public BigDecimal getM_id() {
		return m_id;
	}
	
	public void setM_id(BigDecimal mId) {
		m_id = mId;
	}
	
	@Column(name = "upperid" )
	public BigDecimal getUpperid() {
		return upperid;
	}

	public void setUpperid(BigDecimal upperid) {
		this.upperid = upperid;
	}
	
	@Column(name = "menulevel" )
	public BigDecimal getMenulevel() {
		return menulevel;
	}

	public void setMenulevel(BigDecimal menulevel) {
		this.menulevel = menulevel;
	}
	
	@Column(name = "systemcode" )
	public String getSystemcode() {
		return systemcode;
	}

	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}
	
	@Column(name = "menucname" )
	public String getMenucname() {
		return menucname;
	}

	public void setMenucname(String menucname) {
		this.menucname = menucname;
	}
	
	@Column(name = "menutname" )
	public String getMenutname() {
		return menutname;
	}

	public void setMenutname(String menutname) {
		this.menutname = menutname;
	}
	
	@Column(name = "menuename" )
	public String getMenuename() {
		return menuename;
	}

	public void setMenuename(String menuename) {
		this.menuename = menuename;
	}
	
	@Column(name = "actionurl" )
	public String getActionurl() {
		return actionurl;
	}

	public void setActionurl(String actionurl) {
		this.actionurl = actionurl;
	}
	
	@Column(name = "target" )
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	@Column(name = "displayno" )
	public BigDecimal getDisplayno() {
		return displayno;
	}

	public void setDisplayno(BigDecimal displayno) {
		this.displayno = displayno;
	}
	
	@Column(name = "image" )
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Column(name = "imageexpand" )
	public String getImageexpand() {
		return imageexpand;
	}

	public void setImageexpand(String imageexpand) {
		this.imageexpand = imageexpand;
	}
	
	@Column(name = "imagecollapse" )
	public String getImagecollapse() {
		return imagecollapse;
	}

	public void setImagecollapse(String imagecollapse) {
		this.imagecollapse = imagecollapse;
	}
	
	@Column(name = "iconexpand" )
	public String getIconexpand() {
		return iconexpand;
	}

	public void setIconexpand(String iconexpand) {
		this.iconexpand = iconexpand;
	}
	
	@Column(name = "iconcollapse" )
	public String getIconcollapse() {
		return iconcollapse;
	}

	public void setIconcollapse(String iconcollapse) {
		this.iconcollapse = iconcollapse;
	}
	
	@Column(name = "taskcode" )
	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	
	@Column(name = "createtime" )
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Column(name = "creatorcode" )
	public String getCreatorcode() {
		return creatorcode;
	}

	public void setCreatorcode(String creatorcode) {
		this.creatorcode = creatorcode;
	}
	
	@Column(name = "updatetime" )
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Column(name = "updatercode" )
	public String getUpdatercode() {
		return updatercode;
	}

	public void setUpdatercode(String updatercode) {
		this.updatercode = updatercode;
	}
	
	@Column(name = "validind" )
	public String getValidind() {
		return validind;
	}

	public void setValidind(String validind) {
		this.validind = validind;
	}
	
	@Column(name = "remark" )
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "permitriskclass" )
	public String getPermitriskclass() {
		return permitriskclass;
	}

	public void setPermitriskclass(String permitriskclass) {
		this.permitriskclass = permitriskclass;
	}
	
	@Column(name = "exceptriskcode" )
	public String getExceptriskcode() {
		return exceptriskcode;
	}

	public void setExceptriskcode(String exceptriskcode) {
		this.exceptriskcode = exceptriskcode;
	}
	
	@Column(name = "exceptriskclass" )
	public String getExceptriskclass() {
		return exceptriskclass;
	}

	public void setExceptriskclass(String exceptriskclass) {
		this.exceptriskclass = exceptriskclass;
	}
	
	@Column(name = "permitriskcode" )
	public String getPermitriskcode() {
		return permitriskcode;
	}

	public void setPermitriskcode(String permitriskcode) {
		this.permitriskcode = permitriskcode;
	}
	
	@Column(name = "checkclass" )
	public String getCheckclass() {
		return checkclass;
	}

	public void setCheckclass(String checkclass) {
		this.checkclass = checkclass;
	}
	
	@Column(name = "flag" )
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
