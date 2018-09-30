package com.zhangchao.mongo.dao;

import com.zhangchao.mongo.entity.Student;

/**
 * @author zc
 */
public interface MongoTempDao{

    /**
     *  保存
     *  默认会将实体类名小写作为集合名字
     * @param student
     */
    void save(Student student);





}
