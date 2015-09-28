<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.HashMap"%>
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
      EOS webcomp 整合
    </title>
  </head>
  <body topmargin="0">
  	<w:panel id="panel1" width="100%" title="时间录入">
				1.使用日历控件，只需要写标签即可，默认是短格式yyyy-MM-dd
						<w:date/>
				<br/>2.设置控件的显示格式为yyyy年MM月dd日 HH时mm分ss秒，但是日历控件的提交格式仍然是yyyy-MM-dd HH:mm:ss
						<w:date format="yyyy年MM月dd日 HH时mm分ss秒" width="300"/>
						
				<br/>3.设置控件的最大值和最小值注意，最大值和最小值须和format一致，默认是SDO的格式yyyy-MM-dd(不带时间) yyyy-MM-dd HH:mm:ss(带时间)
						<w:date format="yyyy年MM月dd日 HH时mm分ss秒" minValue="2001年01月01日 12时23分20秒" maxValue="2008年08月08日 00时00分00秒"/>
				<br/>4.一般情况下，日历控件的默认值为当前时间，日历控件可以用defaultNull设置默认值是否为空，defaultNull为true时默认值为空
				        <w:date defaultNull="true"/>
  	</w:panel>
  	<w:panel id="panel2" width="100%" title="静态树">
	静态树控件是把具有树形结构的数据以树的形式在页面上展示。<br>
	    <w:tree id="comp" hasRoot="true" hasCheckBox="true" checkBoxType="associated">
	        <w:treeRoot display="公司人力" icon="/common/images/icons/arrow_merge.png"></w:treeRoot>
	        <w:treeNode nodeType="org" showField="orgname" xpath="org" icon="/common/images/icons/chart_organisation.gif">
	            <w:treeRelation parentNodeType="root" field="" />
	        </w:treeNode>
	        <w:treeNode nodeType="pos" showField="orgname" xpath="pos" icon="/common/images/icons/chart_organisation.gif">
	            <w:treeRelation parentNodeType="org" parentField="orgid" field="parentorgid"/>
	        </w:treeNode>
	        <w:treeNode nodeType="emp" showField="yhmc" xpath="emp" icon="/common/images/icons/user.gif">
	            <w:treeRelation parentNodeType="pos" parentField="orgid" field="orgid" />
	        </w:treeNode>
	    </w:tree>

  	</w:panel>
  	<w:panel id="panel3" width="100%" title="lookup录入">
				Lookup控件有两种传递参数的方法：<br/>
					通过lookup的url <br/>
					通过模态对话框的dialogArguments <br/>
				lookup控件将自身的显示值和提交值传递出去的格式为：[提交值,显示值]。本例使用两种方式传递参数。<br/>
				<w:lookup name="user/org" lookupUrl="lookup_testParam.jsp" displayValue="DisplayTest" value="valueTest" lookupWidth="600">
    				<h:param name="param1"  value="参数1"/>
				</w:lookup>
  	</w:panel>
  	<w:panel id="panel4" width="100%" title="模态窗口">
			    要传入的值
			    <input type="text" id="argument" value="test openDialog argument">
			    <br>
			    对话框的返回值
			    <input type="text" id="returnvalue" value="">
			    <br />
			    <input type="button" value="testShowModal" onclick="testShowModal()" class="button">
			    <br />
			    <input type="button" value="testShowModalCenter" onclick="testShowModalCenter()" class="button">
  	</w:panel>
	<w:panel id="panel5" width="100%" title="表单验证">
			<h:form checkType="blur">
				昵称:
					<h:text name="username" validateAttr="maxLength=8;type=letter;message=昵称不能为空，只能由字母组成，六位到八位之间;minLength=6;allowNull=false"/>
				<br>密码:
					<h:password name="pwd" validateAttr="type=number;allowNull=false;maxLength=8;minLength=6;message=密码只能由数字组成，六位到八位之间"/>
				<br>姓名:
					<h:text name="test_name" validateAttr="type=zh;message=姓名只能输入中文;allowNull=false"/>
				<br>年龄:
					<h:text name="age" validateAttr="minValue=1;maxValue=150;type=naturalNumber;message=年龄必须大于1小于150;allowNull=true" value="a"/>
				<br>邮箱:
					<h:text name="email" validateAttr="type=email;allowNull=false"/>
				<br>
				<input type="submit" class="button"/>
		 	</h:form>
	</w:panel>
    <script>
        // 关闭对话框时的回调函数
        function callBack(value){
            $id("returnvalue").value = value;
        }
        function testShowModal(){
            var argument = $id("argument").value;
            // 显示对话框,参数依次为,
            // 对话框内的页面url
            // 传给对话框的参数
            // 宽度, 高度 左坐标 上坐标
            // 对话框标题
            showModal ("_dialog.jsp",argument,callBack,300,200,100,100,"我的标题1111");
        }
        function testShowModalCenter(){
            // 居中显示对话框,参数依次为,
            // 对话框内的页面url
            // 传给对话框的参数
            // 宽度, 高度
            //对话框标题
            var argument = $id("argument").value;
            showModalCenter("_dialog.jsp",argument,callBack,600,400,"我的标题222");
        }
    </script>
  </body>
</html>
