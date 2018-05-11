package com.wxp.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wangxiaopeng on 2018/5/11.
 */
@Component
public class Project {

	@Value("${project_desc}")
	private String description;
	@Value("${random_num}")
	private String val1;
	@Value("${project_val1}")
	private String val2;
	@Value("${project_val2}")
	private String val3;

	private String val4;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVal1() {
		return val1;
	}

	public void setVal1(String val1) {
		this.val1 = val1;
	}

	public String getVal2() {
		return val2;
	}

	public void setVal2(String val2) {
		this.val2 = val2;
	}

	public String getVal3() {
		return val3;
	}

	public void setVal3(String val3) {
		this.val3 = val3;
	}

	public String getVal4() {
		return val4;
	}

	public void setVal4(String val4) {
		this.val4 = val4;
	}

	@Override
	public String toString() {
		return "Project{" +
				"description='" + description + '\'' +
				", val1='" + val1 + '\'' +
				", val2='" + val2 + '\'' +
				", val3='" + val3 + '\'' +
				", val4='" + val4 + '\'' +
				'}';
	}
}
