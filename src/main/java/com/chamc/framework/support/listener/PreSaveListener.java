package com.chamc.framework.support.listener;

import org.springframework.ui.Model;

public interface PreSaveListener<T> extends BaseListener {

	String preSave(Model model, T entity);
}
