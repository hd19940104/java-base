package com.tencent.passport.musician.model.dmo;

import java.util.ArrayList;
import java.util.List;

public class TRoleTreeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRoleTreeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeIsNull() {
            addCriterion("descendant_role_code is null");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeIsNotNull() {
            addCriterion("descendant_role_code is not null");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeEqualTo(String value) {
            addCriterion("descendant_role_code =", value, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeNotEqualTo(String value) {
            addCriterion("descendant_role_code <>", value, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeGreaterThan(String value) {
            addCriterion("descendant_role_code >", value, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("descendant_role_code >=", value, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeLessThan(String value) {
            addCriterion("descendant_role_code <", value, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeLessThanOrEqualTo(String value) {
            addCriterion("descendant_role_code <=", value, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeLike(String value) {
            addCriterion("descendant_role_code like", value, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeNotLike(String value) {
            addCriterion("descendant_role_code not like", value, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeIn(List<String> values) {
            addCriterion("descendant_role_code in", values, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeNotIn(List<String> values) {
            addCriterion("descendant_role_code not in", values, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeBetween(String value1, String value2) {
            addCriterion("descendant_role_code between", value1, value2, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andDescendantRoleCodeNotBetween(String value1, String value2) {
            addCriterion("descendant_role_code not between", value1, value2, "descendantRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeIsNull() {
            addCriterion("ancestor_role_code is null");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeIsNotNull() {
            addCriterion("ancestor_role_code is not null");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeEqualTo(String value) {
            addCriterion("ancestor_role_code =", value, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeNotEqualTo(String value) {
            addCriterion("ancestor_role_code <>", value, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeGreaterThan(String value) {
            addCriterion("ancestor_role_code >", value, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ancestor_role_code >=", value, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeLessThan(String value) {
            addCriterion("ancestor_role_code <", value, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeLessThanOrEqualTo(String value) {
            addCriterion("ancestor_role_code <=", value, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeLike(String value) {
            addCriterion("ancestor_role_code like", value, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeNotLike(String value) {
            addCriterion("ancestor_role_code not like", value, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeIn(List<String> values) {
            addCriterion("ancestor_role_code in", values, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeNotIn(List<String> values) {
            addCriterion("ancestor_role_code not in", values, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeBetween(String value1, String value2) {
            addCriterion("ancestor_role_code between", value1, value2, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andAncestorRoleCodeNotBetween(String value1, String value2) {
            addCriterion("ancestor_role_code not between", value1, value2, "ancestorRoleCode");
            return (Criteria) this;
        }

        public Criteria andRelatorIsNull() {
            addCriterion("relator is null");
            return (Criteria) this;
        }

        public Criteria andRelatorIsNotNull() {
            addCriterion("relator is not null");
            return (Criteria) this;
        }

        public Criteria andRelatorEqualTo(String value) {
            addCriterion("relator =", value, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorNotEqualTo(String value) {
            addCriterion("relator <>", value, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorGreaterThan(String value) {
            addCriterion("relator >", value, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorGreaterThanOrEqualTo(String value) {
            addCriterion("relator >=", value, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorLessThan(String value) {
            addCriterion("relator <", value, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorLessThanOrEqualTo(String value) {
            addCriterion("relator <=", value, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorLike(String value) {
            addCriterion("relator like", value, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorNotLike(String value) {
            addCriterion("relator not like", value, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorIn(List<String> values) {
            addCriterion("relator in", values, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorNotIn(List<String> values) {
            addCriterion("relator not in", values, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorBetween(String value1, String value2) {
            addCriterion("relator between", value1, value2, "relator");
            return (Criteria) this;
        }

        public Criteria andRelatorNotBetween(String value1, String value2) {
            addCriterion("relator not between", value1, value2, "relator");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}