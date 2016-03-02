import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class RoadSeg {
	private int roadID;
	private double length;
	private int nodeID1;
	private int nodeID2;
	private Location[] coords;
	
	
	public RoadSeg(String Line) {
		String[] data = Line.split("\t");
		this.roadID = Integer.parseInt(data[0]);
		this.length = Double.parseDouble(data[1]);
		this.nodeID1 = Integer.parseInt(data[2]);
		this.nodeID2 = Integer.parseInt(data[3]);
		this.coords = getCoords(data);
	}


	private Location[] getCoords(String[] line) {
		ArrayList<Location> locations = new ArrayList<>();
		int size = 0;
		for(int i = 4; i < line.length; i+= 2){
			locations.add(Location.newFromLatLon(Double.parseDouble(line[i]), Double.parseDouble(line[i+1])));
			size++;
		}
		return locations.toArray(new Location[size]);
	}
	
	public int[] getXPoints(){
		int[] xPoints = new int[coords.length];
		for(int i = 0; i < xPoints.length; i++){
			Point p = coords[i].asPoint(AucklandCityMap.origin, AucklandCityMap.scale);
			xPoints[i] = (int)Math.round(AucklandCityMap.xOffset + p.x);
		}
		return xPoints;
	}
	
	public int[] getYPoints(){
		int[] yPoints = new int[coords.length];
		for(int i = 0; i < yPoints.length; i++){
			Point p = coords[i].asPoint(AucklandCityMap.origin, AucklandCityMap.scale);
			yPoints[i] = (int)Math.round(AucklandCityMap.yOffset + p.y);
		}
		return yPoints;
	}


	public int getRoadID() {
		return roadID;
	}


	public double getLength() {
		return length;
	}


	public int getNodeID1() {
		return nodeID1;
	}


	public int getNodeID2() {
		return nodeID2;
	}



	public Location[] getCoords() {
		return coords;
	}
	
	@Override
	public String toString() {
		return "RoadSeg [roadID=" + roadID + ", length=" + length + ", nodeID1=" + nodeID1 + ", nodeID2=" + nodeID2
				+ ", coords=" + Arrays.toString(coords) + "]";
	}
	
	

}
