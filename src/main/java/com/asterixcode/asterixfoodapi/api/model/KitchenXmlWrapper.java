package com.asterixcode.asterixfoodapi.api.model;


import com.asterixcode.asterixfoodapi.domain.model.Kitchen;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JacksonXmlRootElement(localName = "kitchens")
@Data
public class KitchenXmlWrapper {

    @JacksonXmlProperty(localName = "kitchen")
    @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull //Lombok annotation for creating a constructor with this as parameter
    private List<Kitchen> kitchens;
}
