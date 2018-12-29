package essw.com.scheduler.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskExample() {
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

        public Criteria andDealDatetimeIsNull() {
            addCriterion("deal_datetime is null");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeIsNotNull() {
            addCriterion("deal_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeEqualTo(Date value) {
            addCriterion("deal_datetime =", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeNotEqualTo(Date value) {
            addCriterion("deal_datetime <>", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeGreaterThan(Date value) {
            addCriterion("deal_datetime >", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deal_datetime >=", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeLessThan(Date value) {
            addCriterion("deal_datetime <", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("deal_datetime <=", value, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeIn(List<Date> values) {
            addCriterion("deal_datetime in", values, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeNotIn(List<Date> values) {
            addCriterion("deal_datetime not in", values, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeBetween(Date value1, Date value2) {
            addCriterion("deal_datetime between", value1, value2, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andDealDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("deal_datetime not between", value1, value2, "dealDatetime");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("task_type is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("task_type is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(String value) {
            addCriterion("task_type =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(String value) {
            addCriterion("task_type <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(String value) {
            addCriterion("task_type >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("task_type >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(String value) {
            addCriterion("task_type <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(String value) {
            addCriterion("task_type <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLike(String value) {
            addCriterion("task_type like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotLike(String value) {
            addCriterion("task_type not like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<String> values) {
            addCriterion("task_type in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<String> values) {
            addCriterion("task_type not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(String value1, String value2) {
            addCriterion("task_type between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(String value1, String value2) {
            addCriterion("task_type not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andHandleObjIsNull() {
            addCriterion("handle_obj is null");
            return (Criteria) this;
        }

        public Criteria andHandleObjIsNotNull() {
            addCriterion("handle_obj is not null");
            return (Criteria) this;
        }

        public Criteria andHandleObjEqualTo(String value) {
            addCriterion("handle_obj =", value, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjNotEqualTo(String value) {
            addCriterion("handle_obj <>", value, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjGreaterThan(String value) {
            addCriterion("handle_obj >", value, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjGreaterThanOrEqualTo(String value) {
            addCriterion("handle_obj >=", value, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjLessThan(String value) {
            addCriterion("handle_obj <", value, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjLessThanOrEqualTo(String value) {
            addCriterion("handle_obj <=", value, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjLike(String value) {
            addCriterion("handle_obj like", value, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjNotLike(String value) {
            addCriterion("handle_obj not like", value, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjIn(List<String> values) {
            addCriterion("handle_obj in", values, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjNotIn(List<String> values) {
            addCriterion("handle_obj not in", values, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjBetween(String value1, String value2) {
            addCriterion("handle_obj between", value1, value2, "handleObj");
            return (Criteria) this;
        }

        public Criteria andHandleObjNotBetween(String value1, String value2) {
            addCriterion("handle_obj not between", value1, value2, "handleObj");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNull() {
            addCriterion("task_status is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("task_status is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(Integer value) {
            addCriterion("task_status =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(Integer value) {
            addCriterion("task_status <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(Integer value) {
            addCriterion("task_status >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_status >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(Integer value) {
            addCriterion("task_status <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(Integer value) {
            addCriterion("task_status <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<Integer> values) {
            addCriterion("task_status in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<Integer> values) {
            addCriterion("task_status not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(Integer value1, Integer value2) {
            addCriterion("task_status between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("task_status not between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andStartOnIsNull() {
            addCriterion("start_on is null");
            return (Criteria) this;
        }

        public Criteria andStartOnIsNotNull() {
            addCriterion("start_on is not null");
            return (Criteria) this;
        }

        public Criteria andStartOnEqualTo(Date value) {
            addCriterion("start_on =", value, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnNotEqualTo(Date value) {
            addCriterion("start_on <>", value, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnGreaterThan(Date value) {
            addCriterion("start_on >", value, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnGreaterThanOrEqualTo(Date value) {
            addCriterion("start_on >=", value, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnLessThan(Date value) {
            addCriterion("start_on <", value, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnLessThanOrEqualTo(Date value) {
            addCriterion("start_on <=", value, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnIn(List<Date> values) {
            addCriterion("start_on in", values, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnNotIn(List<Date> values) {
            addCriterion("start_on not in", values, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnBetween(Date value1, Date value2) {
            addCriterion("start_on between", value1, value2, "startOn");
            return (Criteria) this;
        }

        public Criteria andStartOnNotBetween(Date value1, Date value2) {
            addCriterion("start_on not between", value1, value2, "startOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnIsNull() {
            addCriterion("finish_on is null");
            return (Criteria) this;
        }

        public Criteria andFinishOnIsNotNull() {
            addCriterion("finish_on is not null");
            return (Criteria) this;
        }

        public Criteria andFinishOnEqualTo(Date value) {
            addCriterion("finish_on =", value, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnNotEqualTo(Date value) {
            addCriterion("finish_on <>", value, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnGreaterThan(Date value) {
            addCriterion("finish_on >", value, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_on >=", value, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnLessThan(Date value) {
            addCriterion("finish_on <", value, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnLessThanOrEqualTo(Date value) {
            addCriterion("finish_on <=", value, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnIn(List<Date> values) {
            addCriterion("finish_on in", values, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnNotIn(List<Date> values) {
            addCriterion("finish_on not in", values, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnBetween(Date value1, Date value2) {
            addCriterion("finish_on between", value1, value2, "finishOn");
            return (Criteria) this;
        }

        public Criteria andFinishOnNotBetween(Date value1, Date value2) {
            addCriterion("finish_on not between", value1, value2, "finishOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnIsNull() {
            addCriterion("update_on is null");
            return (Criteria) this;
        }

        public Criteria andUpdateOnIsNotNull() {
            addCriterion("update_on is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateOnEqualTo(Date value) {
            addCriterion("update_on =", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnNotEqualTo(Date value) {
            addCriterion("update_on <>", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnGreaterThan(Date value) {
            addCriterion("update_on >", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnGreaterThanOrEqualTo(Date value) {
            addCriterion("update_on >=", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnLessThan(Date value) {
            addCriterion("update_on <", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnLessThanOrEqualTo(Date value) {
            addCriterion("update_on <=", value, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnIn(List<Date> values) {
            addCriterion("update_on in", values, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnNotIn(List<Date> values) {
            addCriterion("update_on not in", values, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnBetween(Date value1, Date value2) {
            addCriterion("update_on between", value1, value2, "updateOn");
            return (Criteria) this;
        }

        public Criteria andUpdateOnNotBetween(Date value1, Date value2) {
            addCriterion("update_on not between", value1, value2, "updateOn");
            return (Criteria) this;
        }

        public Criteria andExtendInfoIsNull() {
            addCriterion("extend_info is null");
            return (Criteria) this;
        }

        public Criteria andExtendInfoIsNotNull() {
            addCriterion("extend_info is not null");
            return (Criteria) this;
        }

        public Criteria andExtendInfoEqualTo(String value) {
            addCriterion("extend_info =", value, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoNotEqualTo(String value) {
            addCriterion("extend_info <>", value, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoGreaterThan(String value) {
            addCriterion("extend_info >", value, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoGreaterThanOrEqualTo(String value) {
            addCriterion("extend_info >=", value, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoLessThan(String value) {
            addCriterion("extend_info <", value, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoLessThanOrEqualTo(String value) {
            addCriterion("extend_info <=", value, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoLike(String value) {
            addCriterion("extend_info like", value, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoNotLike(String value) {
            addCriterion("extend_info not like", value, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoIn(List<String> values) {
            addCriterion("extend_info in", values, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoNotIn(List<String> values) {
            addCriterion("extend_info not in", values, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoBetween(String value1, String value2) {
            addCriterion("extend_info between", value1, value2, "extendInfo");
            return (Criteria) this;
        }

        public Criteria andExtendInfoNotBetween(String value1, String value2) {
            addCriterion("extend_info not between", value1, value2, "extendInfo");
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