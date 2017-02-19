import java.util.*;

public class parkinglotlevels
{
	public static void main(String args[])
	{
		Lot lot1 = new Lot();
		
		
		Vehicle v1 = new Vehicle(1,100);
		Vehicle v2 = new Vehicle(2,101);
		Vehicle v3 = new Vehicle(3,102);
		Vehicle v4 = new Vehicle(1,103);
		Vehicle v5 = new Vehicle(1,104);
		Vehicle v6 = new Vehicle(1,105);
		
		lot1.park(v1);
		lot1.park(v2);
		lot1.park(v3);
		lot1.park(v4);
		lot1.unpark(v1);
		lot1.park(v5);
		
	}
}
class Vehicle
{
private int size;
private int id;
private boolean status;

public Vehicle(int s , int id)
{
	this.size = s;
	this.id =id;
	this.status= true;
}

public int getsize()
{
	return this.size;
}

public int getid()
{
	return this.id;
}
public boolean getstatus()
{
	return this.status;
}
}

class Slot
{
	private int size;
	private int id;
	private String status;
	
	public Slot(int s,int id)
	{
	this.size =s;
	this.id =id;
	this.status ="free";
	}
	
	public int getsize()
	{
		return this.size;
	}
	public int getid()
	{
		return this.id;
	}
	public String getstatus()
	{
		return this.status;
	}
	public void setstatus(String s)
	{
		this.status =s;
	}
}
class Ticket
{
	private int vid;
	private int sid;
	
	public Ticket(int v,int s)
	{
		this.vid =v;
		this.sid =s;
	}
	public void printticketinfo()
	{
		System.out.println("Ticket # :"+ this.vid +"Spot # :" +this.sid);
	}
}
class Lot
{
	public static final int levels =3;
	public static final int smallslots=3;
	public static final int medslots=3;
	public static final int largeslots=3;
	
	ArrayList<ArrayList<Slot>> slots;
	
	Map<Integer,Slot> occupiedslots;
	
	public Lot()
	{
		slots = new ArrayList<ArrayList<Slot>>();
		occupiedslots = new HashMap<Integer,Slot>();
		for(int i=0;i< levels; i++)
		{		
		ArrayList<Slot> level = new ArrayList<Slot>();
		
			
		for(int m=0;m<smallslots;m++)
		{
			level.add(new Slot(1,m));
		}
		for(int j= smallslots ; j< (smallslots + medslots); j++)
		{
			level.add(new Slot(2,j));
		}
		for(int k=(smallslots + medslots); k<(smallslots+medslots+largeslots);k++)
		{
			level.add(new Slot(3,k));
		}
		slots.add(level);
	}
		
		
	}
	
	public void park(Vehicle v)
	{
		Slot s = findslot(v);
		s.setstatus("occupied");
		Ticket t = new Ticket(v.getid(),s.getid());
		t.printticketinfo();
		occupiedslots.put(v.getid(), s);
		System.out.println("Vehicle "+ v.getid() +" is parked at "+ s.getid() +" whose size is " + s.getsize());
		
	}
	
	public Slot findslot(Vehicle v)
	{
		int vsize = v.getsize();
		for(ArrayList<Slot> temp:slots)
		{
			for(Slot s: temp)
			{
				if(vsize==s.getsize() && s.getstatus()=="free")
						{
						return s;
						}
			}
				
		}
		return null;
	}
	
	public void unpark(Vehicle v)
	{
		Slot s = occupiedslots.get(v.getid());
		s.setstatus("free");
		occupiedslots.remove(v.getid());
	}
	
	public void getparkedspot(Vehicle v)
	{
		Slot t = occupiedslots.get(v.getid());
		int tid=t.getid();
		System.out.println("Vehicle " + v.getid() +" is parked at " + tid);
	}
}
