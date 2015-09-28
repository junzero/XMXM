<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@include file="/common/common.jsp"%>
<%@include file="/common/skins/skin1/component.jsp" %>
<h:css href="/css/style1/style-custom.css"/>
<%--
- Author(s): lz
- Date: 2011-10-05 16:16:53
- Description:
--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
      EOS richweb 整合
    </title>
  </head>
  <body topmargin="0">
  	<w:panel id="panel1" width="100%" title="动态树">
		  		动态树控件是把具有树形结构的数据以树的形式在页面上展示。动态树控件的特点是：树上节点的数据并不是一次全部传到客户端，
			而是在每次节点展开时，使用ajax技术从服务器端获得。
				<br/>
        <r:rtree id="tree1">
            <r:treeRoot action="/richweb_demo.do?method=initRTree" 
                        childEntities="Torganization" 
                        display="公司人力" 
                        initParamFunc="orgParamFunc" 
                        icon="/common/images/icons/arrow_merge.png"/>
            <r:treeNode nodeType="Torganization" 
                        submitXpath="org" 
                        showField="orgname" 
                        childEntities="Torganization;Tuser" 
                        icon="/common/images/icons/chart_organisation.gif"
                        action="/richweb_demo.do?method=initRTree" />
            <r:treeNode nodeType="Tuser" showField="yhmc" icon="/common/images/icons/user.gif"/>
        </r:rtree>
  	</w:panel>
  	<w:panel id="panel2" width="100%" title="ComboSelect">
			  	联动下拉列表是一种常见的应用，即有2个（或更多）的select，当其中一个的选中项发生变化时，其他的select内容会发生相应的变化。
			ComboSelect组件支持的联动分为2类：一类是动态联动；一类是静态联动。下面以双下拉列表为例，进行详细介绍。
				<br/>
			公司名：
			<r:comboSelect id="countryList"
			    queryAction="/richweb_demo.do?method=getOrgList"
			    textField="orgname"
			    valueField="orgid"
			    xpath="Torganization"
			    initParamFunc="orgParamFunc"
			    submitXpath="org"
			    width="168"
			/>&nbsp;&nbsp;单位名：
			<r:comboSelect id="provinceList"
			    linkId="countryList"
			    queryAction="/richweb_demo.do?method=getOrgList"
			    textField="orgname"
			    valueField="orgid"
			    xpath="Torganization"
			    width="168"
			/>
  	</w:panel>
  	<w:panel id="panel3" width="100%" title="Ajax">
  				Ajax封装了基本Ajax操作的API，及调用业务流的API。ServiceCaller提供了用Js调用SOA服务的API。下面举例介绍如何使用这些API。
				<br/>	
				日期格式<h:text id="DateStyle" validateAttr="message=日期格式;allowNull=false" value="yyyy-MM-dd HH:mm:ss"/><br/>
				<input type="button" value="获取服务器时间" onclick="getSystemDate()" class="button">
  	</w:panel>
  	<w:panel id="panel4" width="100%" title="DataCell">
  				Datacell控件是在同一个table内可以实现增删改功能的Ajax控件。本控件生成一个可动态编辑、可以批量隐含提交的数据表格，支持多种html输入和显示控件。<br/>
  				本例重点演示 根据条件判断单元格是否可编辑(当上组机构为1时不可编辑)
				<br/>
				<r:datacell id="emps"
					queryAction="/richweb_demo.do?method=initDataGrid" pageSize="5"
					xpath="com.gotop.demo.system.model.Torganization" width="888" height="220"
					>
					<r:toolbar tools="nav:first prev next last goto,edit:reload,pagesize,info"/>
				    <r:field  fieldName="orgid" label="机构编号" width="130"/>
				    
				    <r:field  fieldName="orgname" label="机构名称" >
				         <h:text validateAttr="allowNull=false"/>
				    </r:field>
				    <r:field  fieldName="parentorgid" label="上级机构编号" >
				         <h:text validateAttr="allowNull=false"/>
				    </r:field>
				    <r:field  fieldName="orglevel" label="机构等级" >
				         <h:text validateAttr="allowNull=false"/>
				    </r:field>
				    <r:field  fieldName="orgnamech" label="机构名称拼音" >
				         <h:text validateAttr="allowNull=false"/>
				    </r:field>
				    <r:field  fieldName="createdate" label="创建日期" width="130">
				         <w:date format="yyyy-MM-dd"/>
				    </r:field>
				    <r:field  fieldName="orgstate" label="机构状态" width="60">
				         <h:text validateAttr="allowNull=false"/>
				    </r:field>
				    <r:field  fieldName="datastate" label="数据状态" width="60">
				         <h:text validateAttr="allowNull=false"/>
				    </r:field>
				</r:datacell>
  	</w:panel>
	<script>
        function orgParamFunc(){
            return "<org><orgid>1</orgid></org>";
        }
        function getSystemDate(){
        	var myAjax = new Ajax("/richweb_demo.do?method=getSystemDate");
			myAjax.addParam("DateStyle", $id("DateStyle").value);
			myAjax.submit();
			var returnNode =myAjax.getText();
			alert(returnNode);
        }
		//实现beforeEdit接口
		$id("emps").beforeEdit=function(cell,colIndex,rowIndex)
		{
			//获得编辑行的entity
			var entity=this.getEntityByCell(cell)
			//如果编辑是1返回false,取消编辑操作
			if(entity.getProperty("parentorgid")=="1" && colIndex==3) return false;
			
		}
    </script>
  </body>
</html>
