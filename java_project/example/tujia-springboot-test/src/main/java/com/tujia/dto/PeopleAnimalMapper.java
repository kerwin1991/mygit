package com.tujia.dto;

import java.util.function.Function;

/**
 * @author xiaopengw
 * @date 2018/8/23
 * @remark
 */
public class PeopleAnimalMapper implements Function<People, Animal> {

    /**
     * 集合中元素 people 转换为 animal
     * @param people
     * @return
     */
    @Override
    public Animal apply(People people) {
        if (people.getGender() == 18) return null;
        return new Animal(people.getUsername(), people.getGender());
    }
}
