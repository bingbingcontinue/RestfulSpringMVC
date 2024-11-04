package com.learndemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：bingfeng
 * @Date：2024/11/1 15:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String lastName;

    private String email;
    //1 male, 0 female
    private Integer gender;
}
