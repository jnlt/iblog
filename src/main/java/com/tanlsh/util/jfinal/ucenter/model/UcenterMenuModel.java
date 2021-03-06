package com.tanlsh.util.jfinal.ucenter.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.tanlsh.util.core.annotation.Table;
import com.tanlsh.util.core.data.StringUtil;
import com.tanlsh.util.function.CacheUtil;
import com.tanlsh.util.plugin.contants.Contants;

/**
 * 菜单
 * @author uikoo9
 */
@Table("t_ucenter_menu")
@SuppressWarnings("serial")
public class UcenterMenuModel extends Model<UcenterMenuModel>{
	
	public static final UcenterMenuModel dao = new UcenterMenuModel();
	
	/**
	 * find all default
	 * @return
	 */
	public List<UcenterMenuModel> findAll(){
		return findAll(null);
	}
	
	/**
	 * find all with order
	 * @param order
	 * @return
	 */
	public List<UcenterMenuModel> findAll(String order){
		StringBuilder sb = new StringBuilder("select * from t_ucenter_menu ");
		if(StringUtil.isEmpty(order)){
			return dao.find(sb.append("order by id desc").toString());
		}else{
			return dao.find(sb.append(order).toString());
		}
	}
	
	/**
	 * find all by cache
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UcenterMenuModel> findAllByCache(){
		List<UcenterMenuModel> menus = null;
		
		Object menusObject = CacheUtil.getFromEHCache("menus");
		if(menusObject == null){
			String sql = "select * from t_ucenter_menu where ucenter_menu_parent_id=0 and ucenter_menu_type=? order by ucenter_menu_sn";
			menus = dao.find(sql, Contants.YESNO_YES);
			CacheUtil.putToEHCache("menus", menus);
		}else{
			menus = (List<UcenterMenuModel>) menusObject;
		}
		
		return menus;
	}
	
	/**
	 * get submenus
	 * @return
	 */
	public List<UcenterMenuModel> submenus(){
		return UcenterMenuModel.dao.find("select * from t_ucenter_menu where ucenter_menu_parent_id=? order by ucenter_menu_sn", get("id"));
	}
	
}
