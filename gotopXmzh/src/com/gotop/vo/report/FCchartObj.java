package com.gotop.vo.report;

import java.io.Serializable;
import java.util.List;

/*********************************
 * <p>Title:FCchartObj.java</p>
 *
 * <p>Description:com.gotop.vo.report</p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author huxj
 *
 * @date 2011-5-19 下午07:55:07
 *
 * @version 1.0
 *
 * HISTORY 2011-5-19 下午07:55:07 huxj 创建文件
 *
 *********************************/
public class FCchartObj implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChartObj chart;			//主对象
	private List<SetObj> data;		//数据
	private List<StylesObj> styles; //样式
	
	public FCchartObj()
	{
		super();
	}
	public FCchartObj(boolean isCreateNew)
	{
		super();
		if(isCreateNew)
		{
			this.setChart(new FCchartObj.ChartObj());
		}
	}
	
	public ChartObj getChart()
	{
		return chart;
	}
	public void setChart(ChartObj chart)
	{
		this.chart = chart;
	}
	public List<SetObj> getData()
	{
		return data;
	}
	public void setData(List<SetObj> data)
	{
		this.data = data;
	}
	public List<StylesObj> getStyles()
	{
		return styles;
	}
	public void setStyles(List<StylesObj> styles)
	{
		this.styles = styles;
	}
	

	public class ChartObj
	{
		private String caption="";				//标题
		private String subcaption="";			//子标题
		private String xaxisname="";			//x轴名称
		private String yaxisname="";			//Y轴名称
		private String bgColor="#EDF4FC";				//背景色
		private String bgSWF="";//背景图片或swf/images/right_04.gif
		private String bgSWFAlpha="40";				//背景透明度
		private String logoURL="/images/logo_er.jpg";//图标地址
		private String logoPosition="0A";			//图标位置
		private String logoAlpha="60";				//图标透明度
		private String logoScale="50";				//图表范围
		private String showBorder="";			//是否显示边框
		private String borderColor="78826C";			//边框颜色
		private String borderThickness="1";		//边框粗细
		private String borderAlpha="40";			//边框透明度
		private String labelDisplay="AUTO";		//标签显示 AUTO
		private String useEllipsesWhenOverflow="0";//当超过x轴宽时是否显示省略号	0
		private String maxLabelWidthPercent="30%";//标签占x轴最大百分比
		private String numberPrefix="";		//拼接到数据前的标记
		private String labelStep="1";			//标签步进
		private String showValues="1";			//是否显示值
		private String showToolTip="1";			//是否显示提示
		private String showToolTipShadow="0";	//提示背景阴影
		private String toolTipBorderColor="D9E5F1";	//提示边框颜色
		private String toolTipBgColor="D9E5F1";		//提示背景色
		private String chartLeftMargin="";		//左边沿
		private String chartRightMargin="";	//右边沿
		private String chartTopMargin="";		//上边沿
		private String chartBottomMargin="";	//下边沿
		private String captionPadding="";		//标题填充
		private String xAxisNamePadding="";	//x轴填充
		private String yAxisNamePadding="";	//y轴填充
		private String labelPadding="";		//标题填充
		private String valuePadding="";		//值填充
		private String showAboutMenuItem="1";	//是否右键显示关于
		private String aboutMenuItemLabel="About GOTOP";	//右键关于项标签
		private String aboutMenuItemLink="http://www.gotop.net.cn/";	//关于项连接
		public String getCaption()
		{
			return caption;
		}
		public void setCaption(String caption)
		{
			this.caption = caption;
		}
		public String getSubcaption()
		{
			return subcaption;
		}
		public void setSubcaption(String subcaption)
		{
			this.subcaption = subcaption;
		}
		public String getXaxisname()
		{
			return xaxisname;
		}
		public void setXaxisname(String xaxisname)
		{
			this.xaxisname = xaxisname;
		}
		public String getYaxisname()
		{
			return yaxisname;
		}
		public void setYaxisname(String yaxisname)
		{
			this.yaxisname = yaxisname;
		}
		public String getBgColor()
		{
			return bgColor;
		}
		public void setBgColor(String bgColor)
		{
			this.bgColor = bgColor;
		}
		public String getBgSWF()
		{
			return bgSWF;
		}
		public void setBgSWF(String bgSWF)
		{
			this.bgSWF = bgSWF;
		}
		public String getBgSWFAlpha()
		{
			return bgSWFAlpha;
		}
		public void setBgSWFAlpha(String bgSWFAlpha)
		{
			this.bgSWFAlpha = bgSWFAlpha;
		}
		public String getLogoURL()
		{
			return logoURL;
		}
		public void setLogoURL(String logoURL)
		{
			this.logoURL = logoURL;
		}
		public String getLogoPosition()
		{
			return logoPosition;
		}
		public void setLogoPosition(String logoPosition)
		{
			this.logoPosition = logoPosition;
		}
		public String getLogoAlpha()
		{
			return logoAlpha;
		}
		public void setLogoAlpha(String logoAlpha)
		{
			this.logoAlpha = logoAlpha;
		}
		public String getLogoScale()
		{
			return logoScale;
		}
		public void setLogoScale(String logoScale)
		{
			this.logoScale = logoScale;
		}
		public String getShowBorder()
		{
			return showBorder;
		}
		public void setShowBorder(String showBorder)
		{
			this.showBorder = showBorder;
		}
		public String getBorderColor()
		{
			return borderColor;
		}
		public void setBorderColor(String borderColor)
		{
			this.borderColor = borderColor;
		}
		public String getBorderThickness()
		{
			return borderThickness;
		}
		public void setBorderThickness(String borderThickness)
		{
			this.borderThickness = borderThickness;
		}
		public String getBorderAlpha()
		{
			return borderAlpha;
		}
		public void setBorderAlpha(String borderAlpha)
		{
			this.borderAlpha = borderAlpha;
		}
		public String getLabelDisplay()
		{
			return labelDisplay;
		}
		public void setLabelDisplay(String labelDisplay)
		{
			this.labelDisplay = labelDisplay;
		}
		public String getUseEllipsesWhenOverflow()
		{
			return useEllipsesWhenOverflow;
		}
		public void setUseEllipsesWhenOverflow(String useEllipsesWhenOverflow)
		{
			this.useEllipsesWhenOverflow = useEllipsesWhenOverflow;
		}
		public String getMaxLabelWidthPercent()
		{
			return maxLabelWidthPercent;
		}
		public void setMaxLabelWidthPercent(String maxLabelWidthPercent)
		{
			this.maxLabelWidthPercent = maxLabelWidthPercent;
		}
		public String getNumberPrefix()
		{
			return numberPrefix;
		}
		public void setNumberPrefix(String numberPrefix)
		{
			this.numberPrefix = numberPrefix;
		}
		public String getLabelStep()
		{
			return labelStep;
		}
		public void setLabelStep(String labelStep)
		{
			this.labelStep = labelStep;
		}
		public String getShowValues()
		{
			return showValues;
		}
		public void setShowValues(String showValues)
		{
			this.showValues = showValues;
		}
		public String getShowToolTip()
		{
			return showToolTip;
		}
		public void setShowToolTip(String showToolTip)
		{
			this.showToolTip = showToolTip;
		}
		public String getShowToolTipShadow()
		{
			return showToolTipShadow;
		}
		public void setShowToolTipShadow(String showToolTipShadow)
		{
			this.showToolTipShadow = showToolTipShadow;
		}
		public String getToolTipBorderColor()
		{
			return toolTipBorderColor;
		}
		public void setToolTipBorderColor(String toolTipBorderColor)
		{
			this.toolTipBorderColor = toolTipBorderColor;
		}
		public String getToolTipBgColor()
		{
			return toolTipBgColor;
		}
		public void setToolTipBgColor(String toolTipBgColor)
		{
			this.toolTipBgColor = toolTipBgColor;
		}
		public String getChartLeftMargin()
		{
			return chartLeftMargin;
		}
		public void setChartLeftMargin(String chartLeftMargin)
		{
			this.chartLeftMargin = chartLeftMargin;
		}
		public String getChartRightMargin()
		{
			return chartRightMargin;
		}
		public void setChartRightMargin(String chartRightMargin)
		{
			this.chartRightMargin = chartRightMargin;
		}
		public String getChartTopMargin()
		{
			return chartTopMargin;
		}
		public void setChartTopMargin(String chartTopMargin)
		{
			this.chartTopMargin = chartTopMargin;
		}
		public String getChartBottomMargin()
		{
			return chartBottomMargin;
		}
		public void setChartBottomMargin(String chartBottomMargin)
		{
			this.chartBottomMargin = chartBottomMargin;
		}
		public String getCaptionPadding()
		{
			return captionPadding;
		}
		public void setCaptionPadding(String captionPadding)
		{
			this.captionPadding = captionPadding;
		}
		public String getxAxisNamePadding()
		{
			return xAxisNamePadding;
		}
		public void setxAxisNamePadding(String xAxisNamePadding)
		{
			this.xAxisNamePadding = xAxisNamePadding;
		}
		public String getyAxisNamePadding()
		{
			return yAxisNamePadding;
		}
		public void setyAxisNamePadding(String yAxisNamePadding)
		{
			this.yAxisNamePadding = yAxisNamePadding;
		}
		public String getLabelPadding()
		{
			return labelPadding;
		}
		public void setLabelPadding(String labelPadding)
		{
			this.labelPadding = labelPadding;
		}
		public String getValuePadding()
		{
			return valuePadding;
		}
		public void setValuePadding(String valuePadding)
		{
			this.valuePadding = valuePadding;
		}
		public String getShowAboutMenuItem()
		{
			return showAboutMenuItem;
		}
		public void setShowAboutMenuItem(String showAboutMenuItem)
		{
			this.showAboutMenuItem = showAboutMenuItem;
		}
		public String getAboutMenuItemLabel()
		{
			return aboutMenuItemLabel;
		}
		public void setAboutMenuItemLabel(String aboutMenuItemLabel)
		{
			this.aboutMenuItemLabel = aboutMenuItemLabel;
		}
		public String getAboutMenuItemLink()
		{
			return aboutMenuItemLink;
		}
		public void setAboutMenuItemLink(String aboutMenuItemLink)
		{
			this.aboutMenuItemLink = aboutMenuItemLink;
		}
		
	}
	public class SetObj
	{
		private String label;		//标签名称
		private String value;		//值
		private String link;		//链接地址
		private String displayValue;//显示的值
		private String color;		//颜色
		private String toolText;	//提示信息
		private String showName;	//显示label名称
		public String getLabel()
		{
			return label;
		}
		public void setLabel(String label)
		{
			this.label = label;
		}
		public String getValue()
		{
			return value;
		}
		public void setValue(String value)
		{
			this.value = value;
		}
		public String getLink()
		{
			return link;
		}
		public void setLink(String link)
		{
			this.link = link;
		}
		public String getDisplayValue()
		{
			return displayValue;
		}
		public void setDisplayValue(String displayValue)
		{
			this.displayValue = displayValue;
		}
		public String getColor()
		{
			return color;
		}
		public void setColor(String color)
		{
			this.color = color;
		}
		public String getToolText()
		{
			return toolText;
		}
		public void setToolText(String toolText)
		{
			this.toolText = toolText;
		}
		public String getShowName()
		{
			return showName;
		}
		public void setShowName(String showName)
		{
			this.showName = showName;
		}
		
	}
	
	public class StylesObj
	{
		private List<DefinitionObj> definition;		//样式定义集合
		private List<ApplicationObj> application;	//颜色应用集合
		public List<DefinitionObj> getDefinition()
		{
			return definition;
		}
		public void setDefinition(List<DefinitionObj> definition)
		{
			this.definition = definition;
		}
		public List<ApplicationObj> getApplication()
		{
			return application;
		}
		public void setApplication(List<ApplicationObj> application)
		{
			this.application = application;
		}
		
	}
	public class DefinitionObj
	{
		private List<StyleObj> definition;//样式集合
	
		public List<StyleObj> getDefinition()
		{
			return definition;
		}
	
		public void setDefinition(List<StyleObj> definition)
		{
			this.definition = definition;
		}
		
	}
	public class StyleObj
	{
		private String name;		//名称
		private String type;		//类型
		private String font;		//字体
		private String size;		//大小
		private String color;		//颜色
		private String bold;		//粗体
		private String italic;		//斜体
		private String underline;	//下划线
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public String getType()
		{
			return type;
		}
		public void setType(String type)
		{
			this.type = type;
		}
		public String getFont()
		{
			return font;
		}
		public void setFont(String font)
		{
			this.font = font;
		}
		public String getSize()
		{
			return size;
		}
		public void setSize(String size)
		{
			this.size = size;
		}
		public String getColor()
		{
			return color;
		}
		public void setColor(String color)
		{
			this.color = color;
		}
		public String getBold()
		{
			return bold;
		}
		public void setBold(String bold)
		{
			this.bold = bold;
		}
		public String getItalic()
		{
			return italic;
		}
		public void setItalic(String italic)
		{
			this.italic = italic;
		}
		public String getUnderline()
		{
			return underline;
		}
		public void setUnderline(String underline)
		{
			this.underline = underline;
		}
		
	}
	public class ApplicationObj
	{
		private List<ApplyObj> definition;//应用集合
	
		public List<ApplyObj> getDefinition()
		{
			return definition;
		}
	
		public void setDefinition(List<ApplyObj> definition)
		{
			this.definition = definition;
		}
		
	}
	public class ApplyObj
	{
		private String toobject;	//作用的对象名称
		private String styles;		//对应的样式名称
		public String getToobject()
		{
			return toobject;
		}
		public void setToobject(String toobject)
		{
			this.toobject = toobject;
		}
		public String getStyles()
		{
			return styles;
		}
		public void setStyles(String styles)
		{
			this.styles = styles;
		}
		
	}
}