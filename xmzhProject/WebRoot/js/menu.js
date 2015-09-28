$(document).ready(function(){
	//页面中的DOM已经装载完成时，执行的代码
	$(".inactive").hover(function(){
		$(this).children("ul").slideDown();
		//changeIcon($(this).children("a"));
	},function(){
		$(this).children("ul").slideUp();
		//changeIcon($(this).children("a"));
	});
});

/**
 * 修改主菜单的指示图标
 */
function changeIcon(mainNode) {
	if (mainNode) {
		if (mainNode.css("background-image").indexOf("collapsed.gif") >= 0) {
			mainNode.css("background-image","url('images/expanded.gif')");
		} else {
			mainNode.css("background-image","url('images/collapsed.gif')");
		}
	}
}
