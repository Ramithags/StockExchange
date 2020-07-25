package com.sg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "Exchange")
public class Exchange {

    @Id
    String name;

    @JsonProperty("latestPrice")
    Double latestPrice;

    @JsonProperty("previousClose")
    Double previousClose;
}
