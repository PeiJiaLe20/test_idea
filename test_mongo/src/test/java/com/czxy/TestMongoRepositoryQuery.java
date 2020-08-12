package com.czxy;

import com.czxy.dao.TeacherRepository;
import com.czxy.domain.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author JiaLe Pei
 * @Date 2020/6/25 15:37
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMongoApplication.class)
public class TestMongoRepositoryQuery {
    @Resource
    private TeacherRepository teacherRepository;

    /**
     * 模糊查询
     */
    @Test
    public void testQuery2() {
        Teacher teacher = new Teacher();
        teacher.setName("师");
        //2 查询条件
        // 2.1 模糊查询 包含 "师"
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        // 2.2 创建Example条件对象
        Example<Teacher> example = Example.of(teacher, matcher);
        // 3 查询
        List<Teacher> list = teacherRepository.findAll(example);
        // 4 处理结果
        list.forEach(System.out::println);
    }

    /**
     * 精确查询
     */
    @Test
    public void testQuery1(){
        //1 设置查询数据
        Teacher teacher = new Teacher();
        teacher.setAge(21);
        //2 创建Example条件对象
        Example<Teacher> example = Example.of(teacher);
        //3 查询
        List<Teacher> list = teacherRepository.findAll(example);
        //4 打印
        list.forEach(System.out::println);
    }
}
