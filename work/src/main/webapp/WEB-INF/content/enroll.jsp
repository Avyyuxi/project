<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>查看课程表并选课</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="easyui/demo.css" />
    <script type="text/javascript" src="easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script>
$(function(){
	$('#role').datagrid({  
		url:'schedule_query.action',
		fitColumns:true,
	    with:600,
	    title:'选课',
	    treeField:'name',
	    fit: true,
	    idField:'sectionNo',
	    pageSize:10,
	    toolbar:'#toolbar',
	    pageList:[10,15,20],
	    columns:[[    
	    			  {field:'sectionNo',title:'编号',align:'center'},
			          {field:'courseNo',title:'课程编号',align:'center'},
			          {field:'courseName',title:'名称',align:'center'},
			          {field:'dayOfWeek',title:'周几',align:'center'},
			          {field:'timeOfDay',title:'时间',align:'center'},
			          {field:'room',title:'教室',align:'center'},
			          {field:'seatingCapacity',title:'容量',align:'center'},
			          {field:'professor',title:'教师',align:'center'},
			          /* 	{field:'enroll',title:'选课',sortable:true,width:200,formatter: function(value,row,index){
						$("#id").val(row.id);
	        	return "<a href='javascript:;' onclick=add() style='text-decoration:none'>选课</a>";
		        } 
	        },	*/
      
	             	        
	    ]],   
		fitColumns:true,
		pagination:true,
		remoteSort:false,
		multiSort:true,
		nowrap:false,
		rownumbers:true,
	    striped:true,
	    singleSelect:true,
		queryParams: {
			semester:$('#cc').combo('getValue')
		},
	         		 	
	  });
	$('#cc').combobox({    
		onSelect: function(rec){
			$('#role').datagrid('load',{
				semester:$('#cc').combo('getValue')
			});



		}
	});
});
function add(){
	var row = $('#role').datagrid('getSelected');  
	$.ajax({ url: "${pageContext.request.contextPath}/section_enroll",data:{sectionNo:row.sectionNo},success: function(result){
		$.messager.alert('结果',result);  
      }});
	
};
</script>
</head>
<body>
	<table id="role"></table>
	<input type="text" id="id">
	<div id="toolbar">
		<select id="cc" class="easyui-combobox" name="dept" style="width:200px;" value="0">   
		    <option value="1">大一</option>   
		    <option value="2">大二</option>   
		    <option value="3">大三</option>   
		    <option value="4">大四</option>     
		</select> 
		<a id="btn" href="#" class="easyui-linkbutton" onClick="add()" data-options="iconCls:'icon-search'">选课</a>  
	</div>	
	<div id="win"></div> 
	<input type="text" id="id"> 
</body>
</html>