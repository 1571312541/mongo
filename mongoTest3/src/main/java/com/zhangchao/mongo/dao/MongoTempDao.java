package com.zhangchao.mongo.dao;


public interface MongoTempDao<T> {

    /**
     * 保存
     * 默认会将实体类名小写作为集合名字
     * @param t
     */
    void save(T t);





}
