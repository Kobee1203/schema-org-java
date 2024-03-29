# Schema.org Java Serializer

[Home](https://github.com/Kobee1203/schema-org-java)

---

The Serializer module supports serialization and deserialization of Schema.org objects in JSON-LD format.

### <a name="Maven-Configuration">Maven Configuration</a>

Add the serializer module and the [Maven Plugin](../maven-plugin/README.md) to serialize/deserialize Schema.org objects generated by the [Maven Plugin](../maven-plugin/README.md).

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="..." xmlns:xsi="..." xsi:schemaLocation="...">

  <dependencies>
    <dependency>
      <groupId>com.weedow</groupId>
      <artifactId>schema-org-serializer</artifactId>
      <version>${schema-org-serializer.version}</version>
    </dependency>
  </dependencies>

  <build>
    <plugin>
      <groupId>com.weedow</groupId>
      <artifactId>schema-org-generator-maven-plugin</artifactId>
      <version>${schema-org-generator-maven-plugin.version}</version>
      <executions>
        <execution>
          <goals>
            <goal>generate</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </build>
</project>
```

### <a name="Serialization">Serialization</a>

```java
import com.weedow.schemaorg.serializer.serialization.JsonLdSerializer;
import com.weedow.schemaorg.serializer.serialization.JsonLdSerializerImpl;
import org.schema.model.Thing;
import org.schema.model.impl.ThingImpl;

final class SerializerUtils {

  private SerializerUtils() {
  }

  public static String serialize() {
    final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl();

    Thing thing = new ThingImpl();
    thing.setId("my_id");
    thing.addName(Text.of("My Thing"));
    thing.addDescription(Text.of("This is my thing."));
    thing.addUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));
    thing.addAlternateName(Text.of("My Part"));
    thing.addAlternateName(Text.of("My Object"));

    String result = null;
    try {
      result = jsonLdSerializer.serialize(thing);
    } catch (JsonLdException e) {
      // Errors related to JSON-LD serializer
    }
    return result;
  }
}
```

This example will give the following result:

```json
{"@context":"https://schema.org","@id":"my_id","@type":"Thing","alternateName":["My Part","My Object"],"description":"This is my thing.","name":"My Thing","url":"https://github.com/Kobee1203/schema-org-java"}
```

There is another constructor that receives options as parameters.\
Here is an example to serialize the Schema.org object with a pretty printed result:

```java
import com.weedow.schemaorg.serializer.serialization.JsonLdSerializer;
import com.weedow.schemaorg.serializer.serialization.JsonLdSerializerImpl;
import org.schema.model.Thing;
import org.schema.model.impl.ThingImpl;

final class SerializerUtils {

  private SerializerUtils() {
  }

  public static String serialize() {
    JsonLdSerializerOptions options = JsonLdSerializerOptions.builder()
            .prettyPrint(true)
            .build();
    final JsonLdSerializer jsonLdSerializer = new JsonLdSerializerImpl(options);

    Thing thing = new ThingImpl();
    thing.setId("my_id");
    thing.addName(Text.of("My Thing"));
    thing.addDescription(Text.of("This is my thing."));
    thing.addUrl(URL.of(new java.net.URL("https://github.com/Kobee1203/schema-org-java")));
    thing.addAlternateName(Text.of("My Part"));
    thing.addAlternateName(Text.of("My Object"));

    String result = null;
    try {
      result = jsonLdSerializer.serialize(thing);
    } catch (JsonLdException e) {
      // Errors related to JSON-LD serializer
    }
    return result;
  }
}
```

This example will give the following result:

```json
{
  "@context" : "https://schema.org",
  "@id" : "my_id",
  "@type" : "Thing",
  "alternateName" : [ "My Part", "My Object" ],
  "description" : "This is my thing.",
  "name" : "My Thing",
  "url" : "https://github.com/Kobee1203/schema-org-java"
}
```

### <a name="Deserialization">Deserialization</a>

```java
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDeserializer;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDeserializerImpl;
import com.weedow.schemaorg.serializer.JsonLdException;

final class DeserializerUtils {

  private DeserializerUtils() {
  }

  /**
   * Deserializes the JSON-LD string into schema.org object.
   *
   * @param json JSON-LD String to deserialize
   * @return a JsonLdNode object resulting from the given deserialized String
   * @throws JsonLdException if the String cannot be deserialized
   */
  public static <T extends JsonLdNode> T deserialize(String json) throws JsonLdException {
    JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
    return jsonLdDeserializer.deserialize(json);
  }

  /**
   * Deserializes the JSON-LD string into schema.org objects.<br>
   * The method supports the deserialization of a JSON string representing a single object.
   *
   * @param json JSON-LD String to deserialize
   * @return a List of JsonLdNode objects resulting from the given deserialized String
   * @throws JsonLdException if the String cannot be deserialized
   */
  public static <T extends JsonLdNode> List<T> deserializeList(String json) throws JsonLdException {
    JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl();
    return jsonLdDeserializer.deserializeList(json);
  }
}
```

There is another constructor that receives a Map other types allowed by the deserializer.
- Map key: `@type` value
- Map value: Class

Here is an example to deserialize an object that is not generated by the Schema.org generation:

Let's imagine the following JSON-LD content:
```json
{
  "@context" : "https://schema.org",
  "@type" : "Example",
  "aFloat" : 12345.67,
  "bool" : true,
  "cssSelectorType" : ".css-selector-type",
  "date" : "2022-03-12",
  "dateTime" : "2022-03-12T10:36:30",
  "integer" : 12345,
  "number" : 12345.67,
  "pronounceableText" : "This is my thing.",
  "text" : "My Thing",
  "time" : "10:36:30",
  "url" : "https://github.com/Kobee1203/schema-org-java",
  "xPathType" : "/xpath/example/title"
}
```

The previous content is passed to the deserialization method:
```java
import com.weedow.schemaorg.serializer.deserialization.JsonLdDeserializer;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDeserializerImpl;
import com.weedow.schemaorg.serializer.JsonLdException;
import org.myproject.data.Example;

final class DeserializerUtils {

  private DeserializerUtils() {
  }

  public static Example deserializeExample(String json) throws JsonLdException {
    JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl(Map.of("Example", Example.class));
    return jsonLdDeserializer.deserialize(json);
  }
}
```

If we want to add custom types, there is a constructor to get all classes from a package:
```java
import com.weedow.schemaorg.commons.model.JsonLdNode;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDeserializer;
import com.weedow.schemaorg.serializer.deserialization.JsonLdDeserializerImpl;
import com.weedow.schemaorg.serializer.JsonLdException;

final class DeserializerUtils {

  private DeserializerUtils() {
  }

  public static <T extends JsonLdNode> T deserializeExample(String json) throws JsonLdException {
    JsonLdDeserializer jsonLdDeserializer = new JsonLdDeserializerImpl("org.myproject.data");
    return jsonLdDeserializer.deserialize(json);
  }
}
```

---

[Home](https://github.com/Kobee1203/schema-org-java)