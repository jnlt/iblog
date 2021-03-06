<#include "/WEB-INF/view/inc/inc.ftl"/>

<@html>
	<div class="container">
		<div class="row">
			<@ueditor/>
			<form class="form-horizontal" role="form" id="addBlogForm">
				<input type="hidden" name="row.id" value="${(blog.id)!}"/>
				<@bsinput title='博客类型' input=false>
					<select class="form-control" name='row.blog_type_id'>
						<#if blogTypes??>
							<#list blogTypes as item>
								<option value="${item.id}" <#if blog?? && blog.blog_type_id == item.id>selected</#if>>${item.blog_type_name}</option>
							</#list>
						</#if>
					</select>
				</@bsinput>
				<@bsinput title='博客标题' name='row.blog_article_title' value='${(blog.blog_article_title)!}'/>
				<@bsinput title='博客编码' name='row.blog_article_code' value='${(blog.blog_article_code)!}'/>
				<@bsinput title='博客内容' input=false>
					<script id="ueditor" name="row.blog_article_content" type="text/plain">${(blog.blog_article_content)!}</script>
				</@bsinput>
			</form>
			<p class="text-right">
				<button type="button" class="btn btn-primary addBlog" data-loading-text="保存中。。。">保存</button>
			</p>
		</div>
	</div>
	
	<@js web=true>$(function(){web.blogedit.init();});</@js>
</@html>