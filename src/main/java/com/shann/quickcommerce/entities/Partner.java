package com.shann.quickcommerce.entities;

import com.shann.quickcommerce.enums.PartnerStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "delivery_partners")
@Data
public class Partner extends BaseModel {
  private String partnerName;

  @Enumerated(EnumType.ORDINAL)
  private PartnerStatus partnerStatus;

  @Embedded private Location currentLocation;
}
