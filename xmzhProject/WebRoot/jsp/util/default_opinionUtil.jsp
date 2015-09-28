<%@page pageEncoding="UTF-8"%>

     <tr>
     <td class="form_label" align="right" style="width:120px;">
     自定义意见
     </td>
     <td colspan="3" align="left">
     	<h:select id="default_op" onchange="showDefault()" style="width:300px">
     		<h:option label="请选择" value="null"/>
     		<l:iterate id="defaultOps" property="defaultOps">
     			<h:option iterateId="defaultOps"  labelField="opinion" valueField="recId" />
     		</l:iterate>
     	</h:select>
		<input type="button" class="button" value="保存意见" onclick="savedefault();" /><!-- &nbsp;&nbsp;
     	<input type="button" class="button" value="添加意见" onclick="adddefault();" /> -->
     	</td>
     	</tr>