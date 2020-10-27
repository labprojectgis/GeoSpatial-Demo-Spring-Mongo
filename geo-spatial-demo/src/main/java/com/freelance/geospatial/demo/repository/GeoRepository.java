package com.freelance.geospatial.demo.repository;
import com.freelance.geospatial.demo.model.GeoModel;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface GeoRepository  extends MongoRepository<GeoModel, String> {

	  List<GeoModel> findByLocationNear( GeoJsonPoint p, Distance d);
	  List<GeoModel> findByLocation(GeoJsonPoint p);
	  List<GeoModel> findByLocationWithin(Circle circle);
	  List<GeoModel> findByLocationWithin(Box box);
	  List<GeoModel> findByLocationWithin(Polygon polygon);
	  List<GeoModel> findByLocationWithin(Polygon polygon,List<Point> holes);	  
	  @Query("{'$geoIntersects','$geometry': ?0 } }")
	  List<GeoModel> findByLocation(Polygon polygon);
}
