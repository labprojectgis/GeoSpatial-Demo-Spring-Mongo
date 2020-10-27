package com.freelance.geospatial.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locations")
public class GeoModel {
	@Id
  private String id;
  private String subject;
  @GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
  private GeoJsonPoint location;

  public GeoModel(String subject,GeoJsonPoint location) {
    this.setSubject(subject);
    this.location = (location);
  }

public GeoJsonPoint getLocation() {
	return location;
}

public void setLocation(GeoJsonPoint location) {
	this.location = location;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}
}