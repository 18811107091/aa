package com.lening.entity;

import java.util.Date;
import java.util.List;

public class UserBean{
    private Long id;

    private String uname;

    private String pwd;

    private Integer age;

    private Integer eage;

    private Date birthday;

    private String telphone;

    private String address;

    private String gender;

    private String pwdsalt;

    private String accountname;

    private List<UserBean> depts;

    private List<DeptBean> deptBeans;

    private List<DeptBean> dlist;

    private List<Long> deptss;

    private Integer deptidss;

    public Integer getDeptidss() {
        return deptidss;
    }

    public void setDeptidss(Integer deptidss) {
        this.deptidss = deptidss;
    }

    public List<DeptBean> getDlist() {
        return dlist;
    }

    public void setDlist(List<DeptBean> dlist) {
        this.dlist = dlist;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public List<Long> getDeptss() {
        return deptss;
    }

    public void setDeptss(List<Long> deptss) {
        this.deptss = deptss;
    }

    public List<UserBean> getDepts() {
        return depts;
    }

    public void setDepts(List<UserBean> depts) {
        this.depts = depts;
    }

    public List<DeptBean> getDeptBeans() {
        return deptBeans;
    }

    public void setDeptBeans(List<DeptBean> deptBeans) {
        this.deptBeans = deptBeans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPwdsalt() {
        return pwdsalt;
    }

    public void setPwdsalt(String pwdsalt) {
        this.pwdsalt = pwdsalt == null ? null : pwdsalt.trim();
    }

    public Integer getEage() {
        return eage;
    }

    public void setEage(Integer eage) {
        this.eage = eage;
    }

}