package com.vam.model;

import java.util.Arrays;

//  페이징처리 클래스
public class Criteria {

	// 현재페이지 변수 선언
	private int pageNum;
	
	// 한 페이지당 보여질 게시물의 갯수의 변수 선언
	private int amount;
	
	// 검색 키워드 
	private String keyword;
	
	// 검색 타입
	private String type;
	
	// 검색 타입 배열
	private String[] typeArr;
	
	// 파라미터없이 Criteria 클래스를 호출 시 기본적으로 가질 생성자를 작성
	// 기본생성자 -> 기본 세팅 : pageNum(현재페이지) = 1, amount(한페이지당 보여질 갯수) = 10
	public Criteria() {
		
		this(1,10);
	}

	// 파라미터와 함께 Criteria를 호출하면 파라미터의 값이 각각 pageNum, amount값에 저장되도록 생성자 작성
	// 생성자 --> 원하는 pageNum, 원하는 amount
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.typeArr = type.split("");
	}

	public String[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(String[] typeArr) {
		this.typeArr = typeArr;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", keyword=" + keyword + ", type=" + type
				+ ", typeArr=" + Arrays.toString(typeArr) + "]";
	}

	
	
	
}
