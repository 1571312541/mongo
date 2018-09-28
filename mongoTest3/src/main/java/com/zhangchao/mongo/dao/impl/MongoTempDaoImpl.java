package com.zhangchao.mongo.dao.impl;

import com.zhangchao.mongo.dao.MongoTempDao;
import com.zhangchao.mongo.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.Date;

public class MongoTempDaoImpl implements MongoTempDao<Student> {

    @Resource
    private MongoTemplate template;

    @Override
    public void save(Student student) {
        template.save(student);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_mongo.xml");
        MongoTempDao dao = context.getBean(MongoTempDao.class);
        dao.save(new Student("zhangsan",20,"男",new Date()));
        MongoTempDaoImpl t = new MongoTempDaoImpl();
//        t.save(new Student("zhangsan",20,"男",new Date()));
    }
}
