package com.shann.quickcommerce.entities;

import com.shann.quickcommerce.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "delivery_pickup_tasks")
@Data
public class PickUpTask extends BaseModel {
  private long customerId;
  @Enumerated(EnumType.ORDINAL)
  private TaskStatus taskStatus;

  @Embedded private Location pickupLocation;

}
