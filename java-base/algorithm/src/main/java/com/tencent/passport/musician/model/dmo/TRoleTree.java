package com.tencent.passport.musician.model.dmo;

import java.io.Serializable;

public class TRoleTree implements Serializable {
    private Integer id;

    private String descendantRoleCode;

    private String ancestorRoleCode;

    private String relator;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescendantRoleCode() {
        return descendantRoleCode;
    }

    public void setDescendantRoleCode(String descendantRoleCode) {
        this.descendantRoleCode = descendantRoleCode == null ? null : descendantRoleCode.trim();
    }

    public String getAncestorRoleCode() {
        return ancestorRoleCode;
    }

    public void setAncestorRoleCode(String ancestorRoleCode) {
        this.ancestorRoleCode = ancestorRoleCode == null ? null : ancestorRoleCode.trim();
    }

    public String getRelator() {
        return relator;
    }

    public void setRelator(String relator) {
        this.relator = relator == null ? null : relator.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", descendantRoleCode=").append(descendantRoleCode);
        sb.append(", ancestorRoleCode=").append(ancestorRoleCode);
        sb.append(", relator=").append(relator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}