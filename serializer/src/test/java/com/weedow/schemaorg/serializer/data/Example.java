package com.weedow.schemaorg.serializer.data;

import com.weedow.schemaorg.commons.model.JsonLdNodeImpl;
import com.weedow.schemaorg.commons.model.JsonLdTypeName;
import lombok.Data;
import org.schema.model.datatype.Boolean;
import org.schema.model.datatype.Float;
import org.schema.model.datatype.Integer;
import org.schema.model.datatype.Number;
import org.schema.model.datatype.*;

@Data
@JsonLdTypeName("Example")
public class Example extends JsonLdNodeImpl {

    private Boolean bool;
    private CssSelectorType cssSelectorType;
    private Date date;
    private DateTime dateTime;
    private Float aFloat;
    private Integer integer;
    private Number number;
    private PronounceableText pronounceableText;
    private Text text;
    private Time time;
    private URL url;
    private XPathType xPathType;
}
