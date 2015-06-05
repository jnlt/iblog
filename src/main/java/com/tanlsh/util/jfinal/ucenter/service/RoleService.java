package com.tanlsh.util.jfinal.ucenter.service;

import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.tanlsh.util.jfinal.QJfinalUtil;
import com.tanlsh.util.jfinal.ucenter.model.UcenterMenuModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterRoleRMenuModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterRoleRUserModel;
import com.tanlsh.util.jfinal.ucenter.model.UcenterUserModel;
import com.tanlsh.util.plugin.json.QJson;
import com.tanlsh.util.plugin.json.QJsonUtil;

/**
 * Role Service
 * @author qiaowenbin
 */
public class RoleService {

	private RoleService() {}
	public static RoleService getInstance() {
		return SingletonFactory.instance;
	}
	private static class SingletonFactory {
		private static RoleService instance = new RoleService();
	}
	
	/**
	 * 为角色添加一个用户
	 * @param roleId
	 * @param userIds
	 * @param loginUser
	 * @return
	 */
	public QJson addUser(Integer roleId, String userIds, UcenterUserModel loginUser){
		try {
			for(String uid : userIds.split(",")){
				UcenterUserModel user = UcenterUserModel.dao.findById(uid);
				new UcenterRoleRUserModel()
					.set("ucenter_role_id", roleId)
					.set("ucenter_user_id", user.get("id"))
					.set("ucenter_user_name", user.get("ucenter_user_name"))
					.set("cdate", new Date())
					.set("cuser_id", loginUser.get("id"))
					.set("cuser_name", loginUser.get("ucenter_user_name"))
					.save();
			}
			
			return QJsonUtil.suc();
		} catch (Exception e) {
			e.printStackTrace();
			return QJsonUtil.error("添加用户失败 !");
		}
	}
	
	/**
	 * 从角色移除用户
	 * @param rlids
	 * @return
	 */
	public QJson removeUser(String rlids){
		try {
			for(String rlid : rlids.split(",")){
				UcenterRoleRUserModel.dao.deleteById(rlid);
			}
			
			return QJsonUtil.suc();
		} catch (Exception e) {
			e.printStackTrace();
			return QJsonUtil.error("移除用户失败 !");
		}
	}
	
	/**
	 * 为角色添加或删除url
	 * @param roleId
	 * @param ids
	 * @param loginUser
	 * @return
	 */
	public QJson saveUrl(Integer roleId, String ids, UcenterUserModel loginUser){
		try {
			Db.update("delete from t_ucenter_role_r_menu where ucenter_role_id=?", roleId);
			
			for(String id : ids.split(",")){
				new UcenterRoleRMenuModel()
					.set("ucenter_role_id", roleId)
					.set("ucenter_menu_id", id)
					.set("ucenter_menu_url", UcenterMenuModel.dao.findById(id).getStr("ucenter_menu_url"))
					.set("cdate", new Date())
					.set("cuser_id", loginUser.get("id"))
					.set("cuser_name", loginUser.get("ucenter_user_name"))
					.save();
			}
			
			QJfinalUtil.initAuths();
			
			return QJsonUtil.suc();
		} catch (Exception e) {
			e.printStackTrace();
			return QJsonUtil.error("添加Url失败 !");
		}
	}
	
}