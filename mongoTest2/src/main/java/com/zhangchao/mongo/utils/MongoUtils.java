package com.zhangchao.mongo.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


/**
 *  工具类  服务启动、获取数据库、关闭连接
 */
public class MongoUtils {
    private static final String ADDR = "mongodb://zhangch:root@localhost:27017/test";
    private static final String DBNAME = "test";

    /**
     *  获取服务
     * @return
     */
    public static MongoClient getMongoClient(){
        MongoClient client = null;
        try {
            System.out.println("--------------------------------获取服务--------------------------------");
            MongoClientURI uri = new MongoClientURI(ADDR);
            client = new MongoClient(uri);
        }catch ( Exception e ){
            System.out.println("--------------------------------无法获取服务--------------------------------");
            e.printStackTrace();
        }
        return client;
    }

    /**
     *  连接数据库
     * @param client
     * @return
     */
    public static MongoDatabase getDatabase(MongoClient client){
        MongoDatabase database = null;
        try {
            System.out.println("--------------------------------开始连接数据库--------------------------------");
            database = client.getDatabase(DBNAME);
        }catch ( Exception e ){
            System.out.println("--------------------------------连接数据库失败--------------------------------");
            e.printStackTrace();
        }
        return database;
    }

    /**
     * 关闭连接
     * @param client
     */
    public static void close(MongoClient client){
        try {
            if ( client != null ){
                client.close();
                System.out.println("--------------------------------关闭--------------------------------");
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }
}
