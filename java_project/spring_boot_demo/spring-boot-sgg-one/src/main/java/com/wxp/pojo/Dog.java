package com.wxp.pojo;

/**
 * Created by wangxiaopeng on 2018/5/10.
 */
public class Dog {
	private String color;
	private String ownerName;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public String toString() {
		return "Dog{" +
				"color='" + color + '\'' +
				", ownerName='" + ownerName + '\'' +
				'}';
	}
}
