package com.zhangchao.mongo.dao.impl;

import com.zhangchao.mongo.dao.MongoTempDao;
import com.zhangchao.mongo.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.annotation.Resource;
import java.util.Date;

public class MongoTempDaoImpl implements MongoTempDao {

    @Resource
    private MongoOperations mongoTemplate;

    @Override
    public void save(Student student) {
        mongoTemplate.save(student);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring_mongo.xml");
        MongoOperations mongoTemplate = (MongoOperations) context.getBean("mongoTemplate");
        mongoTemplate.save(new Student("zhangsan",20,"男",new Date()));



    }
}
