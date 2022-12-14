package com.weedow.schemaorg.serializer.data;

import com.weedow.schemaorg.commons.model.JsonLdFieldTypes;
import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import lombok.Data;
import org.schema.model.datatype.Boolean;
import org.schema.model.datatype.Float;
import org.schema.model.datatype.Integer;
import org.schema.model.datatype.Number;
import org.schema.model.datatype.*;

@Data
@JsonLdTypeName("ObjectDataTypeExample")
public class ObjectDataTypeExample extends JsonLdNodeImpl {

    @JsonLdFieldTypes({Example.class, Example2.class})
    private Object obj;
    @JsonLdFieldTypes({Text.class, Boolean.class, URL.class})
    private Object bool;
    @JsonLdFieldTypes({Text.class, CssSelectorType.class})
    private Object cssSelectorType;
    @JsonLdFieldTypes({Date.class, DateTime.class})
    private Object date;
    @JsonLdFieldTypes({Date.class, Time.class, DateTime.class})
    private Object dateTime;
    @JsonLdFieldTypes({Text.class, Number.class, Integer.class, Float.class})
    private Object aFloat;
    @JsonLdFieldTypes({Text.class, Integer.class})
    private Object integer;
    @JsonLdFieldTypes({Number.class, Text.class})
    private Object number;
    @JsonLdFieldTypes({Text.class, PronounceableText.class})
    private Object pronounceableText;
    @JsonLdFieldTypes({Text.class, Example.class})
    private Object text;
    @JsonLdFieldTypes({Time.class, DateTime.class})
    private Object time;
    @JsonLdFieldTypes({URL.class, Text.class})
    private Object url;
    @JsonLdFieldTypes({XPathType.class, Text.class})
    private Object xPathType;
}
