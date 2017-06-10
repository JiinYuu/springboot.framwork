package com.chamc.framework.support.listener;

import org.springframework.ui.Model;

public interface AfterSaveListener<T> extends BaseListener {
	
	public String afterSave(Model model, T entity);
	
}
