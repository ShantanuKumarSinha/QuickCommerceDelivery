package com.shann.quickcommerce.services.impl;

import com.shann.quickcommerce.entities.PartnerTaskMapping;
import com.shann.quickcommerce.repositories.PartnerRepository;
import com.shann.quickcommerce.repositories.TaskRepository;
import com.shann.quickcommerce.services.MatchPartnerTaskService;
import com.shann.quickcommerce.strategy.LocationMatchingStrategy;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * MatchPartnerTaskServiceImpl is a service implementation for matching partners with tasks. It
 * contains the business logic for the matching process.
 */
@Service
public class MatchPartnerTaskServiceImpl implements MatchPartnerTaskService {

  private LocationMatchingStrategy locationMatchingStrategy;
  private PartnerRepository partnerRepository;
  private TaskRepository taskRepository;

  /**
   * Constructor for MatchPartnerTaskServiceImpl.
   *
   * @param locationMatchingStrategy the strategy used for matching partners with tasks based on
   *     location
   * @param partnerRepository the repository for accessing partner data
   * @param taskRepository the repository for accessing task data
   */
  public MatchPartnerTaskServiceImpl(
      LocationMatchingStrategy locationMatchingStrategy,
      PartnerRepository partnerRepository,
      TaskRepository taskRepository) {
    this.locationMatchingStrategy = locationMatchingStrategy;
    this.partnerRepository = partnerRepository;
    this.taskRepository = taskRepository;
  }

  /**
   * Matches partners with tasks based on the provided partner IDs and task IDs.
   *
   * @param partnerIds the list of partner IDs to match
   * @param taskIds the list of task IDs to match
   * @return a list of PartnerTaskMapping objects representing the matched partners and tasks
   */
  @Override
  public List<PartnerTaskMapping> matchPartnerTask(List<Long> partnerIds, List<Long> taskIds) {
    var partners = partnerRepository.findPartnersByIdIn(partnerIds);
    var tasks = taskRepository.findTasksByIdIn(taskIds);
    return locationMatchingStrategy.matchPartnerTask(partners, tasks);
  }
}
