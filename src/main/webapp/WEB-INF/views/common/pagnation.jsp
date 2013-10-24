<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils,com.zhangjiaolong.frame.common.GlobalConfigure"%>

<%	
	int pageSize = GlobalConfigure.DEFAULT_PAGE_SIZE;	//每页显示的行数
	int pageIndex = 1;					//当前页数
	int rowSize = 0;					//记录集大小
	String path = request.getContextPath();
	
	if(!StringUtils.isEmpty(request.getParameter("pageSize"))) {
		pageSize=Integer.parseInt(request.getParameter("pageSize")); //如果传入每页显示的页数，则使用传入值
	}
	if(!StringUtils.isEmpty(request.getParameter("pageIndex"))) {
		pageIndex=Integer.parseInt(request.getParameter("pageIndex")); //获取当前的页数 
	}
	if(!StringUtils.isEmpty(request.getParameter("rowSize"))) {
		rowSize = Integer.parseInt(request.getParameter("rowSize")); //获取记录集大小
	}
	
	int pages = (rowSize % pageSize)==0 ? rowSize/pageSize : rowSize/pageSize+1; //根据记录集大小和页大小，计算出总页数

%>
<script type="text/javascript">
//<![CDATA[
$(function(){
	$('#index').keydown(function(event){
		if(event.keyCode==13) {
			var index=$(this).val();
			  
		  	//isNaN(index)当index为字符串时返回TRUE
		  	if(isNaN(index) || index<1 || index.indexOf(".")>=0){
		    	alert("输入错误：跳转页数必须为正整型！");
		  	}else if(index > <%=pages%>){
		    	alert("输入错误：跳转页数不能超过总页数！");
		  	}else if(index == <%=pageIndex%>){
		    	alert("输入页码就是当前页！");
		  	}else{
		   	 	show(index);
		  	}	
		}
	});	
});

function show(pageIndex){
	if(pageIndex < 1){
		pageIndex = 1;
	}
	if(pageIndex > <%=pages%>){
		pageIndex = <%=pages%>;
	}
	var form = $("#" + "${param.currentFormId}");
	$("#pageIndex").val(pageIndex);
	form.submit();
}
//]]>
</script>

<div class="pagination">
	<%
	if(pageIndex == 1){
		%>
		<a href="javascript:;" title="First Page">&laquo; 首页</a>
		<a href="javascript:;" title="Previous Page">&laquo; 上一页</a>
		<%
	} else {
		%>
		<a href="javascript:;" onclick="show(1)" title="First Page">&laquo; 首页</a>
		<a href="javascript:;" onclick="show(<%= pageIndex - 1 %>)" title="Previous Page">&laquo; 上一页</a>
		<%
	}
 	if(pages > 10){
	 	if(pageIndex >= (10-1)){
	 		int startIndex = pageIndex - 5;
			int endIndex = pageIndex + 5;
			if(endIndex > pages){
				endIndex = pages;
			}
			for(int i = startIndex;i<=endIndex;i++){
				if(i==pageIndex){
					%><a href="javascript:;" class="number current" title="<%=pageIndex %>"><%=pageIndex %></a><%
				} else {
					%><a href="javascript:;" class="number" onclick="show('<%=i %>');" title="<%=i %>"><%=i %></a><%
				}
			}
	 	} else {
	 		for(int i=1;i<10;i++){
				if(i==pageIndex){
					%><a href="javascript:;" class="number current" title="<%=pageIndex %>"><%=pageIndex %></a><%
				} else {
					%><a href="javascript:;" class="number" onclick="show('<%=i %>');" title="<%=i %>"><%=i %></a><%
				}
			}
	 	} 	
 	}else {
		for(int i=1;i<=pages;i++){
			if(i==pageIndex){
				%><a href="javascript:;" class="number current" title="<%=pageIndex %>"><%= pageIndex %></a> <%
			} else {
				%><a href="javascript:;" class="number" onclick="show('<%=i %>')" title="<%=i %>"><%=i %></a><%
			}
		}
	}
	if(pages == pageIndex){
		%>
		<a href="javascript:;" title="Next Page">下一页 &raquo;</a>
		<a href="javascript:;" title="Last Page">末页 &raquo;</a>
		<%
	} else {
		%>
		<a href="javascript:;" onclick="show(<%= pageIndex + 1 %>)" title="Next Page">下一页 &raquo;</a>
		<a href="javascript:;" onclick="show(<%= pages %>)" title="Last Page">末页 &raquo;</a>
		<%
	}
	%>
</div>
<!-- End .pagination -->
<div class="clear"></div>
<input type="hidden" id="pageIndex" name="pageIndex" />
<input type="hidden" id="pageSize" name="pageSize" />