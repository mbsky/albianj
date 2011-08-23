package albianj.objects;

import java.util.Date;

import org.albianj.persistence.object.IAlbianObject;

public interface ILogInfo extends IAlbianObject
{

	public static final int LOGGER_BIZOFFER_STYLE = 0;
	public static final int LOGGER_USER_STYLE = 1;
	public static final int LOGGER_ORDER_STYLE = 2;
	
	public Integer getStyle();

	public void setStyle(Integer style);

	public Date getCreateTime();

	public void setCreateTime(Date createTime);

	public String getCreator();

	public void setCreator(String creator);

	public String getContent();

	public void setContent(String content);

	public String getRemark();

	public void setRemark(String remark);
}
