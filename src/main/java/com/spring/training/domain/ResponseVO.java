package com.spring.training.domain;

import java.io.Serializable;

public class ResponseVO implements Serializable {

	private static final long serialVersionUID = 6368700325476037925L;

	private String status;
	private String message;
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseVO [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
