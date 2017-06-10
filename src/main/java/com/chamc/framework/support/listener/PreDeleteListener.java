package com.chamc.framework.support.listener;

import org.springframework.ui.Model;

public interface PreDeleteListener<T> extends BaseListener {
	
	public String preDelete(Model model, T entity);
	
}
