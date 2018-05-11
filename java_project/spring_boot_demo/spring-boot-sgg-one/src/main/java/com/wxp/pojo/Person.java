package com.wxp.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxiaopeng on 2018/5/10.
 * @ConfigurationProperties 告诉springboot 将本类中的所有属性和配置文件中相关的配置进行绑定
 * prefix = "person" yalm 配置文件中该配置的key
 *
 * 区别：
 * @ConfigurationProperties  支持松散语法绑定  配置文件中  lastName  last-name 都可以注入到类；不支持表达式语言SPEL；支持JSR303数据校验@Validated；支持复杂类型封装如map
 * @value 不支持松散绑定；但是它支持表达式语言SPEL #{20*10}；不支持JSR303数据校验；不支持复杂类型的数据分装如map
 * 如果专门编写一个Javabean来和配置文件映射，就用ConfigurationProperties
 *
 *
 */
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
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
