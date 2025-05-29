package com.shann.quickcommerce.entities;

import com.shann.quickcommerce.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "delivery_tasks")
@Data
public class Task extends BaseModel {
  private long customerId;
  @Enumerated(EnumType.ORDINAL)
  private TaskStatus taskStatus;

  @Embedded private Location pickupLocation;
}
