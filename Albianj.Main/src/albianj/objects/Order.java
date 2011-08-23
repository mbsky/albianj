package albianj.objects;

import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.impl.FreeAlbianObject;

public class Order extends FreeAlbianObject implements IOrder
{

	private String name = "";
	private double price = 0;
	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String name)
	{
		// TODO Auto-generated method stub
this.name = name;
	}

	@Override
	public double getPrice()
	{
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public void setPrice(double price)
	{
		// TODO Auto-generated method stub
this.price = price;
	}

}
