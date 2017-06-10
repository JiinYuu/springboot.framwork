package com.chamc.framework.support.listener;


import org.springframework.ui.Model;

public interface AfterDeleteListener<T> extends BaseListener {
	
	public String afterDelete(Model model, T entity);
	
}
