package com.carruesco.weight;

import java.util.Date;

public class WeightTO {
	private Date date;
	private Double weight;
	private Double height;
    
    @Override
    public String toString() {
    	return String.format(
                "WeightTO[date=%s, weight='%d', height='%d']",
                date, weight, height);
    }
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
}
