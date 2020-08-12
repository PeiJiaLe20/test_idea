package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author JiaLe Pei
 * @Date 2020/6/17 9:25
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "student")
public class Student {
    @Id
    private String sid;
    private String name;
    private String password;
    private Integer age;
    private String edu;
    private String description;
}
