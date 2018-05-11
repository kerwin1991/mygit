package com.wxp.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;

/**
 * Created by wangxiaopeng on 2018/5/10.
 */
@Component
public class Student {

	@Value("${person.last-name}")
	private String username;
	@Value("#{10*20}")
	private int age;
	@Value("true")
	private boolean boss;

	@Email  // @Value 不支持校验
	@Value("${person.email}")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student{" +
				"username='" + username + '\'' +
				", age=" + age +
				", boss=" + boss +
				", email=" + email +
				'}';
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}
}
