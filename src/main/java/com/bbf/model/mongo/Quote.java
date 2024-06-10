package com.bbf.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Quote {
        @JsonIgnore
        private Object _id;
        private long entryID;
        private long key;
        private int quoteType;
        private long time;
        private int bid;
        private int ask;
        private int bidVolume;
        private int askVolume;
        private int depth;
        private int positionNumber;
        private String compID;
        private long validTime;
}
