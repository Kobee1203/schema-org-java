package com.weedow.schemaorg.serializer.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import org.schema.model.impl.DatasetImpl;

@JsonLdTypeName("MyDataset")
public class MyDatasetImpl extends DatasetImpl implements MyDataset {

    @JsonProperty("prov:wasDerivedFrom")
    private MyDataset provWasDerivedFrom;

    public MyDataset getProvWasDerivedFrom() {
        return provWasDerivedFrom;
    }

    public void setProvWasDerivedFrom(MyDataset provWasDerivedFrom) {
        this.provWasDerivedFrom = provWasDerivedFrom;
    }
}
