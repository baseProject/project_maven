<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表</title>
<script type="text/javascript">
	function adduserDialog(){		
		$.layer({
		    type : 2,
		    title : '添加用户信息',
		    iframe : {src : '${path}/user/add.html'},
		    area : ['500px' , '350px'],
		    offset : ['100px',''],
		});
	}
	function edituserDialog(id){
		$.layer({
		    type : 2,
		    title : '修改用户信息',
		    iframe : {src : '${path}/user/edit.html?id='+id},
		    area : ['500px' , '350px'],
		    offset : ['100px',''],
		});
	}
	function deleteuserDialog(id){
		var i=$.layer({
		    shade : [0], //不显示遮罩
		    area : ['auto','auto'],
		    dialog : {
		        msg:'是否确定删除?',
		        btns : 2, 
		        type : 4,
		        btn : ['确定','取消'],
		        yes : function(){
		        	var index=layer.load(0);
		    		$.ajax({
		                url: "${path}/user/ajaxDeleteUser.html",
		                data: {'idUser': id},
		                type: "post",
		                success: function (data) {
		                	layer.close(index);
		                	if(data.success){
		                		layer.msg(data.msg,1,1);
		                		location.reload();
		                	}else{
		                		layer.msg(data.msg,1,4);
		                	}
		                }
		            });
		        },
		        no : function(){
		            layer.close(i);
		        }
		    }
		});	
	}
	
	function updatePS(id){
			var i=$.layer({
		    shade : [0], //不显示遮罩
		    area : ['auto','auto'],
		    dialog : {
		        msg:'确定重置密码?',
		        btns : 2, 
		        type : 4,
		        btn : ['确定','取消'],
		        yes : function(){
		        	var index=layer.load(0);
		    		$.ajax({
					      url: "${path}/user/updatePassword.html",
					      data: {'id': id},
					      type: "post",
					      success: function (data) {
					            layer.close(index);
					            if(data.success){
					                	layer.msg(data.msg,1,1);
					            }else{
					                	layer.msg(data.msg,1,4);
					            }
					     }
					});
		        },
		        no : function(){
		            layer.close(i);
		        }
		    }
		});	
	}
	
	function lock(id){
		var i=$.layer({
			    shade : [0], //不显示遮罩
			    area : ['auto','auto'],
			    dialog : {
			        msg:'确定锁定此用户?',
			        btns : 2, 
			        type : 4,
			        btn : ['确定','取消'],
			        yes : function(){
			        	var index=layer.load(0);
			    		$.ajax({
						      url: "${path}/user/updateLock.html",
						      data: {'id': id,'lock':1},
						      type: "post",
						      success: function (data) {
						            layer.close(index);
						            if(data.success){
						                layer.msg(data.msg,1,1);
						                location.reload();
						            }else{
						                layer.msg(data.msg,1,4);
						            }
						     }
						});
			        },
			        no : function(){
			            layer.close(i);
			        }
			    }
			});	
	}
	
	function unlock(id){
		var i=$.layer({
			    shade : [0], //不显示遮罩
			    area : ['auto','auto'],
			    dialog : {
			        msg:'确定解锁此用户?',
			        btns : 2, 
			        type : 4,
			        btn : ['确定','取消'],
			        yes : function(){
			        	var index=layer.load(0);
			    		$.ajax({
						      url: "${path}/user/updateLock.html",
						      data: {'id': id,'lock':0},
						      type: "post",
						      success: function (data) {
						            layer.close(index);
						                	if(data.success){
						                		layer.msg(data.msg,1,1);
						                		location.reload();
						                	}else{
						                		layer.msg(data.msg,1,4);
						                	}
						     }
						});
			        },
			        no : function(){
			            layer.close(i);
			        }
			    }
			});	
	}
	
	function search(){
		$('#listUser').submit();
	}
</script>
</head>
<body>
	<h2 class="h2">用户管理&gt;用户列表</h2>
	<hr style="FILTER: progid:DXImageTransform.Microsoft.Shadow(color:#987cb9,direction:145,strength:15)" width="100%" size=1/>
	<form id="listUser" action="${path }/user/list.html" method="post">
	<fieldset style="border:2px groove;margin-bottom: 5px;padding-top: 5px;padding-bottom: 5px;padding-left: 10px;">
	<legend>查询条件</legend>
			<p>
				<label>名称:</label>
				<input class="text-input small-input" type="text" id="userName" name="userName" value="${userName}"/>
				<input type="button" value="查   询" style="width: 80px;" onclick="search()"/>
			</p>
		</fieldset>
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		class="table">
		<tr bgcolor="#E7E7E7">
		<td colspan="8" style="text-align: left;"><input type="button" value="添加" onclick="adduserDialog()"/> </td>
		</tr>
		<tr>
			<th>编号</th>
			<th>登录名</th>
			<th>用户名</th>
			<th>邮箱</th>
			<th>性别</th>
			<th>创建时间</th>
			<th>是否锁定</th>
			<th width="25%">操作</th>
		</tr>
		<c:if test="${not empty pageView.data }">
		<c:forEach items="${pageView.data}" var="item" varStatus="varStatus">
			<tr onmousemove="javascript:this.bgColor='#FCFDEE';" onmouseout="javascript:this.bgColor='#FFFFFF';">
				<td><c:out value="${varStatus.index+1}"></c:out></td>
				<td><c:out value="${item.loginName }"></c:out></td>
				<td><c:out value="${item.name }"></c:out></td>
				<td><c:out value="${item.email }"></c:out></td>
				<td>
					<c:if test="${item.gender==0 }">
						男
					</c:if>
					<c:if test="${item.gender==1 }">
						女
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${item.lock==false}">
						未锁定
					</c:if>
					<c:if test="${item.lock==true}">
						已锁定
					</c:if>
				</td>
				<td>
					<input type="button" value="修改" onclick="edituserDialog(${item.id})"/>
					<input type="button" value="角色管理" onclick="editRole(${item.id})"/>
					<input type="button" value="密码重置" onclick="updatePS(${item.id})"/>
					<sec:authorize url="/user/updateLock.html">
						<c:if test="${item.lock==false}">
							<input type="button" value="锁定" onclick="lock(${item.id})"/>
						</c:if>
						<c:if test="${item.lock==true}">
							<input type="button" value="解锁" onclick="unlock(${item.id})"/>
						</c:if>
					</sec:authorize>
					<input type="button" value="删除" onclick="deleteuserDialog(${item.id})"/>
				</td>
			</tr>
		</c:forEach>
		
		</c:if>
	</table>
	<div class="pages">
		<c:import url="../common/pagnation.jsp">
		     <c:param name="rowSize" value="${pageView.rowSize }"/>
		     <c:param name="pageIndex" value="${pageView.pageIndex }"/>
		     <c:param name="pageSize" value="${pageView.pageSize }"/>
	         <c:param name="currentFormId" value="listUser"/>
	    </c:import>
	</div>
	</form>
</body>
</html>
