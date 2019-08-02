/**
 * Copyright (C), 2016-2019, 优世联合
 * FileName: R
 * Author:   Ponyo
 * Date:     21/3/2019 2:30 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lzhiy.sc.common.core.util;

import com.kuncity.cloud.common.core.constant.CommonConstants;

import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Ponyo
 * @create 21/3/2019
 * @since 1.0.0
 */
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;



	private int code = CommonConstants.SUCCESS;

	private String msg = "success";


	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public R(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = CommonConstants.FAIL;
	}

	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}

	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.setMsg( msg);
		return r;
	}

	public static R ok() {
		return new R();
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}