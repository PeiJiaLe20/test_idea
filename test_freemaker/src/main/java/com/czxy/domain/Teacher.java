package com.czxy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author JiaLe Pei
 * @Date 2020/6/14 16:32
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "teacher")
public class Teacher {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String marry;
}
