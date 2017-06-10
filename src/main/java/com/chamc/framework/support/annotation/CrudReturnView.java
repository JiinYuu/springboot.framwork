package com.chamc.framework.support.annotation;

/**
 * BaseController的增删改查方法的返回view配置。<br/>
 * 按照save（包括新增和修改）、delete、query、detail（详情）顺序配置。
 * @author luomq
 *
 */
public @interface CrudReturnView {

	String[] value();
}
