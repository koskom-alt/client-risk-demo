package com.itprofix.investments.quarkus;


import java.beans.Transient;

public class Request {
	

	private String evaluationType;

	private String signCalculation;

	private String id;

	private String tradeDate;

	private String completed;

	public String getEvaluationType(){
		return evaluationType;
	}

	public String getSignCalculation(){
		return signCalculation;
	}

	public String getId(){
		return id;
	}

	public String getTradeDate(){
		return tradeDate;
	}

	public String getCompleted(){
		return completed;
	}

	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}

	public void setSignCalculation(String signCalculation) {
		this.signCalculation = signCalculation;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}
    public Request () {

    }

    public Request(String evaluationType, String signCalculation, String id, String tradeDate) {
        super();
        this.evaluationType = evaluationType;
        this.signCalculation = signCalculation;
        this.id = id;
        this.tradeDate = tradeDate;
    }
	
	@Override
	public String toString() {
		return "Request{" +
				"evaluationType='" + evaluationType + '\'' +
				", signCalculation='" + signCalculation + '\'' +
				", id='" + id + '\'' +
				", tradeDate='" + tradeDate + '\'' +
				", completed='" + completed + '\'' +
				'}';
	}

	public boolean isProcessed () {
		return this.id.equals("f8d41b2c-22de-4d21-91b5-b65245695185");
	}
}
