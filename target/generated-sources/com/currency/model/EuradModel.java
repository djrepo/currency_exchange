
package com.currency.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "_id",
    "entryID",
    "key",
    "quoteType",
    "time",
    "bid",
    "ask",
    "bidVolume",
    "askVolume",
    "depth",
    "positionNumber",
    "compID",
    "validTime"
})
@Generated("jsonschema2pojo")
public class EuradModel {

    @JsonProperty("_id")
    private Id id;
    @JsonProperty("entryID")
    private String entryID;
    @JsonProperty("key")
    private Key key;
    @JsonProperty("quoteType")
    private QuoteType quoteType;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("bid")
    private Bid bid;
    @JsonProperty("ask")
    private Ask ask;
    @JsonProperty("bidVolume")
    private BidVolume bidVolume;
    @JsonProperty("askVolume")
    private AskVolume askVolume;
    @JsonProperty("depth")
    private Depth depth;
    @JsonProperty("positionNumber")
    private PositionNumber positionNumber;
    @JsonProperty("compID")
    private String compID;
    @JsonProperty("validTime")
    private ValidTime validTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("_id")
    public Id getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(Id id) {
        this.id = id;
    }

    public EuradModel withId(Id id) {
        this.id = id;
        return this;
    }

    @JsonProperty("entryID")
    public String getEntryID() {
        return entryID;
    }

    @JsonProperty("entryID")
    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    public EuradModel withEntryID(String entryID) {
        this.entryID = entryID;
        return this;
    }

    @JsonProperty("key")
    public Key getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(Key key) {
        this.key = key;
    }

    public EuradModel withKey(Key key) {
        this.key = key;
        return this;
    }

    @JsonProperty("quoteType")
    public QuoteType getQuoteType() {
        return quoteType;
    }

    @JsonProperty("quoteType")
    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

