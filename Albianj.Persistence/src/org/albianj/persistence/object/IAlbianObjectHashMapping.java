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
	 * @param �ö��������writer routings
	 * @param ��Ҫ�洢�Ķ���
	 * @return �ö���ɢ�е���routings
	 */
	public List<IRoutingAttribute> mappingWriterRouting(Map<String,IRoutingAttribute> routings,IAlbianObject obj);
	/**
	 * @param �ö�������reader routings
	 * @param ��ѯ����
	 * @param ��������
	 * @return ���Ҹö����routing
	 */
	public IRoutingAttribute mappingReaderRouting(Map<String,IRoutingAttribute> routings,Map<String,IFilterCondition> wheres,
			Map<String,IOrderByCondition> orderbys);
	/**
	 * @param �ö���ɢ�е���routing
	 * @param ��Ҫɢ�еĶ���
	 * @return �ö���ɢ�е�����������
	 */
	public String mappingWriterTable(IRoutingAttribute routing,IAlbianObject obj);

	/**
	 * @param �ö���ɢ�е���reader routing
	 * @param ��ѯ����
	 * @param ��������
	 * @return ��ѯ�ö������������
	 */
	public String mappingReaderTable(IRoutingAttribute routing,Map<String,IFilterCondition> wheres,
			Map<String,IOrderByCondition> orderbys);
}
