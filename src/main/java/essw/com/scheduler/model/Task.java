package essw.com.scheduler.model;

import java.util.Date;

public class Task {
    private Integer id;

    private Date dealDatetime;

    private String taskType;

    private String handleObj;

    private Integer taskStatus;

    private Date addOn;

    private Date startOn;

    private Date finishOn;

    private Date updateOn;

    private String extendInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDealDatetime() {
        return dealDatetime;
    }

    public void setDealDatetime(Date dealDatetime) {
        this.dealDatetime = dealDatetime;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getHandleObj() {
        return handleObj;
    }

    public void setHandleObj(String handleObj) {
        this.handleObj = handleObj == null ? null : handleObj.trim();
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getAddOn() {
        return addOn;
    }

    public void setAddOn(Date addOn) {
        this.addOn = addOn;
    }

    public Date getStartOn() {
        return startOn;
    }

    public void setStartOn(Date startOn) {
        this.startOn = startOn;
    }

    public Date getFinishOn() {
        return finishOn;
    }

    public void setFinishOn(Date finishOn) {
        this.finishOn = finishOn;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo == null ? null : extendInfo.trim();
    }
}