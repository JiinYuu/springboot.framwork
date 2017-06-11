package com.chamc.framework.support;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chamc.framework.support.annotation.CrudReturnView;
import com.chamc.framework.support.listener.AfterDeleteListener;
import com.chamc.framework.support.listener.AfterDetailListener;
import com.chamc.framework.support.listener.AfterQueryListener;
import com.chamc.framework.support.listener.AfterSaveListener;
import com.chamc.framework.support.listener.PreDeleteListener;
import com.chamc.framework.support.listener.PreDetailListener;
import com.chamc.framework.support.listener.PreQueryListener;
import com.chamc.framework.support.listener.PreSaveListener;

public abstract class BaseController<T extends BaseEntity> {

	private PreSaveListener<T> preSaveListener = (m, e) -> GO_ON;
	private AfterSaveListener<T> afterSaveListener = (m, e) -> GO_ON;
	private PreDeleteListener<T> preDeleteListener = (m, e) -> GO_ON;
	private AfterDeleteListener<T> afterDeleteListener = (m, e) -> GO_ON;
	private PreDetailListener<T> preDetailListener = (m, e) -> GO_ON;
	private AfterDetailListener<T> afterDetailListener = (m, e) -> GO_ON;
	private PreQueryListener<T> preQueryListener = (m, p) -> GO_ON;
	private AfterQueryListener<T> afterQueryListener = (m, es) -> GO_ON;

	private final String[] crudReturnView;
	protected final String saveReturnView;
	protected final String deleteReturnView;
	protected final String queryReturnView;
	protected final String detailReturnView;
	protected static final String GO_ON = "GO_ON";
	private static final String DEFAULT_SAVE_RETURN = "list";
	private static final String DEFAULT_DELETE_RETURN = "list";
	private static final String DEFAULT_QUERY_RETURN = "list";
	private static final String DEFAULT_DETAIL_RETURN = "detail";

	private static final String[] DEFAULT_CRUDRETURNVIEW = new String[] { DEFAULT_SAVE_RETURN, DEFAULT_DELETE_RETURN,
			DEFAULT_QUERY_RETURN, DEFAULT_DETAIL_RETURN };

	public BaseController() {
		CrudReturnView views = this.getClass().getAnnotation(CrudReturnView.class);
		if (views != null) {
			Assert.assertTrue(views.value().length == 4);
			this.crudReturnView = views.value();
		} else {
			RequestMapping controllerMapping = this.getClass().getAnnotation(RequestMapping.class);
			if (controllerMapping == null) {
				this.crudReturnView = DEFAULT_CRUDRETURNVIEW;
			} else {
				String[] controllerMappingPath = controllerMapping.value();
				if (ArrayUtils.isNotEmpty(controllerMappingPath)) {
					String controllerPath = controllerMappingPath[0];
					String saveReturnView = ("redirect:" + controllerPath + "/" + DEFAULT_SAVE_RETURN).replace("//", "/");
					String deleteReturnView = ("redirect:" + controllerPath + "/" + DEFAULT_DELETE_RETURN).replace("//", "/");
					String queryReturnView = (controllerPath + "/" + DEFAULT_QUERY_RETURN).replace("//", "/");
					String detailReturnView = (controllerPath + "/" + DEFAULT_DETAIL_RETURN).replace("//", "/");
					this.crudReturnView = new String[] { saveReturnView, deleteReturnView, queryReturnView,
							detailReturnView };
				} else {
					this.crudReturnView = DEFAULT_CRUDRETURNVIEW;
				}
			}
		}
		this.saveReturnView = this.crudReturnView[0];
		this.deleteReturnView = this.crudReturnView[1];
		this.queryReturnView = this.crudReturnView[2];
		this.detailReturnView = this.crudReturnView[3];
	}

	public abstract BaseService<T> baseService();

	@RequestMapping("save")
	public String save(Model model, T entity) {
		String preSave = this.preSaveListener.preSave(model, entity);
		if (!GO_ON.equals(preSave))
			return preSave;
		this.baseService().save(entity);
		String afterSave = this.afterSaveListener.afterSave(model, entity);
		if (!GO_ON.equals(afterSave))
			return afterSave;
		return this.saveReturnView;
	}

	@RequestMapping("delete")
	public String delete(T entity, Model model) {
		String preDelete = this.preDeleteListener.preDelete(model, entity);
		if (!GO_ON.equals(preDelete))
			return preDelete;
		this.baseService().delete(entity.getId());
		String afterDelete = this.afterDeleteListener.afterDelete(model, entity);
		if (!GO_ON.equals(afterDelete))
			return afterDelete;
		return this.deleteReturnView;
	}

	@RequestMapping("list")
	public String query(Model model) {
		String preQuery = this.preQueryListener.preQuery(model, new PageRequest(0, 5));
		if (!GO_ON.equals(preQuery))
			return preQuery;
		PageRequest page = new PageRequest(0, 5, new Sort("id"));
		Page<T> result = this.baseService().findPage(page);
		List<T> entities = result.getContent();
		model.addAttribute("entities", entities);
		String afterQuery = this.afterQueryListener.afterQuery(model, result);
		if (!GO_ON.equals(afterQuery))
			return afterQuery;
		return this.queryReturnView;
	}

	@RequestMapping("detail")
	public String detail(T entity, Model model) {
		String preDetail = this.preDetailListener.preDetail(model, entity);
		if (!GO_ON.equals(preDetail))
			return preDetail;
		if(!entity.isNew()) {
			entity = this.baseService().findOne(entity.getId());
		}
		model.addAttribute("entity", entity);
		String afterDetail = this.afterDetailListener.afterDetail(model, entity);
		if (!GO_ON.equals(afterDetail))
			return afterDetail;
		return this.detailReturnView;
	}

	public void setPreDeleteListener(PreDeleteListener<T> listener) {
		this.preDeleteListener = listener;
	}

	public void setAfterDeleteListener(AfterDeleteListener<T> listener) {
		this.afterDeleteListener = listener;
	}

	public void setPreSaveListener(PreSaveListener<T> listener) {
		this.preSaveListener = listener;
	}

	public void setAfterSaveListener(AfterSaveListener<T> listener) {
		this.afterSaveListener = listener;
	}

	public void setPreDetailListener(PreDetailListener<T> listener) {
		this.preDetailListener = listener;
	}

	public void setAfterDetailListener(AfterDetailListener<T> listener) {
		this.afterDetailListener = listener;
	}

	public void setPreQueryListener(PreQueryListener<T> listener) {
		this.preQueryListener = listener;
	}

	public void setAfterQueryListener(AfterQueryListener<T> listener) {
		this.afterQueryListener = listener;
	}

}
