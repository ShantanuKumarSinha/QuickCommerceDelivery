package com.shann.quickcommerce.strategy;

import com.shann.quickcommerce.entities.Partner;
import com.shann.quickcommerce.entities.PartnerTaskMapping;
import com.shann.quickcommerce.entities.Task;

import java.util.List;

/** Interface for location matching strategy. */
public interface LocationMatchingStrategy {

  /** * Matches a partner with a task based on the closest location.
   * @param partners the list of partners to match
   * @param tasks
   * @return
   */
  public List<PartnerTaskMapping> matchPartnerTask(List<Partner> partners, List<Task> tasks);
}
