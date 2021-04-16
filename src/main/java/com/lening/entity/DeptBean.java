package com.lening.entity;

import java.util.List;

public class DeptBean {
    private Long id;

    private String deptname;

    private List<PostBean> dept;

    private List<DeptBean> depts;

    private Long[] deptidss;

    private Long[] postids;

    private List<PostBean> Plist;

    public Long[] getDeptidss() {
        return deptidss;
    }

    public void setDeptidss(Long[] deptidss) {
        this.deptidss = deptidss;
    }

    public Long[] getPostids() {
        return postids;
    }

    public void setPostids(Long[] postids) {
        this.postids = postids;
    }

    public List<PostBean> getPlist() {
        return Plist;
    }

    public void setPlist(List<PostBean> plist) {
        Plist = plist;
    }

    public List<PostBean> getDept() {
        return dept;
    }

    public void setDept(List<PostBean> dept) {
        this.dept = dept;
    }


    public List<DeptBean> getDepts() {
        return depts;
    }

    public void setDepts(List<DeptBean> depts) {
        this.depts = depts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

}