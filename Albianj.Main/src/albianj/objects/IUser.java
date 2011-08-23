package albianj.objects;

import java.util.Date;

import org.albianj.persistence.object.IAlbianObject;

public interface IUser extends IAlbianObject
{
	public String getUserName();

	public void setUserName(String userName);

	public String getPassword();

	public void setPassword(String password);

	public Date getRegistrDate();

	public void setRegistrDate(Date registrDate);

	public Date getCreateTime();

	public void setCreateTime(Date createTime);

	public Date getLastMofidyTime();

	public void setLastMofidyTime(Date lastMofidyTime);

	public String getCreator();

	public void setCreator(String creator);

	public String getLastModifier();

	public void setLastModifier(String lastModifier);

	public String getNickname();

	public void setNickname(String nickname);

	public String getMobile();

	public void setMobile(String mobile);

	public String getMail();

	public void setMail(String mail);
}
