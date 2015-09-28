package test.develop.ibatis;

import java.lang.reflect.Field;
import java.util.Properties;
import org.w3c.dom.Node;
import com.ibatis.common.xml.Nodelet;
import com.ibatis.common.xml.NodeletParser;
import com.ibatis.common.xml.NodeletUtils;
import com.ibatis.sqlmap.client.SqlMapException;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapParser;
import com.ibatis.sqlmap.engine.builder.xml.XmlParserState;

public class DySqlMapParser extends SqlMapParser {
	private XmlParserState myState = null;

	public DySqlMapParser(XmlParserState state) {
		super(state);
		this.myState = state;
		resetSqlNodelets();
	}

	private void resetSqlNodelets() {
		try {
			Field f = SqlMapParser.class.getDeclaredField("parser");
			f.setAccessible(true);
			NodeletParser p = (NodeletParser) f.get(this);
			p.addNodelet("/sqlMap/sql", new Nodelet() {
				public void process(Node node) throws Exception {
					Properties attributes = NodeletUtils.parseAttributes(node,
							myState.getGlobalProps());
					String id = attributes.getProperty("id");
					if (myState.isUseStatementNamespaces()) {
						id = myState.applyNamespace(id);
					}
					myState.getSqlIncludes().put(id, node);
				}
			});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
