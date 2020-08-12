package com.czxy;

import com.czxy.dao.TeacherRepository;
import com.czxy.domain.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author JiaLe Pei
 * @Date 2020/6/14 16:37
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMongoApplication.class)
public class TestRepository {

    @Resource
    private TeacherRepository teacherRepository;

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        //查询
        List<Teacher> list = teacherRepository.findAll();
        System.out.println(list);
    }

    /**
     * 通过id查询
     */
    @Test
    public void findById(){
        Optional<Teacher> optional = teacherRepository.findById("5ee83ccf9d1c6904d8768ca7");
        if(optional.isPresent()){
            Teacher teacher = optional.get();
            System.out.println(teacher);
        } else {
            System.out.println("没有数据");
        }
    }

    /**
     *  排序
     */
    @Test
    public void findByDesc(){
        List<Teacher> list = teacherRepository.findAll(Sort.by(Sort.Order.desc("age")));
        for (Teacher teacher : list) {
            System.out.println(teacher);
        }

    }
    /**
     * 添加
     */
    @Test
    public void testInsert() {
        Teacher teacher = new Teacher();
        teacher.setName("CC老师");
        teacher.setAge(25);
        teacher.setMarry("女昏");
        teacherRepository.insert(teacher);
    }

    /**
     * 更新
     */
    @Test
    public void testUpdate() {
        Optional<Teacher> optional = teacherRepository.findById("5ede4bf43335a82d386f49ff");
        if (optional.isPresent()) {
            Teacher teacher = optional.get();
            teacher.setName("CC老师");
            teacherRepository.save(teacher);
        }
    }

    /**
     * 删除
     */
    @Test
    public void testDelete() {
        teacherRepository.deleteById("5ede57fd3335a8258875e0f2");
    }

    /**
     * 分页
     */
    @Test
    public void testPage() {
        int page = 0; //从0开始
        int size = 2;//每页记录数
        PageRequest pageRequest = PageRequest.of(page, size);
        //查询
        Page<Teacher> all = teacherRepository.findAll(pageRequest);
        // 总条数
        System.out.println(all.getTotalElements());
        // 当前页信息
        all.forEach(teacher -> {
            System.out.println(teacher);
        });
        // 将stream转换成List
        List<Teacher> list = all.get().collect(Collectors.toList());
        System.out.println(list);
    }


    @Test
    public void testDao(){
//        Teacher teacher = teacherRepository.findByName("CC老师");
//        System.out.println(teacher);
//
//        List<Teacher> list = teacherRepository.findByNameLike("老师");
//        System.out.println(list);
//
//        List<Teacher> list2 = teacherRepository.findByNameLikeAndAge("老师", 18);
//        System.out.println(list2);

        PageRequest pageRequest = PageRequest.of(0, 1);
        Page<Teacher> page = teacherRepository.findByNameLikeAndAge("老师", 18, pageRequest);
        List<Teacher> list3 = page.get().collect(Collectors.toList());
        System.out.println(list3);

    }
    @Test
    public void findByExample() {

        Teacher teacher = new Teacher();
        teacher.setName("CC老师");
        teacher.setAge(25);
        teacher.setMarry("女昏");
        Example<Teacher> exam = Example.of(teacher, generateStringContainingAndNullIgnoreMatcher());
        System.out.println(exam.getMatcher());
        System.out.println(exam.getProbe());
        System.out.println(exam.getProbeType());


    }
    private ExampleMatcher generateStringContainingAndNullIgnoreMatcher() {
        return ExampleMatcher.matching()
                .withMatcher("name", match->match.exact()) // 姓名精确查询
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // 其他为模糊查询
                .withIgnoreNullValues();
    }

}
