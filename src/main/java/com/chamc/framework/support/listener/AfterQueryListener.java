package com.chamc.framework.support.listener;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public interface AfterQueryListener<T> extends BaseListener {
	
	public String afterQuery(Model model, Page<T> page);
	
}
