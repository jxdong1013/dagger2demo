package com.jxd.dagger2demo.entity;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Administrator on 2017/8/31.
 */

public class User  extends RealmObject{
    @PrimaryKey
    private String userid;
    @Required
    private String username;
    @Required
    private String password;
    private String age;
    private String address;
    private String job;
    private String company;
    private String phone;
    private RealmList<Address> addressesList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public RealmList<Address> getAddressesList() {
        return addressesList;
    }

    public void setAddressesList(RealmList<Address> addressesList) {
        this.addressesList = addressesList;
    }
}
