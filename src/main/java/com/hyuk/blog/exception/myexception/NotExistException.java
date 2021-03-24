package com.hyuk.blog.exception.myexception;

import java.util.NoSuchElementException;

public class NotExistException extends NoSuchElementException {

	public NotExistException(String msg) {
		super(msg);
	}
}
