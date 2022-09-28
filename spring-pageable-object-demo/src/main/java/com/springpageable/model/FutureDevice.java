package com.springpageable.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class FutureDevice extends Auditable implements Serializable {

  private static final long serialVersionUID = 2613900115897578605L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String serialNumber;

  private String productId;

  private Long customerId;
}
