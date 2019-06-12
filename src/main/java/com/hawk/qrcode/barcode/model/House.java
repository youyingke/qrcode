package com.hawk.qrcode.barcode.model;

/**
 * Created by Lenovo on 2019-06-11.
 */
public class House {



    private Integer id;
    private String buddingname;
    private String address;
    private String propertyunit;
    private String region;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public House() {
    }

    public House(String buddingname, String address, Integer id, String propertyunit, String region) {
        this.buddingname = buddingname;
        this.address = address;
        this.id = id;
        this.propertyunit = propertyunit;
        this.region = region;
    }
}
