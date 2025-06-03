package com.shann.quickcommerce.strategy.impl;

import com.shann.quickcommerce.entities.Location;
import com.shann.quickcommerce.entities.Partner;
import com.shann.quickcommerce.entities.PartnerTaskMapping;
import com.shann.quickcommerce.entities.PickUpTask;
import com.shann.quickcommerce.enums.PartnerStatus;
import com.shann.quickcommerce.strategy.LocationMatchingStrategy;
import com.shann.quickcommerce.utils.DistanceUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
/**
 * ClosestPartnerLocationMatchingStrategy is a strategy for matching partners with tasks based on
 * the closest location. It implements the LocationMatchingStrategy interface.
 */
public class ClosestPartnerLocationMatchingStrategy implements LocationMatchingStrategy {

  /**
   * Matches partners with tasks based on the closest location.
   *
   * @param partners the list of partners to match
   * @param tasks
   * @return
   */
  @Override
  public List<PartnerTaskMapping> matchPartnerTask(List<Partner> partners, List<PickUpTask> tasks) {
    List<PartnerTaskMapping> partnerTaskMappings = new ArrayList<>();
    tasks.forEach(
        task -> {
          List<Partner> availablePartner =
              partners.stream()
                  .filter(
                      partner ->
                          // check partner status is AVAILABLE
                          // !partner.getPartnerStatus().equals(PartnerStatus.AVAILABLE) &
                          partnerTaskMappings.stream()
                              .noneMatch(
                                  partnerTaskMapping ->
                                      partnerTaskMapping.getPartner().getId() == partner.getId()))
                  .toList();
          Partner matchedPartner = match(availablePartner, task.getPickupLocation());
          if (matchedPartner != null) {
            PartnerTaskMapping partnerTaskMapping = new PartnerTaskMapping();
            partnerTaskMapping.setPartner(matchedPartner);
            partnerTaskMapping.setTask(task);
            partnerTaskMappings.add(partnerTaskMapping);
          }
        });
    return partnerTaskMappings;
  }

  public Partner match(List<Partner> partners, Location taskLocation) {
    var closestPartnerOptional =
        partners.stream()
            .filter(p -> p.getPartnerStatus().equals(PartnerStatus.AVAILABLE))
            .min(
                (p1, p2) -> {
                  var distance1 =
                      DistanceUtils.calculateDistance(p1.getCurrentLocation(), taskLocation);
                  var distance2 =
                      DistanceUtils.calculateDistance(p2.getCurrentLocation(), taskLocation);
                  return Double.compare(distance1, distance2);
                });
    if (closestPartnerOptional.isPresent()) {
      var closestPartner = closestPartnerOptional.get();
      closestPartner.setPartnerStatus(PartnerStatus.OCCUPIED);
      return closestPartner;
    }
    return null;
  }
}
