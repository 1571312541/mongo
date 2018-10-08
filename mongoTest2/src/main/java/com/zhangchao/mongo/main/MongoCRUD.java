package com.zhangchao.mongo.main;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.zhangchao.mongo.utils.MongoUtils;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *  测试CRUD的类
 */
public class MongoCRUD {
    /**
     *  测试添加 （添加一条）
     * @param database
     * @param collectionName
     */
    public void addOne(MongoDatabase database,String collectionName){
        //获取要操作的集合
        MongoCollection<Document> coll = database.getCollection(collectionName);
        Document doc = new Document();
        doc.append("name","gewas").append("age","26").append("gender","女").append("class","二班");
        coll.insertOne(doc);
    }
    /**
     *  测试添加 （添加多条）
     * @param database
     * @param collectionName
     */
    public void addMany(MongoDatabase database,String collectionName){
        //获取要操作的集合
        MongoCollection<Document> coll = database.getCollection(collectionName);
        List<Document> list = new ArrayList<>();
        for ( int i = 0 ; i < 5 ; i ++ ){
            Document doc = new Document();
            doc.append("name","yuas"+i).append("age",20+i).append("gender","女");
            list.add(doc);
        }
        coll.insertMany(list);
    }
    /**
     *  测试添加 （添加多条）
     * @param database
     * @param collectionName
     */
    public void addMany2(MongoDatabase database,String collectionName){
        //获取要操作的集合
        MongoCollection<Document> coll = database.getCollection(collectionName);
        List<Document> list = new ArrayList<>();
        for ( int i = 5 ; i < 10 ; i ++ ){
            Document doc = new Document();
            Map map = new HashMap();
            map.put("name","dssss"+i);
            map.put("age",20+i);
            map.put("gender","男");
            map.put("class","三班");
            doc.putAll(map);
            list.add(doc);
        }
        coll.insertMany(list);
    }
    /**
     *  测试查询 （查询数据库中所有数据）
     * @param database 操作的数据库
     * @param collectionName 操作的集合名
     */
    public void findAll(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        FindIterable<Document> documents = coll.find();
        MongoCursor<Document> cursor = documents.iterator();
        while ( cursor.hasNext() ){
            System.out.println(cursor.next().toJson());
        }
    }

    /**
     *  测试查询 （从数据库中第三个数据开始查询，查询8个）
     * @param database 操作的数据库
     * @param collectionName 操作的集合名
     */
    public void find(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        FindIterable<Document> documents = coll.find().skip(2).limit(8);
        MongoCursor<Document> cursor = documents.iterator();
        while ( cursor.hasNext() ){
            System.out.println(cursor.next().toJson());
        }
    }
    /**
     *  测试查询 （查询数据库中所有数据，按照年龄升序 1 升序，-1 降序）
     *  skip(), limilt(), sort()三个放在一起执行的时候，执行的顺序是先 sort(), 然后是 skip()，最后是显示的 limit()
     * @param database 操作的数据库
     * @param collectionName 操作的集合名
     */
    public void find1(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        FindIterable<Document> documents = coll.find().sort(new BasicDBObject("age",1));
        MongoCursor<Document> cursor = documents.iterator();
        while ( cursor.hasNext() ){
            System.out.println(cursor.next().toJson());
        }
    }
    /**
     *  测试查询 (查询数据库中年龄为30，性别为男的数据)
     * @param database 操作的数据库
     * @param collectionName 操作的集合名
     */
    public void find2(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        BasicDBObject DBObject = new BasicDBObject("age","30");
        DBObject.append("gender", "男");
        FindIterable<Document> documents = coll.find(DBObject);
        MongoCursor<Document> cursor = documents.iterator();
        while ( cursor.hasNext() ){
            System.out.println(cursor.next().toJson());
        }
    }
    /**
     *  测试查询 (查询数据库中年龄大于10，小于30，并且性别为男的数据)
     * @param database 操作的数据库
     * @param collectionName 操作的集合名
     */
    public void find3(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        BasicDBObject DBObject = new BasicDBObject("gender","男");
        DBObject.put("age",new BasicDBObject("$lt","30").append("$gte","10"));
        FindIterable<Document> documents = coll.find(DBObject);
        documents.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        });
    }
    /**
     *  测试查询 (查询数据库中姓名包含as的数据，不区分大小写)
     * @param database 操作的数据库
     * @param collectionName 操作的集合名
     */
    public void find4(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        BasicDBObject DBObject = new BasicDBObject();
        Pattern compile = Pattern.compile("as");
        DBObject.put("name",new BasicDBObject("$regex",compile).append("$options","$i"));
        FindIterable<Document> documents = coll.find(DBObject);
        documents.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        });
    }
    /**
     *  删除一个 （删除name是dssss3的数据）
     * @param database
     * @param collectionName
     */
    public void deleteOne(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append("name","dssss3");
        DeleteResult result = coll.deleteOne(dbObject);
        System.out.println("删除结果"+result.getDeletedCount());
    }

    /**
     *  删除多个 （删除性别是男，年龄大于22，小于28的数据）
     * @param database
     * @param collectionName
     */
    public void deleteMany(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        BasicDBObject dbObject = new BasicDBObject("gender","男");
        dbObject.put("age",new BasicDBObject().append("$gt",22).append("$lt",28));
        DeleteResult result = coll.deleteMany(dbObject);
        System.out.println("删除结果"+result.getDeletedCount());
    }

    /**
     *  修改一个 （把年龄为29的 修改）
     * @param database
     * @param collectionName
     */
    public void updateOne(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        BasicDBObject bson = new BasicDBObject("age", 29);
        BasicDBObject bson2 = new BasicDBObject();
        bson2.put("$set",new BasicDBObject("age",33).append("name","ssssd").append("gender","女").append("class","四班"));
        UpdateResult result = coll.updateOne(bson, bson2);
        System.out.println("修改结果"+result);
    }
    /**
     *  修改多个 （把年龄大于29的 性别都改为男 ）
     * @param database
     * @param collectionName
     */
    public void updateMany(MongoDatabase database,String collectionName){
        MongoCollection<Document> coll = database.getCollection(collectionName);
        BasicDBObject bson = new BasicDBObject();
        bson.put("age",new BasicDBObject("$gt",29));
        BasicDBObject bson2 = new BasicDBObject();
        bson2.put("$set",new BasicDBObject("gender","男"));
        UpdateResult result = coll.updateMany(bson, bson2);
        System.out.println("修改结果"+result.getModifiedCount());
    }
    public static void main(String[] args) {
        String collectionName = "zhangchao";
        MongoClient client = MongoUtils.getMongoClient();
        MongoDatabase database = MongoUtils.getDatabase(client);
        MongoCRUD mongo = new MongoCRUD();
        mongo.findAll(database,collectionName);
        MongoUtils.close(client);

    }

}