    public EuradModel withQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
        return this;
    }

    @JsonProperty("time")
    public Time getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Time time) {
        this.time = time;
    }

    public EuradModel withTime(Time time) {
        this.time = time;
        return this;
    }

    @JsonProperty("bid")
    public Bid getBid() {
        return bid;
    }

    @JsonProperty("bid")
    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public EuradModel withBid(Bid bid) {
        this.bid = bid;
        return this;
    }

    @JsonProperty("ask")
    public Ask getAsk() {
        return ask;
    }

    @JsonProperty("ask")
    public void setAsk(Ask ask) {
        this.ask = ask;
    }

    public EuradModel withAsk(Ask ask) {
        this.ask = ask;
        return this;
    }

    @JsonProperty("bidVolume")
    public BidVolume getBidVolume() {
        return bidVolume;
    }

    @JsonProperty("bidVolume")
    public void setBidVolume(BidVolume bidVolume) {
        this.bidVolume = bidVolume;
    }

    public EuradModel withBidVolume(BidVolume bidVolume) {
        this.bidVolume = bidVolume;
        return this;
    }

    @JsonProperty("askVolume")
    public AskVolume getAskVolume() {
        return askVolume;
    }

    @JsonProperty("askVolume")
    public void setAskVolume(AskVolume askVolume) {
        this.askVolume = askVolume;
    }

    public EuradModel withAskVolume(AskVolume askVolume) {
        this.askVolume = askVolume;
        return this;
    }

    @JsonProperty("depth")
    public Depth getDepth() {
        return depth;
    }

    @JsonProperty("depth")
    public void setDepth(Depth depth) {
        this.depth = depth;
    }

    public EuradModel withDepth(Depth depth) {
        this.depth = depth;
        return this;
    }

    @JsonProperty("positionNumber")
    public PositionNumber getPositionNumber() {
        return positionNumber;
    }

    @JsonProperty("positionNumber")
    public void setPositionNumber(PositionNumber positionNumber) {
        this.positionNumber = positionNumber;
    }

    public EuradModel withPositionNumber(PositionNumber positionNumber) {
        this.positionNumber = positionNumber;
        return this;
    }

    @JsonProperty("compID")
    public String getCompID() {
        return compID;
    }

    @JsonProperty("compID")
    public void setCompID(String compID) {
        this.compID = compID;
    }

    public EuradModel withCompID(String compID) {
        this.compID = compID;
        return this;
    }

    @JsonProperty("validTime")
    public ValidTime getValidTime() {
        return validTime;
    }

    @JsonProperty("validTime")
    public void setValidTime(ValidTime validTime) {
        this.validTime = validTime;
    }

    public EuradModel withValidTime(ValidTime validTime) {
        this.validTime = validTime;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public EuradModel withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EuradModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("entryID");
        sb.append('=');
        sb.append(((this.entryID == null)?"<null>":this.entryID));
        sb.append(',');
        sb.append("key");
        sb.append('=');
        sb.append(((this.key == null)?"<null>":this.key));
        sb.append(',');
        sb.append("quoteType");
        sb.append('=');
        sb.append(((this.quoteType == null)?"<null>":this.quoteType));
        sb.append(',');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null)?"<null>":this.time));
        sb.append(',');
        sb.append("bid");
        sb.append('=');
        sb.append(((this.bid == null)?"<null>":this.bid));
        sb.append(',');
        sb.append("ask");
        sb.append('=');
        sb.append(((this.ask == null)?"<null>":this.ask));
        sb.append(',');
        sb.append("bidVolume");
        sb.append('=');
        sb.append(((this.bidVolume == null)?"<null>":this.bidVolume));
        sb.append(',');
        sb.append("askVolume");
        sb.append('=');
        sb.append(((this.askVolume == null)?"<null>":this.askVolume));
        sb.append(',');
        sb.append("depth");
        sb.append('=');
        sb.append(((this.depth == null)?"<null>":this.depth));
        sb.append(',');
        sb.append("positionNumber");
        sb.append('=');
        sb.append(((this.positionNumber == null)?"<null>":this.positionNumber));
        sb.append(',');
        sb.append("compID");
        sb.append('=');
        sb.append(((this.compID == null)?"<null>":this.compID));
        sb.append(',');
        sb.append("validTime");
        sb.append('=');
        sb.append(((this.validTime == null)?"<null>":this.validTime));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.positionNumber == null)? 0 :this.positionNumber.hashCode()));
        result = ((result* 31)+((this.askVolume == null)? 0 :this.askVolume.hashCode()));
        result = ((result* 31)+((this.quoteType == null)? 0 :this.quoteType.hashCode()));
        result = ((result* 31)+((this.entryID == null)? 0 :this.entryID.hashCode()));
        result = ((result* 31)+((this.depth == null)? 0 :this.depth.hashCode()));
        result = ((result* 31)+((this.compID == null)? 0 :this.compID.hashCode()));
        result = ((result* 31)+((this.ask == null)? 0 :this.ask.hashCode()));
        result = ((result* 31)+((this.validTime == null)? 0 :this.validTime.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.time == null)? 0 :this.time.hashCode()));
        result = ((result* 31)+((this.bidVolume == null)? 0 :this.bidVolume.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.bid == null)? 0 :this.bid.hashCode()));
        result = ((result* 31)+((this.key == null)? 0 :this.key.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EuradModel) == false) {
            return false;
        }
        EuradModel rhs = ((EuradModel) other);
        return (((((((((((((((this.positionNumber == rhs.positionNumber)||((this.positionNumber!= null)&&this.positionNumber.equals(rhs.positionNumber)))&&((this.askVolume == rhs.askVolume)||((this.askVolume!= null)&&this.askVolume.equals(rhs.askVolume))))&&((this.quoteType == rhs.quoteType)||((this.quoteType!= null)&&this.quoteType.equals(rhs.quoteType))))&&((this.entryID == rhs.entryID)||((this.entryID!= null)&&this.entryID.equals(rhs.entryID))))&&((this.depth == rhs.depth)||((this.depth!= null)&&this.depth.equals(rhs.depth))))&&((this.compID == rhs.compID)||((this.compID!= null)&&this.compID.equals(rhs.compID))))&&((this.ask == rhs.ask)||((this.ask!= null)&&this.ask.equals(rhs.ask))))&&((this.validTime == rhs.validTime)||((this.validTime!= null)&&this.validTime.equals(rhs.validTime))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.time == rhs.time)||((this.time!= null)&&this.time.equals(rhs.time))))&&((this.bidVolume == rhs.bidVolume)||((this.bidVolume!= null)&&this.bidVolume.equals(rhs.bidVolume))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.bid == rhs.bid)||((this.bid!= null)&&this.bid.equals(rhs.bid))))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))));
    }

}
