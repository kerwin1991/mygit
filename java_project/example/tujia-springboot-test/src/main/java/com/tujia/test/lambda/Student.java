package com.tujia.test.lambda;

/**
 * Created by xiaopengw on 2018/7/18.
 */
public class Student {
    private Long id;
    private Integer age;
    private String name;
    private Character gender;

    public Student() {
    }

    public Student(Long id, Integer age, String name, Character gender) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }

    public static void main(String[] args) {

    }
}
