package com.tencent.passport.musician.model.dmo;

import java.io.Serializable;
import java.util.Date;

public class TDataAuth implements Serializable {
    private Integer id;

    private String bizId;

    private String tenant;

    private String tenantTerm;

    private String ccode;

    private Date ctime;

    private String ucode;

    private Date utime;

    private Boolean deleted;

    private String remark;

    private String nameText;

    private String descText;

    private String markCode;

    private String mode;

    private String condSql;

    private String extendData;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant == null ? null : tenant.trim();
    }

    public String getTenantTerm() {
        return tenantTerm;
    }

    public void setTenantTerm(String tenantTerm) {
        this.tenantTerm = tenantTerm == null ? null : tenantTerm.trim();
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode == null ? null : ccode.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode == null ? null : ucode.trim();
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText == null ? null : nameText.trim();
    }

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText == null ? null : descText.trim();
    }

    public String getMarkCode() {
        return markCode;
    }

    public void setMarkCode(String markCode) {
        this.markCode = markCode == null ? null : markCode.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public String getCondSql() {
        return condSql;
    }

    public void setCondSql(String condSql) {
        this.condSql = condSql == null ? null : condSql.trim();
    }

    public String getExtendData() {
        return extendData;
    }

    public void setExtendData(String extendData) {
        this.extendData = extendData == null ? null : extendData.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bizId=").append(bizId);
        sb.append(", tenant=").append(tenant);
        sb.append(", tenantTerm=").append(tenantTerm);
        sb.append(", ccode=").append(ccode);
        sb.append(", ctime=").append(ctime);
        sb.append(", ucode=").append(ucode);
        sb.append(", utime=").append(utime);
        sb.append(", deleted=").append(deleted);
        sb.append(", remark=").append(remark);
        sb.append(", nameText=").append(nameText);
        sb.append(", descText=").append(descText);
        sb.append(", markCode=").append(markCode);
        sb.append(", mode=").append(mode);
        sb.append(", condSql=").append(condSql);
        sb.append(", extendData=").append(extendData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}