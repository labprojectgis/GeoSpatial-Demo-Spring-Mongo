package com.freelance.geospatial.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.geospatial.demo.model.GeoModel;
import com.freelance.geospatial.demo.repository.GeoRepository;

@RestController
@RequestMapping(value="/geodata")
public class GeoController {
	    
	    private GeoRepository repository;
	    
	    @Autowired
	    GeoController(GeoRepository repository){
	    	this.repository=repository;
	    }
	    
	    @GetMapping(value="/search/nearby")
	    public final List<GeoModel> getNearbyLocations(
	    	@RequestParam("lat") String latitude,
	        @RequestParam("long") String longitude,
	        @RequestParam("d") double distance,
	        @RequestParam(value = "s", required = false) String subjects) {

	        List<GeoModel> result = this.repository.findByLocationNear(
	          new GeoJsonPoint(Double.valueOf(longitude), Double.valueOf(latitude)),
	          new Distance(distance, Metrics.KILOMETERS));
	        return result;
	    }
	    @GetMapping(value="/search/location")
	    public final List<GeoModel> getExactLocation(
	    	@RequestParam("lat") String latitude,
	        @RequestParam("long") String longitude,
	        @RequestParam(value = "s", required = false) String subjects) {

	        List<GeoModel> result = this.repository.findByLocation(
	          new GeoJsonPoint(Double.valueOf(latitude), Double.valueOf(longitude)));
	        return result;
	    }
	    @GetMapping(value="/search/withinCircle")
	    public final List<GeoModel> getLocationsWithinCircle(
	    	@RequestParam("x") String xCoOrdinate,
	        @RequestParam("y") String yCoOrdinate,
	        @RequestParam("radius") String radius,
	        @RequestParam(value = "s", required = false) String subjects) {

	        List<GeoModel> result = this.repository.findByLocationWithin(
	          new Circle(Double.valueOf(xCoOrdinate), Double.valueOf(yCoOrdinate), Double.valueOf(radius)));
	        return result;
	    }
	    @GetMapping(value="/search/withinBox")
	    public final List<GeoModel> getLocationsWithinBox(
	    	@RequestParam("upperX") String upperX,
	        @RequestParam("upperY") String upperY,
	    	@RequestParam("lowerX") String lowerX,
	        @RequestParam("lowerY") String lowerY,
	        @RequestParam(value = "s", required = false) String subjects) {

	        List<GeoModel> result = this.repository.findByLocationWithin(
	          new Box(new Point(Double.valueOf(upperX), Double.valueOf(upperY)), new Point(Double.valueOf(lowerX), Double.valueOf(lowerY))));
	        return result;
	    }
	    @GetMapping(value="/search/withinPolygon")
	    public final List<GeoModel> getLocationsWithinPolygon(
	    	@RequestParam("X1") String x1,
	        @RequestParam("Y1") String y1,
	    	@RequestParam("X2") String x2,
	        @RequestParam("Y2") String y2,
	        @RequestParam("X3") String x3,
	        @RequestParam("Y3") String y3,
	    	@RequestParam("X4") String x4,
	        @RequestParam("Y4") String y4,
	        @RequestParam(value = "s", required = false) String subjects) {

	        List<GeoModel> result = this.repository.findByLocationWithin(
	          new Polygon(
	        		  new Point(Double.valueOf(x1), Double.valueOf(y1)), 
	        		  new Point(Double.valueOf(x2), Double.valueOf(y2)),
	          		  new Point(Double.valueOf(x3), Double.valueOf(y3)), 
	          		  new Point(Double.valueOf(x4), Double.valueOf(y4))));
	        return result;
	    }
	    @GetMapping(value="/search/withinPolygonWithHoles")
	    public final List<GeoModel> getLocationsWithinPolygonWithHoles(
	    	@RequestParam("X1") String x1,
	        @RequestParam("Y1") String y1,
	    	@RequestParam("X2") String x2,
	        @RequestParam("Y2") String y2,
	        @RequestParam("X3") String x3,
	        @RequestParam("Y3") String y3,
	    	@RequestParam("X4") String x4,
	        @RequestParam("Y4") String y4,
	        @RequestParam("holeX") String holeX,
	        @RequestParam("holeY") String holeY,
	        @RequestParam(value = "s", required = false) String subjects) {

	        List<GeoModel> result = this.repository.findByLocationWithin(
	          new Polygon(
	        		  new Point(Double.valueOf(x1), Double.valueOf(y1)), 
	        		  new Point(Double.valueOf(x2), Double.valueOf(y2)),
	          		  new Point(Double.valueOf(x3), Double.valueOf(y3)), 
	          		  new Point(Double.valueOf(x4), Double.valueOf(y4)),
	          		  new Point(Double.valueOf(holeX), Double.valueOf(holeY))));
	        return result;
	    }
	    @GetMapping(value="/search/IntersectingLocations")
	    public final List<GeoModel> getIntersectingLocations(
	    	@RequestParam("X1") String x1,
	        @RequestParam("Y1") String y1,
	    	@RequestParam("X2") String x2,
	        @RequestParam("Y2") String y2,
	        @RequestParam("X3") String x3,
	        @RequestParam("Y3") String y3,
	    	@RequestParam("X4") String x4,
	        @RequestParam("Y4") String y4,
	        @RequestParam(value = "s", required = false) String subjects) {

	        List<GeoModel> result = this.repository.findByLocation(
	          new Polygon(
	        		  new Point(Double.valueOf(x1), Double.valueOf(y1)), 
	        		  new Point(Double.valueOf(x2), Double.valueOf(y2)),
	          		  new Point(Double.valueOf(x3), Double.valueOf(y3)), 
	          		  new Point(Double.valueOf(x4), Double.valueOf(y4))));
	        return result;
	    }
	    @PostMapping(value="addPoint")
	    public final void addLocations(
	      @RequestParam(value="s",required=false) String sid,
	      @RequestBody List<GeoModel> entries) {
	    	System.out.print(entries.get(0).getLocation().toString());
	      List<GeoModel> entities = new ArrayList<>();
	      for (GeoModel point : entries) {
	        final GeoJsonPoint locationPoint = new GeoJsonPoint(
	                Double.valueOf(point.getLocation().getX()),
	                Double.valueOf(point.getLocation().getY()));

	        entities.add(new GeoModel(point.getSubject(), locationPoint));
	      }

	      for (GeoModel entity : entities) {
	    	  this.repository.save(entity);
	      }
	    }
}
