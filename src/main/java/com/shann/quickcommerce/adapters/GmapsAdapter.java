package com.shann.quickcommerce.adapters;


import com.shann.quickcommerce.entities.Location;
import com.shann.quickcommerce.libraries.gmaps.GLocation;
import com.shann.quickcommerce.libraries.gmaps.GMaps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GmapsAdapter implements MapsAdapter {
    @Override
    public List<Location> buildRoute(List<Location> locations) {
        List<GLocation> gLocations = locations.stream().map(location -> {
            GLocation gLocation = new GLocation();
            gLocation.setLat(location.getLatitude());
            gLocation.setLng(location.getLongitude());
            return gLocation;
        }).collect(Collectors.toList());
        GMaps gmaps = new GMaps();
        List<GLocation> optimizedLocations = gmaps.getOptimizedRoute(gLocations);
        return optimizedLocations.stream().map(optimizedLocation -> new Location(optimizedLocation.getLat(), optimizedLocation.getLng())).toList();
    }
}
