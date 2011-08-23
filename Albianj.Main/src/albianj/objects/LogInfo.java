package albianj.objects;

import java.util.Date;

import org.albianj.persistence.object.impl.FreeAlbianObject;

public class LogInfo extends FreeAlbianObject implements ILogInfo
{
	private Integer style = null;
	private Date createTime = null;
	private String creator = null;
	private String content = null;
	private String remark = null;
	
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#getStyle()
	 */
	@Override
	public Integer getStyle()
	{
		return style;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#setStyle(java.lang.Integer)
	 */
	@Override
	public void setStyle(Integer style)
	{
		this.style = style;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#getCreateTime()
	 */
	@Override
	public Date getCreateTime()
	{
		return createTime;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#setCreateTime(java.util.Date)
	 */
	@Override
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#getCreator()
	 */
	@Override
	public String getCreator()
	{
		return creator;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#setCreator(java.lang.String)
	 */
	@Override
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#getContent()
	 */
	@Override
	public String getContent()
	{
		return content;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#setContent(java.lang.String)
	 */
	@Override
	public void setContent(String content)
	{
		this.content = content;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#getRemark()
	 */
	@Override
	public String getRemark()
	{
		return remark;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.ILogInfo1#setRemark(java.lang.String)
	 */
	@Override
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
}
