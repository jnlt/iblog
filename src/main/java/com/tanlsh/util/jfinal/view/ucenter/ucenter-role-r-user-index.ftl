<#include "/WEB-INF/classes/com/tanlsh/util/jfinal/view/common/inc.ftl"/>

<@bslist qpage=qpage>
	<@bstable>
		<thead>
			<tr>
		        <th><input type="checkbox" class="allcheck"/></th>
				<th>角色id</th>
				<th>用户id</th>
				<th>用户姓名</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		    <#list qpage.list?if_exists as row>
			    <tr data="id:${row.id};">
			        <td><input type="checkbox" class="onecheck"/></td>
				    <td>${(row.ucenter_role_id)!}</td>				    
					<td>${(row.ucenter_user_id)!}</td>				    
					<td>${(row.ucenter_user_name)!}</td>				    
					<td>${(row.cdate)!}</td>				    
					<td>
			        	<@bsbutton size='xs' icon='pencil' class='editbtn'/>
			        	<@bsbutton size='xs' icon='remove' class='delbtn'/>
			        </td>
			    </tr>
		    </#list>
		</tbody>
	</@bstable>
</@bslist>