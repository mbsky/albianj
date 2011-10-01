package org.albianj.config.entity;

public interface IConfigAttribute
{
	public void setRole(Role role);
	public Role getRole();
	public IConfigStorageAttribute getConfigStorage();
	public void setConfigStorage(IConfigStorageAttribute configStorage);
	public IConfigNotifyAttribute getConfigNotify();
	public void  setConfigNotify(IConfigNotifyAttribute configNotify);
	public void setLoadMode(LoadMode loadMode);
	public LoadMode getLoadMode();
}
