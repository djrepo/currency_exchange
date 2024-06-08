package com.shorturl.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Quote {

        private LocalDateTime time;
        private int bid;
        private int ask;
        private int bidVolume;
        private int askVolume;
}
