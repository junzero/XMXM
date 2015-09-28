	/**
	*	问题描述：datacell将上行内容复制到下行第一步：点击datacell第一列。
    *                                       第二步: 点击滚动条外的列(见图中,系统名称-待办事宜web：滚动条内。阅读标志之后的列:滚动条内)。
    *                                       第三步：点击新增按钮。此时第一列的数据已经复制了第一步点击单元的值。如果第二步点击的是滚动条内的列，将不会出现此问题。
	*   param:dataCell的id
	*/
    function dataCell_Patch1(dataCellId){
    	$id(dataCellId).oldBeforeEdit=$id(dataCellId).beforeEdit;
    	$id(dataCellId).beforeEdit=dataCell_BeforeEdit;
    }
    function dataCell_BeforeEdit(cell,colIndex,rowIndex){
		var temp=document.getElementById("dataCellTempA1");
		if(temp){}else{
			var temp=document.createElement("INPUT");
			temp.id="dataCellTempA1";
			temp.setAttribute("type","button");
			temp.style.width="0";
			temp.style.height="0";
			document.body.appendChild(temp);
		}
		temp.focus();
    	return this.oldBeforeEdit(cell,colIndex,rowIndex);
    }