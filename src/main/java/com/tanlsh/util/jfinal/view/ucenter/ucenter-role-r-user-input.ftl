<#include "/WEB-INF/classes/com/tanlsh/util/jfinal/view/common/inc.ftl"/>

<form class="form-horizontal" role="form">
	<input type="hidden" name="row.id" value="${(row.id)!}"/>
	<@bsinput title='角色id' name='row.ucenter_role_id' value='${(row.ucenter_role_id)!}'/>	
	<@bsinput title='用户id' name='row.ucenter_user_id' value='${(row.ucenter_user_id)!}'/>	
	<@bsinput title='用户姓名' name='row.ucenter_user_name' value='${(row.ucenter_user_name)!}'/>	
</form>