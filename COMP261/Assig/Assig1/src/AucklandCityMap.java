import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;

public class AucklandCityMap extends GUI{
	
	
	public static Location origin = Location.newFromLatLon(-36.847622, 174.763444);
	public static int scale = 10;
	public static double xOffset = 0;
	public static double yOffset = 0;

	
	private HashMap<Integer, Road> roads = new HashMap<>();
	private HashMap<Integer, Intersection> intersections = new HashMap();
	
	
	public AucklandCityMap() {
//		origin = new Location(-window.getWidth()/2, -window.getHeight()/2);
	}


	@Override
	protected void redraw(Graphics g) {
		xOffset = getDrawingAreaDimension().getWidth()/2.0;
		yOffset = getDrawingAreaDimension().getHeight()/2.0;
		getTextOutputArea().setText("REDRAW");
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		for(Intersection I: intersections.values()){
			I.draw(g);
		}
		
		g.setColor(Color.gray);
		
		for(Road R: roads.values()){
			R.draw(g);
		}
		
	}

	@Override
	protected void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onMove(Move m) {
		getTextOutputArea().setText(m.name());
		switch (m) {
		case ZOOM_IN:
			scale+=4;
			break;
		case ZOOM_OUT:
			scale-=4;
			break;
		case NORTH:
			origin = origin.moveBy(0, 1);
			break;
		case SOUTH:
			origin = origin.moveBy(0, -1);
			break;
		case EAST:
			origin = origin.moveBy(1, 0);
			break;
		case WEST:
			origin = origin.moveBy(-1, 0);
			break;

		default:
			break;
		}
		
	}

	@Override
	protected void onLoad(File nodes, File roads, File segments, File polygons) {
		intersections.clear();
		this.roads.clear();
		for (String node : fileToStringArray(nodes)) {
			Intersection temp = new Intersection(node);
			intersections.put(temp.nodeID, temp);
		}
		for(String road: fileToStringArray(roads)){
			if(!Character.isDigit(road.toCharArray()[0])){
				continue;
			}
			Road temp = new Road(road);
			this.roads.put(temp.getRoadid(), temp);
		}
		for(String seg: fileToStringArray(segments)){
			if(!Character.isDigit(seg.toCharArray()[0])){
				continue;
			}
			RoadSeg temp = new RoadSeg(seg);
			this.roads.get(temp.getRoadID()).addSegments(temp);
		}
		
		
	}
	
	private String[] fileToStringArray(File file){
		ArrayList<String> lines = new ArrayList<>();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR File not found: " + file.getName());
		}catch (IOException e){
			System.out.println("ERROR IOException: " + file.getName() + "\nPREV: " + line);
		}
		
		
		return lines.toArray(new String[lines.size()]);
	}
	
	public static void main(String[] args) {
		new AucklandCityMap();

	}

}
