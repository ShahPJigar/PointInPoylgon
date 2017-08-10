package com.geocoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class Main {

	public static void main(String[] args) {

		File inFile = new File(args[0]);
		String stringAPIKey = args[1];
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(inFile));
			int count = 0;
			List polygon = null;
			while ((sCurrentLine = br.readLine()) != null) {
				if (count == 0) {
					polygon = parseCoordinates(sCurrentLine);					
					count++;
				} else {
					LatLng point = getCoordinates(sCurrentLine, stringAPIKey);
					boolean isInside = contains(point, polygon);
					if (isInside){
						System.out.println(sCurrentLine);
					}
				}
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static LatLng getCoordinates(String s, String stringAPIKey) {
		GeoApiContext context = new GeoApiContext().setApiKey(stringAPIKey);
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context, s).await();
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results[0].geometry.location;
	}

	private static List parseCoordinates(String s) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d{1,2})");
		Matcher matcher = pattern.matcher(s);
		List latlnglist = new ArrayList();
		while (matcher.find()) {
			double lat = Double.parseDouble(matcher.group(0));
			matcher.find();
			double lng = Double.parseDouble(matcher.group(0));
			LatLng latlng = new LatLng(lat, lng);
			latlnglist.add(latlng);
		}
		return latlnglist;
	}

	private static boolean contains(LatLng point, List path) {
		int crossings = 0;

		// for each edge
		for (int i = 0; i < path.size(); i++) {
			LatLng a = (LatLng) path.get(i);
			int j = i + 1;
			if (j >= path.size()) {
				j = 0;
			}
			LatLng b = (LatLng) path.get(j);
			if (rayCrossesSegment(point, a, b)) {
				crossings++;
			}
		}

		// odd number of crossings?
		return (crossings % 2 == 1);
	}

	private static boolean rayCrossesSegment(LatLng point, LatLng a, LatLng b) {
		double px = point.lng;
		double py = point.lat;
		double ax = a.lng;
		double ay = a.lat;
		double bx = b.lng;
		double by = b.lat;
		if (ay > by) {
			ax = b.lng;
			ay = b.lat;
			bx = a.lng;
			by = a.lat;
		}
		// alter longitude to cater for 180 degree crossings
		if (px < 0) {
			px += 360;
		}
		if (ax < 0) {
			ax += 360;
		}
		if (bx < 0) {
			bx += 360;
		}

		if (py == ay || py == by)
			py += 0.00000001;
		if ((py > by || py < ay) || (px > Math.max(ax, bx)))
			return false;
		if (px < Math.min(ax, bx))
			return true;

		double red = (ax != bx) ? ((by - ay) / (bx - ax)) : Double.MAX_VALUE;
		double blue = (ax != px) ? ((py - ay) / (px - ax)) : Double.MAX_VALUE;
		return (blue >= red);
	}
}
