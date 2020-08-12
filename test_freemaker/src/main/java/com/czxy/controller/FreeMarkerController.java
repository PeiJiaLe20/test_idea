package com.czxy.controller;

import com.alibaba.fastjson.JSON;
import com.czxy.dao.TeacherRepository;
import com.czxy.domain.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author JiaLe Pei
 * @Date 2020/6/22 16:43
 * @Version 1.0
 */
@Controller
public class FreeMarkerController {
    @Resource
    private TeacherRepository teacherRepository;


    @GetMapping("/testpath")
    public String testMethod() {
        return "test";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Teacher> list = teacherRepository.findAll();
        model.addAttribute("AllTeacher", list);
        //设置视图（页面）
        return "list";
    }
    @GetMapping("/model_a")
    public ModelAndView model_a(){
        ModelAndView modelAndView = new ModelAndView();
        //设置模型（数据）
        Map<String,String> map = new HashMap<>();
        map.put("name","jack");
        map.put("age","18");
        modelAndView.addObject("user" , map);
        //设置视图（页面）
        modelAndView.setViewName("model_a");
        return modelAndView;
    }

    @GetMapping("/model_b")
    public String model_b(Model model){
        //设置模型（数据）
        Map<String,String> map = new HashMap<>();
        map.put("name","jack");
        map.put("age","19");
        model.addAttribute("user",map);
        //设置视图（页面）
        return "model_b";
    }

    @GetMapping("/model_c")
    public String model_c(Map map){
        //设置模型（数据）
        Map<String,String> userMap = new HashMap<>();
        userMap.put("name","jack");
        userMap.put("age","20");
        map.put("user",userMap);
        //设置视图（页面）
        return "model_c";
    }
    @GetMapping("/map")
    public String map(Model model){
        //设置模型（数据）
        Map<String,Teacher> map = new HashMap<>();
        map.put("user1", new Teacher("jack","1234",18,"22"));
        map.put("user2", new Teacher("rose","5678",21,"33"));
        model.addAttribute("allUser", map);
        //设置视图（页面）
        return "map";
    }
    @GetMapping("/if_path")
    public String _if(Map<String,Object> map){
        map.put("token",1234);
        map.put("token2","5678");
        return "if";
    }

    @GetMapping("/method")
    public String method(Model model){
        //设置模型（数据）
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("123");
        model.addAttribute("list", list);
        model.addAttribute("birthday", new Date());
        model.addAttribute("money",12345678);
        model.addAttribute("text", JSON.toJSON(new Teacher("rose","5678",21,"33")) );

        //设置视图（页面）
        return "method";
    }
    @GetMapping("/test2")
    public String test2(Model model){
        //存放字符串类型
        model.addAttribute("token","1234");
        //整数
        model.addAttribute("num",100);

        return "test2";
    }

}
