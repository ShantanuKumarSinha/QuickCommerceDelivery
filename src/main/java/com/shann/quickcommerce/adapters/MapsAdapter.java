package com.shann.quickcommerce.adapters;

import com.shann.quickcommerce.entities.Location;

import java.util.List;

public interface MapsAdapter {

    public List<Location> buildRoute(List<Location> locations);
}
