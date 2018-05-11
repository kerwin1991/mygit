package com.wxp.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ConfigurationProperties 默认从全局配置文件中获取配置
 * @PropertySource 映射指定配置文件中的配置
 *
 * 导入spring的配置文件，让配置文件里面的内容生效
 *
 *
 * */
@Component
@PropertySource(value = {"classpath:person.properties"})
@ConfigurationProperties(prefix = "person")
@Validated
public class PersonOne {
	private String lastName;
	private Date birthday;
	private int age;
	private Map<String, Object> kvs;
	private List<String> likes;

	@Email
	private String email; // 必须是邮箱格式

	private Dog dog;

	@Override
	public String toString() {
		return "Person{" +
				"lastName='" + lastName + '\'' +
				", birthday=" + birthday +
				", age=" + age +
				", kvs=" + kvs +
				", likes=" + likes +
				", email='" + email + '\'' +
				", dog=" + dog +
				'}';
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Map<String, Object> getKvs() {
		return kvs;
	}

	public void setKvs(Map<String, Object> kvs) {
		this.kvs = kvs;
	}

	public List<String> getLikes() {
		return likes;
	}

	public void setLikes(List<String> likes) {
		this.likes = likes;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}
}
