package com.hawk.qrcode.barcode.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2019-06-11.
 */
public class Data {

    List<House> list=new ArrayList<>();
    private Integer id;
    private String buddingname;
    private String address;
    private String propertyunit;
    private String region;

    String Status;

    public List<House> getList() {
        return list;
    }

    public void setList(List<House> list) {
        this.list = list;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuddingname() {
        return buddingname;
    }

    public void setBuddingname(String buddingname) {
        this.buddingname = buddingname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyunit() {
        return propertyunit;
    }

    public void setPropertyunit(String propertyunit) {
        this.propertyunit = propertyunit;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
