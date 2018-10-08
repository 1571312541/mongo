package com.zhangchao.mongo.main;

import com.mongodb.WriteResult;
import com.zhangchao.mongo.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zc
 */
public class MongoTemplateTest{

    @Resource
    private static MongoOperations mongoTemplate;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring_mongo.xml");
        mongoTemplate = (MongoOperations) context.getBean("mongoTemplate");
    }

    /**
     *  insert插入
     */
    @Test
    public void insertone(){
        //insert: 若新增数据的主键已经存在，则会抛 org.springframework.dao.DuplicateKeyException 异常提示主键重复，不保存当前数据
       mongoTemplate.insert(new Student( "dsfsdfsdfsdfsde","王五2",15,"女",new Date()),"student");
    }
    /**
     *  save插入
     */
    @Test
    public void save(){
        //save若新增数据的主键已经存在，则会对当前已经存在的数据进行修改操作
        mongoTemplate.save(new Student( "wwwwwwwwwwww","宋九",21,"男",new Date()),"student");
    }

    /**
     *  查询所有
     */
    @Test
    public void find() {
        List<Student> all = mongoTemplate.findAll(Student.class,"student");
        for (Student s:all
             ) {
            System.out.println(s);
        }
    }
    /**
     * 条件查询
     */
    @Test
    public void find1(){
        Query query = new Query(Criteria.where("name").is("王五").and("age").gte(20).lte(30));
        List<Student> student = mongoTemplate.find(query, Student.class, "student");
        for (Student s :
                student) {
            System.out.println(s);
        }
    }
    /**
     * 排序查询(按年龄倒序)
     */
    @Test
    public void find2(){
        Query query = new Query();
        Query q = query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "age")));
        List<Student> student = mongoTemplate.find(q, Student.class, "student");
        for (Student s :
                student) {
            System.out.println(s);
        }
    }
    /**
     * 删除(名字包含赵)
     */
    @Test
    public void delete(){
        Query query = new Query(Criteria.where("name").regex("赵"));
        WriteResult result = mongoTemplate.remove(query, "student");
        System.out.println(result);
    }
    /**
     * 修改
     * 如果query条件没有筛选出对应的数据，那么upsert会插入一条新的数据，而update则什么都不会做
     */
    @Test
    public void update(){
        Query query = new Query(Criteria.where("age").ne(25));
        Update update = Update.update("gender","女");
//        更新满足条件的第一条数据
//        WriteResult result = mongoTemplate.updateFirst(query, update, "student");
//        System.out.println(result);
//        更新所有满足条件的数据
        WriteResult result1 = mongoTemplate.updateMulti(query, update, "student");
        System.out.println(result1);

    }
    @Test
    public void upsert(){
        Query query = new Query(Criteria.where("age").is(35));
        Update update = Update.update("gender","男");
        WriteResult result = mongoTemplate.upsert(query, update, "student");
        System.out.println(result);

    }


}
