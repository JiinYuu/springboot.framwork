package com.chamc.framework.support.listener;

import org.springframework.ui.Model;

public interface PreDetailListener<T> extends BaseListener {

	String preDetail(Model model, T entity);
}
