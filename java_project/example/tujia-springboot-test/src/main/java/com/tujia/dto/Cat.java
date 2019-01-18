package com.tujia.dto;

/**
 * @author xiaopengw
 * @date 2018/11/29
 * @remark
 */
public class Cat {


    /**
     * address : {"province":"100","city":"10"}
     * age : 20
     * name : 笑话
     */

    private Address address;
    private int age;
    private String name;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Address {
        /**
         * province : 100
         * city : 10
         */

        private int province;
        private int city;

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }
    }
}
