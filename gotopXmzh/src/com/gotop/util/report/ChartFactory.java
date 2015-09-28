package com.gotop.util.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.Element;
import jofc2.model.elements.LineChart;
import jofc2.model.elements.PieChart;
import jofc2.model.elements.StackedBarChart;
import jofc2.model.elements.BarChart.Bar;
import jofc2.model.elements.PieChart.Slice;


@SuppressWarnings("unchecked")
public class ChartFactory implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final int PIE_CHART=0;//饼图
	public static final int BAR_CHART=1;//柱状图
	public static final int LINE_CHART=2;//曲线图
	public static final int STACKEDBAR_CHART=3;//组合柱状图
	
	private int chartType=0;//报表类型
	private String chartTitleSytle="font-size:20px; color:#ff0000; margin: 5px; background-color: #505050; padding:5px; padding-left: 20px; padding-right: 20px;";//报表标题样式
	
	private String chartTitle="";//报表标题
	private String ylegend="";//Y轴注释
	private String xlegend="";//X轴注释
	private String chartText="";//说明
	private String tipUnit="";//提示单位
	private int xStep=1;//x轴步进
	private String linkUrlOrJsFunc="http://localhost:8080/";
	
	private List<String> titleList=null;//保存标题列表
	private List<Number> dataList=null;//保存数据列表
	
	/**
	 * @param titleList 数据显示说明
	 * @param dataList  数据集合
	 * @param chartTitle 报表标题
	 * @param chartTitleSytle 报表标题样式
	 * @param chartType 报表种类
	 * 
	 * **/
	public ChartFactory(List<String> titleList, List<Number> dataList,String chartTitle,String chartTitleSytle,int chartType,String chartText,String ylegend,String xlegend,String tipUnit,int xStep,String linkUrlOrJsFunc) throws Exception
	{
		super();
		this.titleList=titleList;
		this.dataList=dataList;
		this.chartTitle=chartTitle;
		this.chartText=chartText;
		this.ylegend=ylegend;
		this.xlegend=xlegend;
		this.tipUnit=tipUnit;
		this.xStep=xStep;
		this.linkUrlOrJsFunc=linkUrlOrJsFunc;
		
		if(null!=chartTitleSytle && chartTitleSytle.trim().length()>0)
		{
			this.chartTitleSytle=chartTitleSytle;
		}
		if(chartType>=0 && chartType<=3)
		{
			this.chartType=chartType;
		}
		else
		{
			this.chartType=0;
		}
		initChart();
	}
	
	public ChartFactory(int chartType, String chartTitle, String tipUnit,
			List<String> titleList, List<Number> dataList)
	{
		super();
		this.chartType = chartType;
		this.chartTitle = chartTitle;
		this.tipUnit = tipUnit;
		this.titleList = titleList;
		this.dataList = dataList;
	}

	public ChartFactory(int chartType,String chartTitle, String tipUnit, String linkUrlOrJsFunc,
			List<String> titleList, List<Number> dataList)
	{
		super();
		this.chartType = chartType;
		this.chartTitle = chartTitle;
		this.tipUnit = tipUnit;
		this.linkUrlOrJsFunc = linkUrlOrJsFunc;
		this.titleList = titleList;
		this.dataList = dataList;
	}

	/**
	 * 初始化判断
	 * **/
	private void initChart() throws Exception
	{
		if(null==titleList && !(titleList instanceof List) && titleList.size()<1)
		{
			throw new Exception("传入的标题集合为空或不是List对象");
		}
		if(null==dataList && !(dataList instanceof List) && dataList.size()<1)
		{
			throw new Exception("传入的数据集合为空或不是List对象");
		}
		if(titleList.size()!=dataList.size())
		{
			throw new Exception("传入的数据集合中的元素个数同标题集合中的元素个数不相同");
		}
	}
	
	public String constructionChart()
	{
		//容器设置
		Chart chart=new Chart(chartTitle,chartTitleSytle);
		List<Element> charEle=new ArrayList<Element>();
		
		switch(chartType)
		{
			//饼状
			case ChartFactory.PIE_CHART:
			{
				//元素设置
				PieChart pie=new PieChart();
				pie.setFontSize(12);//设置字体大小
				pie.setAnimate(true);//设置动漫
				pie.setBorder(1);//设置边框
				pie.setStartAngle(100);//设置角度
				pie.setRadius(100);//设置半径
				pie.setAlpha(50f);//设置透明度0.0f~100.0f
				List<Slice> sliceList=new ArrayList<Slice>();
				
				int isize=titleList.size();
				for(int i=0;i<isize;i++)
				{
					Number num=(Number)dataList.get(i);//数据第一行为报表数据
					Slice slice=new Slice(num,titleList.get(i));
					slice.setText("~"+linkUrlOrJsFunc);
					slice.setOnMouseOverAlpha();
					slice.setOnMouseOverBreakout();
					sliceList.add(slice);
				}
				pie.addSlices(sliceList);

				pie.setColours( new String[] { "0x336699" , "0x88AACC" , "0x999933" ,
						"0x666699" , "0xCC9933" , "0x006666" , "0x3399FF" , "0x993300" ,
						"0xAAAA77" , "0x666666" , "0xFFCC66" , "0x6699CC" , "0x663366" ,
						"0x9999CC" , "0xAAAAAA" , "0x669999" , "0xBBBB55" , "0xCC6600" ,
						"0x9999FF" , "0x0066CC" , "0x99CCCC" , "0x999999" , "0xFFCC00" ,
						"0x009999" , "0x99CC33" , "0xFF9900" , "0x999966" , "0x66CCCC" ,
						"0x339966" , "0xCCCC33" , "0xB6D6FF" , "0x0066CC" , "#9933CC" ,
						"#639F45"});//设置颜色
				pie.setTooltip( "#val# / #total#<br> 占百分之 #percent#");// 鼠标移动上去后提示内容
				
				charEle.add(pie);
				chart.addElements(charEle);
				break;
			}
			//柱状图
			case ChartFactory.BAR_CHART:
			{
				int max=100;
				BarChart barchart=new BarChart(BarChart.Style.THREED);
				barchart.setText(chartText);
				//barchart.setTooltip("#x_label#在区域的值#val#,\n所在区域最高值#high#,\n所在区域最低值#low#");
				
				List<Bar> barList = new ArrayList<Bar>();
			    List<Label> labels = new ArrayList<Label>();
				
				XAxis xaxis=new XAxis();
				YAxis yaxis=new YAxis();
				
				xaxis.setGridColour("#224488");
				yaxis.setGridColour("#884422");
				
				
				int isize=titleList.size();
				for(int i=0;i<isize;i++)
				{
					Label label=new Label(titleList.get(i));
					labels.add(label);
					Number num=(Number)dataList.get(i);
					Bar bar=new Bar(num,tipUnit);
					bar.setColour("0x88AACC~"+linkUrlOrJsFunc);
					int val=num.intValue();
					bar.setTooltip(val+tipUnit);
				
					if(val>max)
					{
						max =val;
			        }
					barList.add(bar);
				}
				xaxis.setSteps(xStep);
				xaxis.addLabels(labels);
				barchart.addBars(barList);
				charEle.add(barchart);
				chart.addElements(charEle);
				if (max >= 10)
				{
			    	yaxis.setMax(max+10);
			    	yaxis.setSteps(50);
		        }
		        else
		        {
		        	yaxis.setMax(max+5); //y轴最大值
		        	yaxis.setSteps(50); //步进
		        }
				
				Text xText = new Text(xlegend,Text.createStyle(12, "#736AFF", Text.TEXT_ALIGN_CENTER));
				Text yText = new Text(ylegend,Text.createStyle(12, "#736AFF", Text.TEXT_ALIGN_CENTER));

				chart.setYLegend(yText);
				chart.setXLegend(xText);
				chart.setYAxis(yaxis);
				chart.setXAxis(xaxis);
				
				break;
			}
			//曲线图
			case ChartFactory.LINE_CHART:
			{
				LineChart line=new LineChart();
				line.setFontSize(12);
				line.setTooltip("#val#%");
				XAxis xaxis=new XAxis();
				
				YAxis yaxis=new YAxis();
				List<LineChart.Dot> dotList=new ArrayList<LineChart.Dot>();
				List<Label> labelList=new ArrayList<Label>(); 
				int max=100;
				
				int isize=titleList.size();
				for(int i=0;i<isize;i++)
				{
					Label label=new Label(titleList.get(i));
					labelList.add(label);
					Number num=(Number)dataList.get(i);
					//Number num=99;
					LineChart.Dot dot=new LineChart.Dot(num);
					dot.setColour("#736AFF~"+linkUrlOrJsFunc);
					//dot.setDotSize(2);//设置点的大小
					//dot.setHaloSize(23);//设置点覆盖的线的范围
					int val=num.intValue();
					if(val>max)
					{
						max =val;
			        }
					dotList.add(dot);
				}
				xaxis.setSteps(xStep);
				xaxis.addLabels(labelList);
				line.addDots(dotList);
				
				if (max>=10)
				{
			    	yaxis.setMax(max+10);
			    	yaxis.setSteps(50);
		        }
		        else
		        {
		        	yaxis.setMax(max+5); //y轴最大值
		        	yaxis.setSteps(50); //步进
		        }
				charEle.add(line);
				chart.addElements(charEle);
				
				Text xText = new Text(xlegend,Text.createStyle(20, "#736AFF", Text.TEXT_ALIGN_CENTER));
				Text yText = new Text(ylegend,Text.createStyle(20, "#736AFF", Text.TEXT_ALIGN_CENTER));

				chart.setYAxis(yaxis);
				chart.setXAxis(xaxis);
				chart.setXLegend(xText);
				chart.setYLegend(yText);
				
				break;
			}
			//组合柱状图
			case ChartFactory.STACKEDBAR_CHART:
			{
				StackedBarChart stackBar=new StackedBarChart();
				stackBar.setFontSize(12);
				stackBar.setText(chartText);
				
				XAxis xaxis=new XAxis();
				xaxis.set3D(0);
				xaxis.setColour("#df0fd0");
				xaxis.setGridColour("#00ff00");
				YAxis yaxis=new YAxis();
				int max=1000;
				
				List<StackedBarChart.Stack> stackList = new ArrayList<StackedBarChart.Stack>();
			    List<Label> labels = new ArrayList<Label>();
			   
			    int isize=titleList.size();
				for(int i=0;i<isize;i++)
				{
					StackedBarChart.Stack stack=new StackedBarChart.Stack();
					Label label=new Label(titleList.get(i));
					labels.add(label);
					
					Number num=(Number)dataList.get(i);
					//Number num=99;
					StackedBarChart.StackValue stackVal=new StackedBarChart.StackValue(num,"0x336699~"+linkUrlOrJsFunc);
					StackedBarChart.StackValue stackVal2 = new StackedBarChart.StackValue(i * 5 + 10,"#3334AD~"+linkUrlOrJsFunc);
					StackedBarChart.StackValue stackVal3 = new StackedBarChart.StackValue(i * 10 + 20,"#D54C78~"+linkUrlOrJsFunc);
					int val=num.intValue();
					val=val+100;
					if(val>max)
					{
						max =val;
			        }
					
					stack.addStackValues(stackVal);
					stack.addStackValues(stackVal2);
					stack.addStackValues(stackVal3);
					
					stackList.add(stack);
					
					
				}
				xaxis.setSteps(xStep);
				xaxis.addLabels(labels);
				stackBar.addStack(stackList);
				
				StackedBarChart.Key stackKey=new StackedBarChart.Key("0x336699","title1",10);
				StackedBarChart.Key stackKey2=new StackedBarChart.Key("#3334AD","title2",10);
				StackedBarChart.Key stackKey3=new StackedBarChart.Key("#D54C78","title3",10);
				stackBar.addKeys(stackKey,stackKey2,stackKey3);
				
				charEle.add(stackBar);
				chart.addElements(charEle);
				
				yaxis.set3D(0);
				yaxis.setColour("#d000d0");
				yaxis.setGridColour("#00ff00");
				
				
				if (max>=10)
				{
					yaxis.setMax(max+10.0); //y 轴最大值   
					yaxis.setSteps(max/10*1.0); // 步进   
		        }
		        else
		        {
		        	yaxis.setMax(max+5.0); //y 轴最大值   
					yaxis.setSteps(max/10*1.0); // 步进   
		        }
				
				chart.setYAxis(yaxis);  
				chart.setXAxis(xaxis);  
				
				Text xText = new Text(xlegend,Text.createStyle(20, "#736AFF", Text.TEXT_ALIGN_CENTER));
				Text yText = new Text(ylegend,Text.createStyle(20, "#736AFF", Text.TEXT_ALIGN_CENTER));

				chart.setXLegend(xText);
				chart.setYLegend(yText);
				
				break;
			}
			
		}
		
		String json=chart.toString();
		String keyContextName="text";//text  colour
		/**
		 * 设置处理标准 js函数必须写在显示flash的页面上
		 * //饼状图
		 * slice.setText("pie text~http://localhost:8080/ct/jsp/StaticDataTest.jsp?josonName=huxj&josonValue=胡小军");
		 * 或者
		 * slice.setText("pie text~javascript:js_Function()");
		 * //柱状图
		 * bar.setColour("0x336699~http://localhost:8080/ct/jsp/StaticDataTest.jsp?josonName=huxj&josonValue=胡小军");
		 * 或者
		 * bar.setColour("0x336699~javascript:js_Function()");
		 * //曲线图
		 * dot.setColour("0x336699~http://localhost:8080/ct/jsp/StaticDataTest.jsp?josonName=huxj&josonValue=胡小军");
		 * 或者
		 * dot.setColour("0x336699~javascript:js_Function()");
		 * //
		 * stackVal.setColour("0x336699~http://localhost:8080/ct/jsp/StaticDataTest.jsp?josonName=huxj&josonValue=胡小军");
		 * 或者 
		 * stackVal.setColour("0x336699~javascript:js_Function()");
		 * 
		 ***/
		if(chartType==0)//饼图
		{
			keyContextName="text";
		}
		else
		{
			keyContextName="colour";
		}
		json=addOnClickMethod(json,keyContextName,chartType);
		////System.out.println(json);
		return json;
	}
	
	/**
	 * 给json串增加on-click方法
	 * @param jsonStr JSON串
	 * @param key 包含链接地址的键
	 * @param chartType 那种报表
	 * **/
	private static String addOnClickMethod(String jsonStr,String key,int chartType)
	{
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		String jsone=jsonObject.getString("elements");
		JSONObject elements = JSONObject.fromObject(jsone.substring(1,jsone.length()-1));//去掉前后[]
		
		String eleValues=elements.getString("values");
		JSONArray ja=JSONArray.fromObject(eleValues);
		for(int i=0;i<ja.size();i++)
		{
			if(chartType==ChartFactory.STACKEDBAR_CHART)
			{
				JSONArray inja=JSONArray.fromObject(ja.get(i));
				for(int j=0;j<inja.size();j++)
				{
					JSONObject jsonObj=JSONObject.fromObject(inja.get(j));
					String keyText=jsonObj.getString(key);
					jsonObj.element("on-click", keyText.substring((keyText.indexOf("~")+1)));
					jsonObj.element(key, keyText.substring(0,keyText.indexOf("~")));
					inja.set(j, jsonObj);
				}
				ja.set(i, inja);
			}
			else
			{
				JSONObject jsonObj=JSONObject.fromObject(ja.get(i));
				String keyText=jsonObj.getString(key);
				jsonObj.element("on-click", keyText.substring((keyText.indexOf("~")+1)));
				jsonObj.element(key, keyText.substring(0,keyText.indexOf("~")));
				ja.set(i, jsonObj);
			}
		}
		elements.element("values", ja.toString());
		String newElements=elements.toString();
		jsonObject.element("elements", "["+newElements+"]");//补上前后[]
		return jsonObject.toString();
	}
	
}
