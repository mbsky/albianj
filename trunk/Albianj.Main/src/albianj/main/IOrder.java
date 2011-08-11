package albianj.main;


import org.albianj.persistence.object.IAlbianObject;

public interface IOrder extends IAlbianObject
{
	public String getName();
	public void setName(String name);
	public double getPrice();
	public void setPrice(double price);
}
