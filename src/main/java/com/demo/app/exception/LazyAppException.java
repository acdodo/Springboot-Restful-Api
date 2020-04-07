package com.demo.app.exception;

import com.demo.app.common.response.ResultCode;

import java.text.MessageFormat;

/**
 * 如果代码中需要抛出一个不需要显式地进行throw或catch的异常,请使用该异常.
 * 如果没有处理,则这个异常最后会抛出给用户
 */
public class LazyAppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2478448624511847608L;

	/**
	 * 
	 */
	public LazyAppException() {
	}

	/**
	 * @param message
	 */
	public LazyAppException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LazyAppException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LazyAppException(String message, Throwable cause) {
		super(message, cause);
	}
	//错误代码
	ResultCode resultCode;

	public LazyAppException(ResultCode resultCode){
		super(resultCode.message());
		this.resultCode = resultCode;
	}

	public LazyAppException(ResultCode resultCode, Object... args){
		super(resultCode.message());
		String message = MessageFormat.format(resultCode.message(), args);
		resultCode.setMessage(message);
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode(){
		return resultCode;
	}
}
