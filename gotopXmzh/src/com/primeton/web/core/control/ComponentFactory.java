package com.primeton.web.core.control;

import com.eos.system.exception.EOSException;
import com.primeton.ext.data.vmodel.ControlAttr;
import com.primeton.ext.data.vmodel.Field;
import com.primeton.ext.data.vmodel.FieldEdit;
import com.primeton.ext.data.vmodel.FieldView;
import com.primeton.ext.data.vmodel.ViewModelHelper;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ComponentFactory
{
  private static Properties controlTypes = null;

  private static Set<String> extendAttrs = new HashSet();

  public static void init() throws EOSException {
    extendAttrs.add("notNull");
    extendAttrs.add("minlen");
    extendAttrs.add("maxlen");
    extendAttrs.add("lengthUnit");
    extendAttrs.add("minValue");
    extendAttrs.add("maxValue");
    extendAttrs.add("enumValue");
    extendAttrs.add("checkType");
    extendAttrs.add("checkFormat");
    extendAttrs.add("totalDigits");
    extendAttrs.add("fracDigits");

    InputStream in = null;
    try {
      in = ComponentFactory.class.getResourceAsStream("component_config.properties");

      controlTypes = new Properties();
      controlTypes.load(in);
      in.close();
    } catch (Exception e) {
      throw new EOSException("17012101", "控件库配置加载失败", e);
    }
  }

  public static WebComponent createWebComponent(String ctype)
    throws EOSException
  {
    return createWebComponent(ctype, false);
  }

  public static WebComponent createWebComponent(String ctype, String modelField)
    throws EOSException
  {
    if (modelField == null) {
      return createWebComponent(ctype, false);
    }

    Field field = ViewModelHelper.getField(modelField);
    WebComponent comp = createWebComponent(ctype, false);
    int type = 2;
    field.getView();

    if ((ctype.equalsIgnoreCase("label")) || (ctype.equalsIgnoreCase("dictwrite")))
    {
      type = 1;
    }
    if (type == 1) {
      if (!ctype.equalsIgnoreCase(field.getView().getType())) throw new EOSException("Type inharmonious!");
    }
    else if (!ctype.equalsIgnoreCase(field.getEdit().getType())) throw new EOSException("Type inharmonious!");

    setDictAttribute(comp, field);
    setAttribute(comp, type, field);

    return comp;
  }

  public static WebComponent createWebComponent(String ctype, boolean isDict)
    throws EOSException
  {
    WebComponent component = null;

    if (controlTypes == null)
      init();
    String compClassName = controlTypes.getProperty(ctype);
    if (compClassName != null) {
      try {
        component = (WebComponent)Class.forName(compClassName).newInstance();
      }
      catch (Exception e) {
        throw new EOSException("17012103", "无法实例化控件对象(" + compClassName + ")");
      }
    }
    else {
      throw new EOSException("17012102", "无效的页面控件类型(" + ctype + ")");
    }
    return component;
  }

  public static WebComponent createWebComponent(Field field, int type)
    throws EOSException
  {
    WebComponent component = null;
    String ctype = null;
    if (type == 1)
      ctype = field.getView().getType();
    else {
      ctype = field.getEdit().getType();
    }
    component = createWebComponent(ctype, field.isDict().booleanValue());

    setDictAttribute(component, field);

    setAttribute(component, type, field);

    return component;
  }

  public static void setAttribute(WebComponent component, int type, Field field)
  {
    List attrList = null;
    if (type == 1) {
      FieldView view = field.getView();
      attrList = view.getAttr();
    } else {
      FieldEdit edit = field.getEdit();
      attrList = edit.getAttr();
      component.setName(field.getEdit().getName());
      if (edit.getDefaultValue() != null) {
        component.setDefaultValue(edit.getDefaultValue());
      }
    }
    for (int i = 0; i < attrList.size(); i++) {
      ControlAttr attr = (ControlAttr)attrList.get(i);
      String name = attr.getName();
      if (name.equals("value"))
        component.setValue(attr.getValue());
      else if (name.equals("styleClass"))
        component.setAttribute("class", attr.getValue());
      else
        component.setAttribute(name, attr.getValue());
    }
  }

  public static void setDictAttribute(WebComponent component, Field field)
  {
    if (field.isDict().booleanValue()) {
      component.setDict(true);
      component.setDictTypeId(field.getDictTypeId());
    }
  }
}