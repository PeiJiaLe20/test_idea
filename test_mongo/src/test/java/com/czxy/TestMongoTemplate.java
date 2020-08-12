package com.czxy;

import com.czxy.dao.TeacherRepository;
import com.czxy.domain.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author JiaLe Pei
 * @Date 2020/6/22 10:23
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMongoApplication.class)

public class TestMongoTemplate {
    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void run() {
        Query query = new Query();
        query.addCriteria(Criteria.where("age").is(21));
        List<Teacher> teachers = mongoTemplate.find(query, Teacher.class);
        System.out.println(teachers);

    }

    @Test
    public void run1() {
        Query query = new Query();
        //查询age 大于25

        query.addCriteria(Criteria.where("age").gt(25));
        List<Teacher> teachers = mongoTemplate.find(query, Teacher.class);
        System.out.println(teachers);

    }

    @Test
    public void run2() {
        //分页
        int pageNum = 0;
        int pageSize = 2;
        PageRequest pagequest = PageRequest.of(pageNum, pageSize);
        //查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("age").is(21));
        //查询总条数
        long count = mongoTemplate.count(query, Teacher.class);
        System.out.println(count);
        //添加分页条件
        query.with(pagequest);
        List<Teacher> teachers = mongoTemplate.find(query, Teacher.class);
        System.out.println(teachers);

    }
    @Resource
    private TeacherRepository teacherRepository;
    @Test
    public void testQuery2() {
        Teacher teacher = new Teacher();
        teacher.setName("师");
        //2 查询条件
        // 2.1 设置匹配器，用于确定那个字段进行什么查询（例如：模糊查询）
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        // 2.2 创建Example条件对象
        Example<Teacher> example = Example.of(teacher, matcher);
        // 3 查询
        List<Teacher> list = teacherRepository.findAll(example);
        // 4 处理结果
        list.forEach(System.out::println);
    }

}
