package com.weedow.schemaorg.serializer.data;

import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import lombok.Data;
import org.schema.model.datatype.Text;

@Data
@JsonLdTypeName("InvalidData")
public class InvalidData extends JsonLdNodeImpl {

    private Text text;

    public Text getText() {
        throw new RuntimeException("Unexpected error");
    }
}
