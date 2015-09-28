<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html style="height:100%;width:100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/common.jsp" %>
		<script type="text/javascript" src="<%=cbasePath%>/js/test/jquery.datagrid.js"></script>
		<title>jQuery EasyUI grid</title>
	<script>
		var products = [
		    {productid:'FI-SW-01',name:'Koi'},
		    {productid:'K9-DL-01',name:'Dalmation'},
		    {productid:'RP-SN-01',name:'Rattlesnake'},
		    {productid:'RP-LI-02',name:'Iguana'},
		    {productid:'FL-DSH-01',name:'Manx'},
		    {productid:'FL-DLH-02',name:'Persian'},
		    {productid:'AV-CB-01',name:'Amazon Parrot'}
		];
		function productFormatter(value){
			for(var i=0; i<products.length; i++){
				if (products[i].productid == value) return products[i].name;
			}
			return value;
		}
		jQuery(function(){
			var lastIndex;
			jQuery('#tt').datagrid({
				toolbar:[{
					text:'append',
					iconCls:'icon-add',
					handler:function(){
					jQuery('#tt').datagrid('endEdit', lastIndex);
						jQuery('#tt').datagrid('appendRow',{
							itemid:'',
							productid:'',
							listprice:'',
							unitprice:'',
							attr1:'',
							status:'P'
						});
						var lastIndex = jQuery('#tt').datagrid('getRows').length-1;
						jQuery('#tt').datagrid('selectRow', lastIndex);
						jQuery('#tt').datagrid('beginEdit', lastIndex);
					}
				},'-',{
					text:'delete',
					iconCls:'icon-remove',
					handler:function(){
						var row = jQuery('#tt').datagrid('getSelected');
						if (row){
							var index = jQuery('#tt').datagrid('getRowIndex', row);
							jQuery('#tt').datagrid('deleteRow', index);
						}
					}
				},'-',{
					text:'accept',
					iconCls:'icon-save',
					handler:function(){
						jQuery('#tt').datagrid('acceptChanges');
					}
				},'-',{
					text:'reject',
					iconCls:'icon-undo',
					handler:function(){
						jQuery('#tt').datagrid('rejectChanges');
					}
				},'-',{
					text:'getChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = jQuery('#tt').datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				}],
				onBeforeLoad:function(){
					jQuery(this).datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){
					if (lastIndex != rowIndex){
						jQuery('#tt').datagrid('endEdit', lastIndex);
						jQuery('#tt').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
			});
		});
	</script>
</head>
<body>
	<h1>Editable DataGrid</h1>
	
	<table id="tt" style="width:650px;height:auto"
			title="Editable DataGrid" iconCls="icon-edit" singleSelect="true"
			idField="itemid" url="datagrid_data2.jsp">
		<thead>
			<tr>
				<th field="itemid" width="80">Item ID</th>
				<th field="productid" width="100" formatter="productFormatter" editor="{type:'combobox',options:{valueField:'productid',textField:'name',data:products,required:true}}">Product</th>
				<th field="listprice" width="80" align="right" editor="{type:'numberbox',options:{precision:1}}">List Price</th>
				<th field="unitcost" width="80" align="right" editor="numberbox">Unit Cost</th>
				<th field="attr1" width="150" editor="text">Attribute</th>
				<th field="status" width="60" align="center" editor="{type:'checkbox',options:{on:'P',off:''}}">Status</th>
			</tr>
		</thead>
	</table>
	
</body>
</html>