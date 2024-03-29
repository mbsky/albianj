package org.albianj.persistence.object;

import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 *
 */
public interface IAlbianObjectHashMapping
{
	/**
	 * @param 该对象的所有writer routings
	 * @param 需要存储的对象
	 * @return 该对象散列到的routings
	 */
	public List<IRoutingAttribute> mappingWriterRouting(Map<String,IRoutingAttribute> routings,IAlbianObject obj);
	/**
	 * @param 该对象所有reader routings
	 * @param 查询条件
	 * @param 排序条件
	 * @return 查找该对象的routing
	 */
	public IRoutingAttribute mappingReaderRouting(Map<String,IRoutingAttribute> routings,Map<String,IFilterCondition> wheres,
			Map<String,IOrderByCondition> orderbys);
	/**
	 * @param 该对象散列到的routing
	 * @param 需要散列的对象
	 * @return 该对象散列到的完整表名
	 */
	public String mappingWriterTable(IRoutingAttribute routing,IAlbianObject obj);

	/**
	 * @param 该对象散列到的reader routing
	 * @param 查询条件
	 * @param 排序条件
	 * @return 查询该对象的完整表明
	 */
	public String mappingReaderTable(IRoutingAttribute routing,Map<String,IFilterCondition> wheres,
			Map<String,IOrderByCondition> orderbys);
}
