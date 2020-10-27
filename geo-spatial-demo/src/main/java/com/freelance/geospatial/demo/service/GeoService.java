package com.freelance.geospatial.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import com.freelance.geospatial.demo.repository.GeoRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

@Service
public class GeoService {
	GeoRepository geoRepository;
	public void givenNearbyLocation_whenSearchNearby_thenFound() {
//		MongoCollection collection = mongoOps.getCollection("places");
//	    Point currentLoc = new Point(new Position(-0.126821, 51.495885));
//	 
//	    FindIterable<Document> result = collection.find(
//	      Filters.near("location", currentLoc, 1000.0, 10.0));
//	    
//	    System.out.println("Collection size: " + result.toString());
	 
	}
}
