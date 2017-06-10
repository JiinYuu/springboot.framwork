package com.chamc.framework.support.listener;

import org.springframework.ui.Model;

public interface AfterDetailListener<T> extends BaseListener {
	
	public String afterDetail(Model model, T entity);
	
}
