package com.gotop.util.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

public class Crit implements CriteriaSpecification, Serializable{
	protected static Logger log = Logger.getLogger(Crit.class);
	private static final long serialVersionUID = -3507690234243389601L;
	private DetachedCriteria detachedCriteria;
	private Criteria criteria;
	private Session session;
	private Projection projection;
	private List<Projection> groupList;
	private List<Order> orderList;
	private List<HashMap> _expr;
	private Class clazz;
	//扩展
	private Boolean distinct;
	public Crit(DetachedCriteria criteria, Session session){
		this.detachedCriteria = criteria;
		this.session = session;
	}
	public Crit(String entityName, Session session) {
		this.detachedCriteria = DetachedCriteria.forEntityName(entityName);
	}
	public Crit(String entityName, String alias, Session session) {
		this.detachedCriteria = DetachedCriteria.forEntityName(entityName,alias);
		this.session = session;
	}
	
	public Crit(Class clazz, Session session,boolean isClazz) {
		this.detachedCriteria = DetachedCriteria.forClass(clazz);
		this.session = session;
		if(isClazz){
			this.clazz = clazz;
		}
	}
	public Crit(Class clazz, Session session) {
		this.detachedCriteria = DetachedCriteria.forClass(clazz);
		this.session = session;
	}
	
	public Crit(Class clazz, String alias, Session session) {
		this.detachedCriteria = DetachedCriteria.forClass(clazz,alias);
		this.session = session;
	}
	
	public Crit add(Criterion criterion) {
		if(criterion==null){
			return this;	
		}
		if(criteria!=null){
			criteria = this.criteria.add(criterion);
		}else{
			detachedCriteria = this.detachedCriteria.add(criterion);
		}
		return this;
	}
	/**
	 * 扩展方法
	 * @param criterion
	 * @return
	 */
	public Crit add(Criterion... criterion) {
		if(criterion==null){
			return this;	
		}
		if(criteria!=null){
			for (int i = 0; i < criterion.length; i++) {
				if(criterion[i]==null){
					continue;
				}
				criteria = this.criteria.add(criterion[i]);
			}
		}else{
			for (int i = 0; i < criterion.length; i++) {
				if(criterion[i]==null){
					continue;
				}
				detachedCriteria = this.detachedCriteria.add(criterion[i]);
			}
		}
		return this;
	}
	/**
	 * 修改
	 * @param order
	 * @return
	 */
	public Crit addOrder(Order order) {
		if(orderList==null){
			orderList = new ArrayList<Order>();
		}
		orderList.add(order);
		return this;
	}
	/**
	 * 扩展
	 * @param order
	 * @return
	 */
	public Crit addOrder(List<Order> orderL) {
		if(orderL==null || orderL.size()<1){
			return this;
		}
		boolean cbool = (criteria!=null);
		for (int i = 0; i < orderL.size(); i++) {
			if(cbool){//是否存在criteria
				criteria = this.criteria.addOrder(orderL.get(i));
			}else{
				detachedCriteria = this.detachedCriteria.addOrder(orderL.get(i));
			}
		}
		return this;
	}
	/**
	 * 扩展
	 * @param proj
	 * @return
	 */
	public Crit addGroup(Projection proj){
		if(groupList==null){
			groupList = new ArrayList<Projection>();
		}
		groupList.add(proj);
		return this;
	}
	
	public Crit createAlias(String associationPath, String alias) throws HibernateException {
		if(criteria!=null){
			criteria = this.criteria.createAlias(associationPath, alias);
		}else{
			detachedCriteria = this.detachedCriteria.createAlias(associationPath, alias);
		}
		return this;
	}

	public String getAlias() {
		return this.detachedCriteria.getAlias();
	}

	public Crit setFetchMode(String associationPath, FetchMode mode) throws HibernateException {
		if(criteria!=null){
			criteria = this.criteria.setFetchMode(associationPath, mode);
		}else{
			detachedCriteria = this.detachedCriteria.setFetchMode(associationPath, mode);
		}
		return this;
	}

	public Crit setResultTransformer(ResultTransformer resultTransformer) {
		if(criteria!=null){
			criteria = this.criteria.setResultTransformer(resultTransformer);
		}else{
			detachedCriteria = this.detachedCriteria.setResultTransformer(resultTransformer);
		}
		return this;
	}
	/**
	 * Get an executable instance of <literal>Criteria</literal>,
	 * to actually run the query.
	 */
	public Criteria getExecutableCriteria(Session session) {
		if(criteria==null){
			return this.detachedCriteria.getExecutableCriteria(session);
		}else{
			return criteria;
		}
	}
	
