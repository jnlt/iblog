package com.tanlsh.util.jfinal.ucenter.controller;

import java.util.List;

import com.tanlsh.util.core.data.ArrayUtil;
import com.tanlsh.util.function.CacheUtil;
import com.tanlsh.util.jfinal.BaseController;
import com.tanlsh.util.jfinal.ucenter.model.UcenterMenuModel;
import com.tanlsh.util.plugin.contants.Contants;
import com.tanlsh.util.plugin.contants.ContantsUtil;
import com.tanlsh.util.plugin.json.MyJson;
import com.tanlsh.util.plugin.json.MyJsonUtil;
import com.tanlsh.util.plugin.tree.MyTree;

/**
 * 用户中心-菜单controller
 * @author uikoo9
 */
public class UcenterMenuController extends BaseController{
	
	/**
	 * 跳转到菜单管理页面
	 */
	public void index(){
		setAttr("tree", new MyTree(0, "/", "根菜单", null, null));
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-menu-index.ftl");
	}
	
	/**
	 * 生成树
	 */
	public void tree(){
		renderJson(MyJsonUtil.suc(new MyTree(0, "/", "根菜单", null, null)));
	}
	
	/**
	 * 跳转到添加子菜单页面
	 */
	public void add(){
		setAttr("yesnos", ContantsUtil.list(Contants.YESNO));
		setAttr("ucenter_menu_parent_id", getParaToInt(0));
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-menu-add.ftl");
	}
	
	/**
	 * 跳转到保存修改页 
	 */
	public void savep(){
		setAttr("yesnos", ContantsUtil.list(Contants.YESNO));
		setAttr("row", getRow(UcenterMenuModel.class));
		render("/WEB-INF/classes/com/tanlsh/util/jfinal/view/ucenter/ucenter-menu-edit.ftl");
	}
	
	/**
	 * 保存或修改
	 */
	public void save(){
		String validate = validate();
		if(validate == null){
			MyJson json = save(UcenterMenuModel.class);
			if(MyJsonUtil.TYPE_BS_SUCC.equals(json.getType())){
				CacheUtil.putToEHCache("menus", UcenterMenuModel.dao.findAllByCache());
			}
			
			renderJson(json);
		}else{
			renderJson(MyJsonUtil.error(validate));
		}
	}
	
	/**
	 * 删除菜单，包括子菜单
	 */
	public void del(){
		try {
			delChildren(getParaToInt());
			CacheUtil.putToEHCache("menus", UcenterMenuModel.dao.findAllByCache());
			renderJson(MyJsonUtil.suc("删除成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(MyJsonUtil.error("删除失败！"));
		}
	}
	private void delChildren(Integer id){
		UcenterMenuModel menu = UcenterMenuModel.dao.findById(id);
		
		List<UcenterMenuModel> menus = menu.submenus();
		if(ArrayUtil.notEmpty(menus)){
			for(UcenterMenuModel sub : menus){
				delChildren(sub.getInt("id"));
			}
		}
		
		menu.delete();
	}
	
}
