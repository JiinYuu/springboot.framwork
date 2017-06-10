package com.chamc.framework.support.listener;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface PreQueryListener<T> extends BaseListener {

	String preQuery(Model model, Pageable page);
}
