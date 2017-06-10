package com.chamc.framework.support.annotation;

import com.chamc.framework.support.listener.BaseListener;

public @interface CrudListener {

	Class<? extends BaseListener> value();
}
