package com.weedow.schemaorg.serializer.data;

import org.schema.model.Dataset;

public interface MyDataset extends Dataset {

    MyDataset getProvWasDerivedFrom();

    void setProvWasDerivedFrom(MyDataset provWasDerivedFrom);
}
