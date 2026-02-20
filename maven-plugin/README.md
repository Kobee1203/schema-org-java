# Schema.org Java Generator Maven Plugin

[Home](https://github.com/Kobee1203/schema-org-java)

---

Add the plugin to your `project->build->plugins` section (default phase is `generate-sources` phase).

#### Generate all Schema.org entities:

```xml
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
```

#### Generate specific Schema.org entities:

```xml
<plugin>
  <groupId>com.weedow</groupId>
  <artifactId>schema-org-generator-maven-plugin</artifactId>
  <version>${schema-org-generator-maven-plugin.version}</version>
  <executions>
    <execution>
      <goals>
        <goal>generate</goal>
      </goals>
      <configuration>
        <models>Hotel</models>
      </configuration>
    </execution>
  </executions>
</plugin>
```

Note: _The previous example will generate all classes required by the [Hotel](https://schema.org/Hotel) type._

#### Plugin Configuration parameters

| Option                        | Description                                                                                                                                                                                                                                                                                                                     | Default value                                                                  |
|:------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------|
| verbose                       | Enable verbose mode.                                                                                                                                                                                                                                                                                                            | `false`                                                                        |
| schemaVersion                 | Schema version to be used for generation. eg. `13.0` (see [Schema.org releases](https://github.com/schemaorg/schemaorg/tree/main/data/releases)).<br/>Specify the value `latest` to use the lastest version.                                                                                                                    | Local resource named `schemaorg-current-https.jsonld` present in the classpath |
| schemaResource                | Schema resource location to be used for generation. The value can be either a "classpath:" pseudo URL, a "file:" URL, or a plain file path.                                                                                                                                                                                     | If not defined, uses the 'schemaVersion' parameter.                            |
| javaTypes                     | Use Java types instead of schema.org DataTypes.                                                                                                                                                                                                                                                                                 | `false`: schema.org DataTypes are used.                                        |
| models                        | A comma separated list of models to generate.                                                                                                                                                                                                                                                                                   | All models                                                                     |
| output                        | Location of the output directory.                                                                                                                                                                                                                                                                                               | `${project.build.directory}/generated-sources/schemaorg`                       |
| modelPackage                  | Package of the models                                                                                                                                                                                                                                                                                                           | `org.schema.model`                                                             |
| modelImplPackage              | Package of the model implementations                                                                                                                                                                                                                                                                                            | `org.schema.model.impl`                                                        |
| dataTypePackage               | Package of the data type                                                                                                                                                                                                                                                                                                        | `org.schema.model.datatype`                                                    |
| customDataTypes               | Configures Java types to be used for Schema.org data types during code generation DateTime=java.time.ZonedDateTime).                                                                                                                                                                                                            | If not defined, uses default java types mapping                                |
| skip                          | Skip the execution.<br/>Can also be set globally through the `weedow.schemaorg.generator.maven.plugin.skip` property.                                                                                                                                                                                                           | `false`                                                                        |
| sourcesAndResourcesProcessing | Specify the behavior of the plugin with the generated java types and generated resources.<br/><ul><li>SOURCES_AND_RESOURCES: Add the output directory to the project as a source root</li><li>TEST_SOURCES_AND_RESOURCES: Add the output directory to the project as a test source root</li><li>`NOTHING`: Do nothing</li></ul> | `SOURCES_AND_RESOURCES`                                                        |

---

[Home](https://github.com/Kobee1203/schema-org-java)