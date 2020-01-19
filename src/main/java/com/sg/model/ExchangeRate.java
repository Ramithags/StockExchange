package com.sg.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("rate")
    @Expose
    private Double rate;

    @SerializedName("timestamp")
    @Expose
    private Long timestamp;

    @SerializedName("isDerived")
    @Expose
    private Boolean isDerived;
}
