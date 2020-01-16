package com.community.model;

public class MApplyCommunityView {
    private Integer m_id;
    private Integer co_id;
    private Integer u_id;
    private Integer m_status;
    private String co_name;
    private String co_ldname;
    private String co_ldtel;

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getM_status() {
        return m_status;
    }

    public void setM_status(Integer m_status) {
        this.m_status = m_status;
    }

    public String getCo_name() {
        return co_name;
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }

    public String getCo_ldname() {
        return co_ldname;
    }

    public void setCo_ldname(String co_ldname) {
        this.co_ldname = co_ldname;
    }

    public String getCo_ldtel() {
        return co_ldtel;
    }

    public void setCo_ldtel(String co_ldtel) {
        this.co_ldtel = co_ldtel;
    }
}