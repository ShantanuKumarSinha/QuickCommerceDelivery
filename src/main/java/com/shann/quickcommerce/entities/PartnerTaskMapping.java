package com.shann.quickcommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "delivery_partner_task_mapping")
@Data
public class PartnerTaskMapping extends BaseModel {

  @ManyToOne
  @JoinColumn(name = "partner_id", referencedColumnName = "id", nullable = false)
  private Partner partner;

  @ManyToOne
  @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false)
  private PickUpTask task;
}
