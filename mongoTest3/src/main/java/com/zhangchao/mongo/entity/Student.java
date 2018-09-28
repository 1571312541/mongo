package com.zhangchao.mongo.entity;

import java.util.Date;

public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Date createTime;

    public Student() {
    }

    public Student( String name, Integer age, String gender, Date createTime) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
