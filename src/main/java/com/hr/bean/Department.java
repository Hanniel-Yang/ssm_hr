package com.hr.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 部门表
 * @author Hanniel_Yang
 */
@TableName("department")
public class Department  extends Model<Department> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;
    private Integer departmentNumber;
    private String name;
    private String manager;
    private String telephone;
    private String address;
    private String notes;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Integer departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        return "Department{" +
                "id=" + id +
                ", departmentNumber=" + departmentNumber +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}