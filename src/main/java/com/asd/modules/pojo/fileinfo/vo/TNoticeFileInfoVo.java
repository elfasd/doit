package com.asd.modules.pojo.fileinfo.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TNoticeFileInfoVo {
	
	private BigDecimal f_id;
	
	private String fileName;
	
	private String fileType;
	
	private String filePath;
	
	private String operator;
	
	private Date operateTime;
	
	private String flag;

	public BigDecimal getF_id() {
		return f_id;
	}

	public void setF_id(BigDecimal f_id) {
		this.f_id = f_id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}	
	
	
}
