import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Intersection {
	public int nodeID;
	public Location loc;
	
	
	public Intersection(String Line) {
		String[] data = Line.split("\t");
		this.nodeID = Integer.parseInt(data[0]);
		this.loc = Location.newFromLatLon(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
	}


	public double getNodeID() {
		return nodeID;
	}


	public Location getLoc() {
		return loc;
	}


	@Override
	public String toString() {
		return "Intersection [nodeID=" + nodeID + ", loc=" + loc.toString() + "]";
	}
	
	public void draw(Graphics g){
		Point p = loc.asPoint(AucklandCityMap.origin, AucklandCityMap.scale);
		int size = (int)(Math.cbrt(AucklandCityMap.scale));
		g.fillOval((int)Math.round(AucklandCityMap.xOffset + p.getX()-size/2), (int)Math.round(AucklandCityMap.yOffset + p.getY()-size/2), size, size);
	}


	
	
	
	

}
