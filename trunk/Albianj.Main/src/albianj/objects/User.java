package albianj.objects;

import java.util.Date;

import org.albianj.persistence.object.impl.FreeAlbianObject;

public class User extends FreeAlbianObject implements IUser
{
	private String userName = null;
	private String password = null;
	private Date registrDate = null;
	private Date createTime = null;
	private Date lastMofidyTime = null;
	private String creator = null;
	private String lastModifier = null;
	private String nickname = null;
	private String mobile = null;
	private String mail = null;
	
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getUserName()
	 */
	public String getUserName()
	{
		return userName;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setUserName(java.lang.String)
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getPassword()
	 */
	public String getPassword()
	{
		return password;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setPassword(java.lang.String)
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getRegistrDate()
	 */
	public Date getRegistrDate()
	{
		return registrDate;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setRegistrDate(java.util.Date)
	 */
	public void setRegistrDate(Date registrDate)
	{
		this.registrDate = registrDate;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getCreateTime()
	 */
	public Date getCreateTime()
	{
		return createTime;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setCreateTime(java.util.Date)
	 */
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getLastMofidyTime()
	 */
	public Date getLastMofidyTime()
	{
		return lastMofidyTime;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setLastMofidyTime(java.util.Date)
	 */
	public void setLastMofidyTime(Date lastMofidyTime)
	{
		this.lastMofidyTime = lastMofidyTime;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getCreator()
	 */
	public String getCreator()
	{
		return creator;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setCreator(java.lang.String)
	 */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getLastModifier()
	 */
	public String getLastModifier()
	{
		return lastModifier;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setLastModifier(java.lang.String)
	 */
	public void setLastModifier(String lastModifier)
	{
		this.lastModifier = lastModifier;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getNickname()
	 */
	public String getNickname()
	{
		return nickname;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setNickname(java.lang.String)
	 */
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getMobile()
	 */
	public String getMobile()
	{
		return mobile;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setMobile(java.lang.String)
	 */
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#getMail()
	 */
	public String getMail()
	{
		return mail;
	}
	/* (non-Javadoc)
	 * @see albianj.objects.efwef#setMail(java.lang.String)
	 */
	public void setMail(String mail)
	{
		this.mail = mail;
	}
}
