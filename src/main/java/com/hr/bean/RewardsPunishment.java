package com.hr.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖惩记录表
 * @author Hanniel_Yang
 */
@TableName("rewards_punishment")
public class RewardsPunishment extends Model<RewardsPunishment> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;
    private Integer employeeNumber;
    private String type;
    private String reason;
    private Float money;
    private Date time;
    private String manager;
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "RewardsPunishment{" +
                "id=" + id +
                ", employeeNumber=" + employeeNumber +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", money=" + money +
                ", time=" + time +
                ", manager='" + manager + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}