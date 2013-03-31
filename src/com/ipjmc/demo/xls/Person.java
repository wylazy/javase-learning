package com.ipjmc.demo.xls;

public class Person {
	private String name;
	private String nickname;
	private String power;
	private String wit;
	private String polity;
	private String charm;
	private String story;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public void setWit(String wit) {
		this.wit = wit;
	}
	public void setPolity(String polity) {
		this.polity = polity;
	}
	public void setCharm(String charm) {
		this.charm = charm;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getNickname() {
		return nickname;
	}
	public String getPower() {
		return power;
	}
	public String getWit() {
		return wit;
	}
	public String getPolity() {
		return polity;
	}
	public String getCharm() {
		return charm;
	}
	public String getStory() {
		return story;
	}
	Person(){}
	Person(String name,String nickname,String power,String wit,String polity,String charm,String story){
		this.name = name;
		this.nickname = nickname;
		this.power = power;
		this.wit = wit;
		this.polity = polity;
		this.charm = charm;
		this.story = story;
	}

}
