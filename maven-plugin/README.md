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

| Option               | Description                                                                                                                                                                                                 | Default value                                                                  |
|:---------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------|
| verbose              | Enable verbose mode.                                                                                                                                                                                        | `false`                                                                        |
| schemaVersion        | Schema version to be used for generation. eg. `13.0` (see [Schema.org releases](https://github.com/schemaorg/schemaorg/tree/main/data/releases)).<br/>Specify the value `latest` to use the lastes version. | Local resource named `schemaorg-current-https.jsonld` present in the classpath |
| models               | A comma separated list of models to generate.                                                                                                                                                               | All models                                                                     |
| output               | Location of the output directory.                                                                                                                                                                           | `${project.build.directory}/generated-sources/schemaorg`                       |
| modelPackage         | Package of the models                                                                                                                                                                                       | `org.schema.model`                                                             |
| modelImplPackage     | Package of the model implementations                                                                                                                                                                        | `org.schema.model.impl`                                                        |
| dataTypePackage      | Package of the data type                                                                                                                                                                                    | `org.schema.model.datatype`                                                    |
| skip                 | Skip the execution.<br/>Can also be set globally through the `weedow.schemaorg.generator.maven.plugin.skip` property.                                                                                       | `false`                                                                        |
| addCompileSourceRoot | Add the output directory to the project as a source root, so that the generated java types are compiled and included in the project artifact.                                                               | `true`                                                                         |

---

[Home](https://github.com/Kobee1203/schema-org-java)