	public static DetachedCriteria forEntityName(String entityName) {
		return DetachedCriteria.forEntityName(entityName);
	}
	
	public static DetachedCriteria forEntityName(String entityName, String alias) {
		return DetachedCriteria.forEntityName(entityName, alias);
	}
	
	public static DetachedCriteria forClass(Class clazz) {
		return DetachedCriteria.forEntityName(clazz.getName());
	}
	
	public static DetachedCriteria forClass(Class clazz, String alias) {
		return DetachedCriteria.forEntityName( clazz.getName(),alias );
	}
	
	public void detachedCriteria(DetachedCriteria detachedCriteria){
		this.detachedCriteria = detachedCriteria;
	}
	
	public String toString() {
		return "DetachableCriteria(" + detachedCriteria.toString() + ')';
	}
	/**
	 * criteria
	 * @param associationPath
	 * @return
	 * @throws HibernateException
	 */
	public Crit createCriteria(String associationPath) throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.createCriteria(associationPath);
		return this;
	}
	public Crit createAlias(String associationPath, String alias, int joinType) throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = criteria.createAlias(associationPath, alias, joinType);
		return this;
	}

	public Crit createCriteria(String associationPath, int joinType) throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.createCriteria(associationPath, joinType);
		return this;
	}

	public Crit createCriteria(String associationPath, String alias) throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.createCriteria(associationPath, alias);
		return this;
	}

	public Crit createCriteria(String associationPath, String alias, int joinType) throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.createCriteria(associationPath, alias, joinType);
		return this;
	}

	public List list() throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		if(distinct!=null && distinct==true){
			this.setDistinct(this.projection);
		}
		if(this.clazz!=null){
			this.setResultTransformer(Transformers.aliasToBean(this.clazz));
		}
		return this.criteria.list();
	}
	/**
	 * 扩展???
	 * @param page
	 * @return
	 * @throws HibernateException
	 */
	public List list(Page page) throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		if(distinct!=null && distinct==true){
			this.setDistinct(this.projection);
		}
		if("true".equals(page.getIsCount())){//分页处理
			Projection projection = this.projection;
			this.setProjection(Projections.rowCount());//统计总页数
			this.addProjection(groupList);
			if(groupList==null || groupList.size()<1){
				Integer rowCount = (Integer)this.criteria.uniqueResult();
				page.setCount(rowCount);
			}else{
				ScrollableResults cats = this.criteria.scroll();
				cats.last();
				int count = cats.getRowNumber();
				page.setCount(count);
			}
	        pageCondExpand pce = new pageCondExpand();
	        pce.putPageCond(page);
	        
	        //分页属性设置
			this.setProjection(projection);
			this.addProjection(groupList);//增加group by
			this.addOrder(orderList);//增加order by
			this.criteria.setFirstResult(page.getBegin());
			int paglen = page.getLength();
			if(paglen<page.getSize()){
				paglen = page.getSize();
			}
			this.criteria.setMaxResults(paglen);
		}
		if(this.clazz!=null){
			this.setResultTransformer(Transformers.aliasToBean(this.clazz));
		}
		return this.criteria.list();
	}

	public ScrollableResults scroll() throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		return this.criteria.scroll();
	}

	public ScrollableResults scroll(ScrollMode scrollMode) throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		return this.criteria.scroll(scrollMode);
	}

	public Crit setCacheMode(CacheMode cacheMode) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setCacheMode(cacheMode);
		return this;
	}

	public Crit setCacheRegion(String cacheRegion) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setCacheRegion(cacheRegion);
		return this;
	}

	public Crit setCacheable(boolean cacheable) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setCacheable(cacheable);
		return this;
	}

	public Crit setComment(String comment) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setComment(comment);
		return this;
	}
	
	public Crit setFetchSize(int fetchSize) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setFetchSize(fetchSize);
		return this;
	}

	public Crit setFirstResult(int firstResult) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setFirstResult(firstResult);
		return this;
	}

	public Crit setFlushMode(FlushMode flushMode) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setFlushMode(flushMode);
		return this;
	}

	public Crit setLockMode(LockMode lockMode) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setLockMode(lockMode);
		return this;
	}

	public Crit setLockMode(String alias, LockMode lockMode) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setLockMode(alias, lockMode);
		return this;
	}

	public Crit setMaxResults(int maxResults) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setMaxResults(maxResults);
		return this;
	}

	public Crit setProjection(Projection projection) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		this.projection = projection;
		criteria = this.criteria.setProjection(projection);
		return this;
	}
	/**
	 * 扩展
	 * @param projection
	 * @return
	 */
	public Crit addProjection(Projection projection) {
		ProjectionList plt = null;
		if(this.projection == null){
			plt = Projections.projectionList();
			plt.add(projection);
		}else if(this.projection instanceof ProjectionList){
			plt = (ProjectionList)this.projection;
			plt.add(projection);
		}else if(projection instanceof ProjectionList){
			plt = (ProjectionList)projection;
		}else{
			plt = Projections.projectionList();
			plt.add(this.projection);
			plt.add(projection);
		}
		return this.setProjection(plt);
	}
	/**
	 * 扩展
	 * @param projection
	 * @return
	 */
	public Crit addProjection(Projection projection,String pname) {
		ProjectionList plt = null;
		if(this.projection == null){
			plt = Projections.projectionList();
			plt.add(projection,pname);
		}else if(this.projection instanceof ProjectionList){
			plt = (ProjectionList)this.projection;
			plt.add(projection,pname);
		}else if(projection instanceof ProjectionList){
			plt = (ProjectionList)projection;
		}else{
			plt = Projections.projectionList();
			plt.add(this.projection);
			plt.add(projection,pname);
		}
		return this.setProjection(plt);
	}
	/**
	 * 扩展
	 * @param projection
	 * @return
	 */
	public Crit addProjection(List<Projection> groupList) {
		if(groupList==null || groupList.size()<1){
			return this;
		}
		ProjectionList plt = null;
		if(this.projection == null){
			plt = Projections.projectionList();
		}else if(this.projection instanceof ProjectionList){
			plt = (ProjectionList)this.projection;
		}else{
			plt = Projections.projectionList();
			plt.add(this.projection);
		}
		for (int i = 0; i < groupList.size(); i++) {
			plt.add(groupList.get(i));
		}
		return this.setProjection(plt);
	}
	/**
	 * 扩展
	 * @param projection
	 * @return
	 */
	public Crit addProjection(Projection... groupList) {
		if(groupList==null || groupList.length<1){
			return this;
		}
		ProjectionList plt = null;
		if(this.projection == null){
			plt = Projections.projectionList();
		}else if(this.projection instanceof ProjectionList){
			plt = (ProjectionList)this.projection;
		}else{
			plt = Projections.projectionList();
			plt.add(this.projection);
		}
		for (int i = 0; i < groupList.length; i++) {
			plt.add(groupList[i]);
		}
		return this.setProjection(plt);
	}
	
	public Projection setDistinct(Projection proj){
		return Projections.distinct(proj);
	}
	/**
	 * 扩展方法
	 * @return
	 */
	public Projection setDistinct(){
		if(this.projection == null){
			log.error("设置distinct失败，distinct之前需指定select field");
		}
		return Projections.distinct(this.projection);
	}
	/**
	 * 扩展方法
	 * @return
	 */
	public void setDistnect(Boolean distinct){
		this.distinct = distinct;
	}

	public Crit setTimeout(int timeout) {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		criteria = this.criteria.setTimeout(timeout);
		return this;
	}

	public Object uniqueResult() throws HibernateException {
		if(criteria==null){
			criteria = this.getExecutableCriteria(session);
		}
		return this.criteria.uniqueResult();
	}
	/**
	 * 扩展
	 * @param propertyName
	 * @return
	 */
	public Crit desc(String propertyName){
		Order order = Order.desc(propertyName);
		this.addOrder(order);
		return this;
	}
	/**
	 * 扩展
	 * @param propertyName
	 * @return
	 */
	public Crit asc(String propertyName){
		Order order = Order.asc(propertyName);
		this.addOrder(order);
		return this;
	}
	public List<HashMap> get_expr() {
		return _expr;
	}
	public void set_expr(List<HashMap> _expr) {
		this._expr = _expr;
	}
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
}
