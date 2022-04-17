package com.barclays.model;

import com.barclays.util.BooleanToYNStringConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="Trade_Details")
@Getter
@Setter
@NoArgsConstructor
public class Trade implements Serializable {
    private static final long serialVersionUID = -2173783526197972016L;
    @Id
    @Column(name="trade_Id")
    private String trade_Id;
    @Column(name="version")
    private int version;
    @Column(name="counter_party_Id")
    private String counter_party_Id;
    @Column(name="book_Id")
    private String book_Id;
    @Column(name="maturity_date")
    private LocalDate maturity_date;
    @Column(name="created_date")
    private LocalDate created_date;
    @Convert(converter= BooleanToYNStringConverter.class)
    @Column(name="expired")
    private Character expired;


}
