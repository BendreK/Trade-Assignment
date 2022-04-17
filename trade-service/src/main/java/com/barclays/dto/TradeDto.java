package com.barclays.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TradeDto implements Serializable {
    private static final long serialVersionUID = -2173783526197972016L;
    private String trade_Id;
    private int version;
    private String counter_party_Id;
    private String book_Id;
    private LocalDate maturity_date;
    private LocalDate created_date;
    private Character expired;
}
