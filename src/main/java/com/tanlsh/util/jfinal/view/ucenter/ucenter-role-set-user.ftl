<#include "/WEB-INF/classes/com/tanlsh/util/jfinal/view/common/inc.ftl"/>

<input type="hidden" name="roleid" value="${roleid}"/>
<div class="row">
	<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" style="height:400px;overflow-y:auto;">
		<p>已分配用户</p>
		<table class="table table-bordered table-hover intable">
			<#if rls?? && rls?size gt 0>
				<#list rls as rl>
					<tr class="mytr intr" style="cursor:pointer;" data="${(rl.id)!}">
						<td>${(rl.ucenter_user_name)!}</td>
					</tr>
				</#list>
			</#if>
		</table>
	</div>
	<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
		<div style="positoin:absolute;top:50%;height:100px;margin-top:50px;">
			<@bsbutton type="primary" icon="arrow-left"  href='javascript:qiao.role.addUser();'/>
			<@bsbutton type="primary" icon="arrow-right" href='javascript:qiao.role.removeUser();'/>
		</div>
	</div>
	<div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" style="height:400px;overflow-y:auto;">
		<p>未分配用户</p>
		<table class="table table-bordered table-hover outtable">
			<#if outusers?? && outusers?size gt 0>
				<#list outusers as u>
					<tr class="mytr outtr" style="cursor:pointer;" data="${(u.id)!}">
						<td>${(u.ucenter_user_name)!}</td>
					</tr>
				</#list>
			</#if>
		</table>
	</div>
</div>