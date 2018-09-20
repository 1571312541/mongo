package com.zhangchao.mongo.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.io.Closeable;

/**
 *  工具类  服务启动、获取数据库、关闭连接
 */
public class MongoUtils {

    private static final String ADDR = "mongodb://zhangch:root@localhost:27017/test";
    private static final String DBNAME = "test";

    public static MongoClient getMongoClient(){
        MongoClient client = null;
        try {
            MongoClientURI uri = new MongoClientURI(ADDR);
            client = new MongoClient(uri);
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return client;
    }

    public static MongoDatabase getDatabase(MongoClient client){
        MongoDatabase database = null;
        try {
            database = client.getDatabase(DBNAME);
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return database;
    }

    public static void close(MongoClient client){
        try {
            if ( client != null ){
                client.close();
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }
}
