package com.zhangchao.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author zc
 */
@Document(collection = "student") //注解对应表名
public class Student {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private Integer age;
    @Field
    private String gender;
    @Field
    private Date createTime;

    public Student() {
    }

    public Student( String name, Integer age, String gender, Date createTime) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.createTime = createTime;
    }
    public Student( String id, String name, Integer age, String gender, Date createTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.createTime = createTime;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
