package com.asd.modules.pojo.fzacc.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单实体
 * Created by zlp on 2017/1/16.
 */
@Entity
@Table(name = "fzAcc")
public class FzAcc {

    private String accNo;//VARCHAR(22) not null,*账单编号PK,由系统自动生成
    private String optType;//VARCHAR(1),业务类型--**0-分出业务；1-分入业务
    private String accType;//VARCHAR(2),--**[1]：1-对外账单--**5-省级账单,4-市级账单,2-县级账单
    //--**[2]：1-合同账单,0-临分账单,--**2-法定账单
    private String accKind;//VARCHAR(1),--**(临分账单)P：保单账单 E：批单账单  C：赔款账单
    //--**(合同账单)M：现金赔款  G：正常账单  X：巨灾超赔账单
    //--**(超赔临分账单)A：分保费账单 保单    B：超赔调整  批单   D：赔款账单  赔案
    //--**(超赔合约账单)I：预付保费  F：调整保费  H：赔款账单   K：合约超赔系统账单
    //--**(内超赔账单)L: 内超赔系统账单  PC账单(暂未定)   结转账单(暂未定)   结转账单(暂未定)
    private String comCode;//VARCHAR(8),业务归属公司代码
    private String comName;// VARCHAR(60),业务归属公司名称
    private String insuredName;// VARCHAR(100),被保险人／船东名称
    private Date damageDate;//DATE,出险日期
    private String treatyId;//VARCHAR(10),合同序号(合同账单)
    private String sectionNo;//VARCHAR(1),合约分项编码(合同账单)--超赔时，含义为层，程序里面控制小于10层
    private String treatyName;//VARCHAR(40),合同名称
    private String accPeriod;//VARCHAR(8),账单期合同账单
    private String repolicyNo;// VARCHAR(22),分保保单号
    private String recertifyNo;//VARCHAR(22),分保业务单证号--**临分账单：分保保单号--**分保批单号--**分保赔案号
    private String drcertifyNo;// VARCHAR(27),直接业务单证--**临分账单：保单号--**批单号--**赔案号
    private String riskUnitCode;//SMALLINT,危险单位号
    private String yourRef;//VARCHAR(22),对方业务单证号
    private String facFlag;//VARCHAR(1),临分接受人类型--**1:特约;0:普通
    private String reinsType;//VARCHAR(1),接受人类型--**1:再保人;0:经纪人
    private String reinsCode;//VARCHAR(10),接受人编码
    private String reinsName;//VARCHAR(100),接受人名称（再保人／经纪人）
    private String payCode;// VARCHAR(10),结付人编码
    private String payName;//VARCHAR(100),结付人名称
    private String uwYear;//VARCHAR(4),业务年度
    private String classCode;//VARCHAR(3),险类
    private String riskCode;//VARCHAR(3),险种超赔：如果险类没有在接受人上体现,则对于对--**外账单,各接受人各险类的信息,需要反算
    private BigDecimal shareRate;//DECIMAL(9,6),分出份额
    private String currencyOrig ;// VARCHAR(3),原币▲(分保币种)
    private BigDecimal balanceOrig;//DECIMAL(14,2),原币余额（分保币种）对外账单：=贷-借  对内账单：=借-贷
    private BigDecimal exchRate;// DECIMAL(16,10) default 1,兑换率
    private BigDecimal exchShareVAT = BigDecimal.ONE; // 增值税兑换率
    private String currency;// VARCHAR(3),账单币种▲（账单币种）
    private BigDecimal balance;// DECIMAL(14,2),余额(兑换后)（账单币种）
    private BigDecimal osLoss = new BigDecimal(0);// DECIMAL(14,2),应摊未决赔款
    private BigDecimal ibnr;// DECIMAL(14,2),已发生未报告的未决金额
    private BigDecimal premiumVAT = BigDecimal.ZERO; //增值税
    private BigDecimal brokerageVAT = BigDecimal.ZERO; //经纪费增值税
    private BigDecimal brokerageVATS = BigDecimal.ZERO; //经纪费增值税附加
    private BigDecimal premiumVATD = BigDecimal.ZERO; //增值税扣除
    private BigDecimal premiumVATS = BigDecimal.ZERO; //增值税附加
    private BigDecimal brokerageVATD = BigDecimal.ZERO; //经纪费增值税扣除
    private BigDecimal brokerageVATSD = BigDecimal.ZERO; //经纪费增值税附加扣除
    private String remarks;// VARCHAR(255),备注(对外)
    private String memo;//VARCHAR(255),备注(对内)
    private String sepeFlag;//VARCHAR(1),分期付款否1-分期；0-不分期
    private String settleType;//VARCHAR(1),结付类型1分出合同2	分出临分*3	核pool（旧系统含义，新系统无，且不支持此类数据的结付）
    //4	卫星联合体（旧系统含义，新系统无，且不支持此类数据的结付）5	分入合同6	分入临分7	对内合同8	对内临分
    private String makeComCode;//VARCHAR(8),制单部门代码
    private Date accDate;//DATE,业务账单生成日期(打印时显示)
    private String createrCode;// VARCHAR(10),创建人
    private Date createDate;//DATETIME YEAR TO SECOND,创建时间
    private String updaterCode;//VARCHAR(10),修改人
    private Date updateDate;//DATETIME YEAR TO SECOND,最后修改时间
    private String belongType;//VARCHAR(2),所属的统计类型00-基本险,01-地震险,--**02-战争险,--**10-石油险查勘费--**99-不区分统计类型
    private String businessFlag;//VARCHAR(1),业务类型：0-人民币业务,--**1-外币业务,9-不区分业务类型
    private String dealFlag;// VARCHAR(10),处理标志：
    //--**[1]周边交互：0-未处理，1-转结算（原系统种的账单有该标志），2-转财务(分公司账单转财务或总公司的分入账单转财务),3-总公司分出账单转财务；
    //--**[2]操作方式：0-系统账单，1-手工账单,2-原系统转入账单
    //--**[3]通讯情况：
    //--**[4]冲销情况：0-未冲销，1-已冲销，2－冲销账单 ——v1.2.0停止使用该字段
    //--**[5]账单情况：1-转分账单 0-普通账单，2-偿付能力账单
    //--**[6]系统账存储状态：
    //--**   0-暂存 1-保存 2-提交审核
    //--**[8]合约系统账对外账生成方式
    //--**   0:分公司制作对外账
    //--**   1-总公司制作对外账
    //--**[9] 关门账标志
    //--**   0-非关门账  1-关门账
    //--**[10] 0-分入合约对外账未生成对内账，1-分入合约对外账已生成对内账，
    private String VATFlag; //增值税标识  0 --免税，1 --应税
    private String flag;//VARCHAR(10)--**备用--**[1]:超赔合约账单--**    预付保费账单，默认“1”，--**    调整保费账单，存储调整次数，如1、2；--**    赔款账单，同调整保费账单的存储规则--**[2, 3]:分入合约账单：--**       账单批次（对内、对外账一致）
    private String ppw;//varchar(1),
    private Date createtimefordw;//DATE,
    private Date updatetimefordw;//DATE,
    private String associateNo;
    private String treatyType;//合约类型
    private String yourRep;
    private BigDecimal vatRate;
    private String inAccNo;//转分账单对应的分入账单号
    private String relationNo;//人保在系统账单关联码，主要用于关联冲销账单和正式账单以及分入分出账单
    private BigDecimal noDuePremiumCNY; // 未到期保费(折账单币种)
    private String dmflag = "0";
    private Date startDate;
    private Date endDate;
    private Date accPayTime;

