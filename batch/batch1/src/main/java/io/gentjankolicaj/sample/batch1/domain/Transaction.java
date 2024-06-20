package io.gentjankolicaj.sample.batch1.domain;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

  private String account;
  private Date timeStamp;
  private BigDecimal bigDecimal;

}
