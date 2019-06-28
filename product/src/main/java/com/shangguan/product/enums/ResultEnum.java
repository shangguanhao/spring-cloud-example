package com.shangguan.product.enums;

public enum ResultEnum {
	
	PRODUCT_NOT_EXIT(1,"商品不存在"),
	PRODUCT_STOCK_ERROR(2,"库存不足"),
	;
	
	private Integer code;
	
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
}