    @Id
    @Column(name = "accNo" )
    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    @Column(name="optType")
    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    @Column(name="accType")
    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    @Column(name="accKind")
    public String getAccKind() {
        return accKind;
    }

    public void setAccKind(String accKind) {
        this.accKind = accKind;
    }

    @Column(name="comCode")
    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    @Column(name="comName")
    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    @Column(name="insuredName")
    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    @Column(name="damageDate")
    public Date getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(Date damageDate) {
        this.damageDate = damageDate;
    }

    @Column(name="treatyId")
    public String getTreatyId() {
        return treatyId;
    }

    public void setTreatyId(String treatyId) {
        this.treatyId = treatyId;
    }

    @Column(name="sectionNo")
    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo;
    }

    @Column(name="treatyName")
    public String getTreatyName() {
        return treatyName;
    }

    public void setTreatyName(String treatyName) {
        this.treatyName = treatyName;
    }

    @Column(name="accPeriod")
    public String getAccPeriod() {
        return accPeriod;
    }

    public void setAccPeriod(String accPeriod) {
        this.accPeriod = accPeriod;
    }

    @Column(name="repolicyNo")
    public String getRepolicyNo() {
        return repolicyNo;
    }

    public void setRepolicyNo(String repolicyNo) {
        this.repolicyNo = repolicyNo;
    }

    @Column(name="recertifyNo")
    public String getRecertifyNo() {
        return recertifyNo;
    }

    public void setRecertifyNo(String recertifyNo) {
        this.recertifyNo = recertifyNo;
    }

    @Column(name="drcertifyNo")
    public String getDrcertifyNo() {
        return drcertifyNo;
    }

    public void setDrcertifyNo(String drcertifyNo) {
        this.drcertifyNo = drcertifyNo;
    }

    @Column(name="riskUnitCode")
    public String getRiskUnitCode() {
        return riskUnitCode;
    }

    public void setRiskUnitCode(String riskUnitCode) {
        this.riskUnitCode = riskUnitCode;
    }

    @Column(name="yourRef")
    public String getYourRef() {
        return yourRef;
    }

    public void setYourRef(String yourRef) {
        this.yourRef = yourRef;
    }

    @Column(name="facFlag")
    public String getFacFlag() {
        return facFlag;
    }

    public void setFacFlag(String facFlag) {
        this.facFlag = facFlag;
    }

    @Column(name="reinsType")
    public String getReinsType() {
        return reinsType;
    }

    public void setReinsType(String reinsType) {
        this.reinsType = reinsType;
    }

    @Column(name="reinsCode")
    public String getReinsCode() {
        return reinsCode;
    }

    public void setReinsCode(String reinsCode) {
        this.reinsCode = reinsCode;
    }

    @Column(name="reinsName")
    public String getReinsName() {
        return reinsName;
    }

    public void setReinsName(String reinsName) {
        this.reinsName = reinsName;
    }

    @Column(name="payCode")
    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    @Column(name="payName")
    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    @Column(name="uwYear")
    public String getUwYear() {
        return uwYear;
    }

    public void setUwYear(String uwYear) {
        this.uwYear = uwYear;
    }

    @Column(name="classCode")
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Column(name="riskCode")
    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    @Column(name="shareRate")
    public BigDecimal getShareRate() {
        return shareRate;
    }

    public void setShareRate(BigDecimal shareRate) {
        this.shareRate = shareRate;
    }

    @Column(name="currencyOrig")
    public String getCurrencyOrig() {
        return currencyOrig;
    }

    public void setCurrencyOrig(String currencyOrig) {
        this.currencyOrig = currencyOrig;
    }

    @Column(name="balanceOrig")
    public BigDecimal getBalanceOrig() {
        return balanceOrig;
    }

    public void setBalanceOrig(BigDecimal balanceOrig) {
        this.balanceOrig = balanceOrig;
    }

    @Column(name="exchRate")
    public BigDecimal getExchRate() {
        return exchRate;
    }

    public void setExchRate(BigDecimal exchRate) {
        this.exchRate = exchRate;
    }

    @Column(name="exchShareVAT")
    public BigDecimal getExchShareVAT() {
        return exchShareVAT;
    }

    public void setExchShareVAT(BigDecimal exchShareVAT) {
        this.exchShareVAT = exchShareVAT;
    }

    @Column(name="currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name="balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Column(name="osLoss")
    public BigDecimal getOsLoss() {
        return osLoss;
    }

    public void setOsLoss(BigDecimal osLoss) {
        this.osLoss = osLoss;
    }

    @Column(name="ibnr")
    public BigDecimal getIbnr() {
        return ibnr;
    }

    public void setIbnr(BigDecimal ibnr) {
        this.ibnr = ibnr;
    }

    @Column(name="premiumVAT")
    public BigDecimal getPremiumVAT() {
        return premiumVAT;
    }

    public void setPremiumVAT(BigDecimal premiumVAT) {
        this.premiumVAT = premiumVAT;
    }

    @Column(name="brokerageVAT")
    public BigDecimal getBrokerageVAT() {
        return brokerageVAT;
    }

    public void setBrokerageVAT(BigDecimal brokerageVAT) {
        this.brokerageVAT = brokerageVAT;
    }

    @Column(name="brokerageVATS")
    public BigDecimal getBrokerageVATS() {
        return brokerageVATS;
    }

    public void setBrokerageVATS(BigDecimal brokerageVATS) {
        this.brokerageVATS = brokerageVATS;
    }

    @Column(name="premiumVATD")
    public BigDecimal getPremiumVATD() {
        return premiumVATD;
    }

    public void setPremiumVATD(BigDecimal premiumVATD) {
        this.premiumVATD = premiumVATD;
    }

    @Column(name="premiumVATS")
    public BigDecimal getPremiumVATS() {
        return premiumVATS;
    }

    public void setPremiumVATS(BigDecimal premiumVATS) {
        this.premiumVATS = premiumVATS;
    }

    @Column(name="brokerageVATD")
    public BigDecimal getBrokerageVATD() {
        return brokerageVATD;
    }

    public void setBrokerageVATD(BigDecimal brokerageVATD) {
        this.brokerageVATD = brokerageVATD;
    }

    @Column(name="brokerageVATSD")
    public BigDecimal getBrokerageVATSD() {
        return brokerageVATSD;
    }

    public void setBrokerageVATSD(BigDecimal brokerageVATSD) {
        this.brokerageVATSD = brokerageVATSD;
    }

    @Column(name="remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name="memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(name="sepeFlag")
    public String getSepeFlag() {
        return sepeFlag;
    }

    public void setSepeFlag(String sepeFlag) {
        this.sepeFlag = sepeFlag;
    }

    @Column(name="settleType")
    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    @Column(name="makeComCode")
    public String getMakeComCode() {
        return makeComCode;
    }

    public void setMakeComCode(String makeComCode) {
        this.makeComCode = makeComCode;
    }

    @Column(name="accDate")
    public Date getAccDate() {
        return accDate;
    }

    public void setAccDate(Date accDate) {
        this.accDate = accDate;
    }

    @Column(name="createrCode")
    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }

    @Column(name="createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name="updaterCode")
    public String getUpdaterCode() {
        return updaterCode;
    }

    public void setUpdaterCode(String updaterCode) {
        this.updaterCode = updaterCode;
    }

    @Column(name="updateDate")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name="belongType")
    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
    }

    @Column(name="businessFlag")
    public String getBusinessFlag() {
        return businessFlag;
    }

    public void setBusinessFlag(String businessFlag) {
        this.businessFlag = businessFlag;
    }

    @Column(name="dealFlag")
    public String getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    @Column(name="VATFlag")
    public String getVATFlag() {
        return VATFlag;
    }

    public void setVATFlag(String VATFlag) {
        this.VATFlag = VATFlag;
    }

    @Column(name="flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Column(name="ppw")
    public String getPpw() {
        return ppw;
    }

    public void setPpw(String ppw) {
        this.ppw = ppw;
    }

    @Column(name="createtimefordw")
    public Date getCreatetimefordw() {
        return createtimefordw;
    }

    public void setCreatetimefordw(Date createtimefordw) {
        this.createtimefordw = createtimefordw;
    }

    @Column(name="updatetimefordw")
    public Date getUpdatetimefordw() {
        return updatetimefordw;
    }

    public void setUpdatetimefordw(Date updatetimefordw) {
        this.updatetimefordw = updatetimefordw;
    }

    @Column(name="associateNo")
     public String getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(String associateNo) {
        this.associateNo = associateNo;
    }

    @Column(name="treatyType")
    public String getTreatyType() {
        return treatyType;
    }

    public void setTreatyType(String treatyType) {
        this.treatyType = treatyType;
    }

    @Column(name="yourRep")
    public String getYourRep() {
        return yourRep;
    }

    public void setYourRep(String yourRep) {
        this.yourRep = yourRep;
    }

    @Column(name="vatRate")
    public BigDecimal getVatRate() {
        return vatRate;
    }

    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    @Column(name="inAccNo")
    public String getInAccNo() {
        return inAccNo;
    }

    public void setInAccNo(String inAccNo) {
        this.inAccNo = inAccNo;
    }

    @Column(name="relationNo")
    public String getRelationNo() {
        return relationNo;
    }

    public void setRelationNo(String relationNo) {
        this.relationNo = relationNo;
    }

    @Column(name="noDuePremiumCNY")
    public BigDecimal getNoDuePremiumCNY() {
        return noDuePremiumCNY;
    }

    public void setNoDuePremiumCNY(BigDecimal noDuePremiumCNY) {
        this.noDuePremiumCNY = noDuePremiumCNY;
    }

    @Column(name="dmflag")
    public String getDmflag() {
        return dmflag;
    }

    public void setDmflag(String dmflag) {
        this.dmflag = dmflag;
    }

    @Column(name = "startDate" )
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @Column(name = "endDate" )
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Column(name = "accPayTime" )
    public Date getAccPayTime() {
        return accPayTime;
    }

    public void setAccPayTime(Date accPayTime) {
        this.accPayTime = accPayTime;
    }
}
