package com.zhangchao.mongo.main;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * mongo简单连接测试
 */
public class MongoApp {
    public static void main(String[] args){
        try {
            //连接mongo服务器(3后这么连接，传统uri mongodb:// 账号 ：密码 @ IP：端口号 / 数据库)
            //如果没有账号密码
//          MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/test");
            //如果有账号密码
            MongoClientURI uri = new MongoClientURI("mongodb://zhangch:root@localhost:27017/test");
            MongoClient client = new MongoClient(uri);
            System.out.println("连接mongo服务器成功");
            //连接test数据库
            MongoDatabase db = client.getDatabase("test");
            System.out.println("连接test数据库成功"+db.getName());
            //当前数据库中所有集合
            for (String  name:  db.listCollectionNames()) {
                System.out.print(name+" ,");
            }
            //选择zhangchao集合
            MongoCollection<Document> collection = db.getCollection("zhangchao");
            //向zhangchao集合插入一条数据
            Document document = new Document("name", "测试").append("age", 30).append("gender", "男");
            collection.insertOne(document);
            //遍历zhangchao集合--- 方法1
            collection.find().forEach(new Block<Document>() {
                @Override
                public void apply(Document document) {
                    System.out.println(document.toJson());
                }
            });
            System.out.println("--------------------------------------------------------------");
            //遍历zhangchao集合--- 方法2
            MongoCursor<Document> iterator = collection.find().iterator();
            while ( iterator.hasNext() ){
                System.out.println(iterator.next().toJson());
            }
            client.close();
            System.out.println("mongo服务器关闭成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
