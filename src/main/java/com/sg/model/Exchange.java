package com.sg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Exchange")
public class Exchange {

    @Id
    String symbol;

    @JsonProperty("name")
    String name;

    @JsonProperty("latestPrice")
    Double latestPrice;

    @JsonProperty("previousClose")
    Double previousClose;
}
