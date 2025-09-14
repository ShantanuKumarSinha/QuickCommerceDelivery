package com.shann.quickcommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "batched_tasks")
@Data
public class BatchedTask extends BaseModel {

  @OneToMany(fetch = FetchType.LAZY)
  private List<DropTask> tasks;

  private Date batchedAt;
}
