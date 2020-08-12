package com.czxy;

import com.czxy.domain.Teacher;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author JiaLe Pei
 * @Date 2020/6/12 16:08
 * @Version 1.0  520
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMongoApplication.class)
public class TestMongo {

    @Test
    public void testConnection() {
        //创建mongodb 客户端
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        System.out.println(mongoClient);
    }

    /**
     * 查询
     */
    @Test
    public void find() {
        //采用连接字符串
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017/demo");
        MongoClient mongoClient = new MongoClient(connectionString);
        // 连接数据库
        MongoDatabase database = mongoClient.getDatabase("demo");
        // 连接collection
        MongoCollection<Document> collection = database.getCollection("student");
        //查询第一个文档
        Document myDoc = collection.find().first();
        //得到文件内容 json串
        String json = myDoc.toJson();
        System.out.println(json);
    }

    /**
     * 创建集合
     */
    @Test
    public void testCollection() {
        // 采用连接字符串
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017/demo");
        MongoClient mongoClient = new MongoClient(connectionString);
        // 连接数据库
        MongoDatabase database = mongoClient.getDatabase("demo");
        // 创建集合
        database.createCollection("student");
    }

    /**
     * 插入一个文档
     */
    @Test
    public void testDocument() {
        // 采用连接字符串
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017/demo");
        MongoClient mongoClient = new MongoClient(connectionString);
        // 连接数据库
        MongoDatabase database = mongoClient.getDatabase("demo");
        // 获得集合
        MongoCollection<Document> collection = database.getCollection("student");
        // 插入文档
        Document document = new Document();
        document.append("name", "小梁");
        document.append("password", "1234");
        document.append("age",18);
        document.append("edu","大学");
        document.append("description","嘻嘻");
        collection.insertOne(document);
    }

    /**
     * 批量插入文档
     */
    @Test
    public void testAllDocument() {
        // 采用连接字符串
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017/demo");
        MongoClient mongoClient = new MongoClient(connectionString);
        // 连接数据库
        MongoDatabase database = mongoClient.getDatabase("demo");
        // 获得集合
        MongoCollection<Document> collection = database.getCollection("teacher");
        // 插入文档
        Document doc1 = new Document();
        doc1.append("name", "XX老师");
        doc1.append("age", 21);
        doc1.append("marry", "new new ...");

        Document doc2 = new Document();
        doc2.append("name", "XXX老师");
        doc2.append("age", 20);
        doc2.append("marry", "Done ...");
        List<Document> documentList = new ArrayList<>();
        documentList.add(doc1);
        documentList.add(doc2);
        collection.insertMany(documentList);
    }

    /**
     * 查询所有
     */
    @Test
    public void testSelectAll() {
        // 采用连接字符串
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017/demo");
        MongoClient mongoClient = new MongoClient(connectionString);
        // 连接数据库
        MongoDatabase database = mongoClient.getDatabase("demo");
        // 获得集合
        MongoCollection<Document> collection = database.getCollection("teacher");
        //查询所有
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            Document document = mongoCursor.next();
            String name = document.get("name", String.class);
            Integer age = document.get("age", Integer.class);
            String marry = document.get("marry", String.class);
            System.out.println(name + "_" + age + "_" + marry);
        }
    }

    /**
     * 更新文档
     */
    @Test
    public void testUpdate() {
        // 采用连接字符串
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017/demo");
        MongoClient mongoClient = new MongoClient(connectionString);
        // 连接数据库
        MongoDatabase database = mongoClient.getDatabase("demo");
        // 获得集合
        MongoCollection<Document> collection = database.getCollection("teacher");
        // 更新
        collection.updateOne(Filters.eq("age",20), new Document("$set", new Document("name","YY老师")));
    }

    /**
     * 删除文档
     */
    @Test
    public void testDelete() {
        // 采用连接字符串
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017/demo");
        MongoClient mongoClient = new MongoClient(connectionString);
        // 连接数据库
        MongoDatabase database = mongoClient.getDatabase("demo");
        // 获得集合
        MongoCollection<Document> collection = database.getCollection("teacher");
        // 删除
        collection.deleteOne(Filters.eq("age",20));
    }


        @Test
        public void run (){
            List<Teacher> list = new ArrayList();
            list.add(new Teacher("30","jack",20,"done"));
            list.add(new Teacher("31","rose",20,"done"));
            list.add(new Teacher("32","mary",20,"done"));
            list.add(new Teacher("33","loce",20,"done"));

            for (Teacher teacher : list) {
                System.out.println("第一种"+teacher);
            }

            for (int i = 0; i < list.size(); i++) {
                System.out.println("第二种"+list.get(i));
            }

            Iterator<Teacher> it = list.iterator();
            while (it.hasNext()){
                Teacher next = it.next();
                System.out.println("第三种"+next);
            }

            list.forEach((item->{
                System.out.println("第四种"+item);
            }));

        }
}
