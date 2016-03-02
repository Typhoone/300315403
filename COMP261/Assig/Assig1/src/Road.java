import java.awt.Graphics;
import java.util.ArrayList;

public class Road {
	private int roadID;
	private int type;
	private String label;
	private String city;
	private int oneway;
	private int speed;
	private int roadclass;
	private int notforcar;
	private int notforpede;
	private int notforbicy;
	private ArrayList<RoadSeg> segments;
//	private RoadSeg segments;
	
	public Road(String Line) {
		String[] data = Line.split("\t");

		this.roadID = Integer.parseInt(data[0]);
		this.type = Integer.parseInt(data[1]);
		this.label = data[2];
		this.city = data[3];
		this.oneway = Integer.parseInt(data[4]);
		this.speed = Integer.parseInt(data[5]);
		this.roadclass = Integer.parseInt(data[6]);
		this.notforcar = Integer.parseInt(data[7]);
		this.notforpede = Integer.parseInt(data[8]);
		this.notforbicy = Integer.parseInt(data[9]);
		this.segments = new ArrayList<>();
	}
	
	public void draw(Graphics g){
		if(segments.size() > 0){
			for(RoadSeg seg: segments)
			g.drawPolyline(seg.getXPoints(), seg.getYPoints(), seg.getCoords().length);
		}
		
	}

	public int getRoadid() {
		return roadID;
	}

	public int getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}

	public String getCity() {
		return city;
	}

	public int getOneway() {
		return oneway;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRoadclass() {
		return roadclass;
	}

	public int getNotforcar() {
		return notforcar;
	}

	public int getNotforpede() {
		return notforpede;
	}

	public int getNotforbicy() {
		return notforbicy;
	}

	public ArrayList<RoadSeg> getSegments() {
		return segments;
	}

	public void addSegments(RoadSeg segments) {
		this.segments.add(segments);
	}
	
	
	
}
