package com.bbf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="QUOTE_ENTITY", indexes = {
        @Index(columnList = "TIME", name = "time_idx")})
@Cacheable
@Getter
@Setter
@NoArgsConstructor
public class QuoteEntity implements Serializable {
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIME")
    private long time;
    @Column(name = "BID")
    private int bid;
    @Column(name = "ASK")
    private int ask;
    @Column(name = "BID_VOLUME")
    private int bidVolume;
    @Column(name = "ASK_VOLUME")
    private int askVolume;


}
