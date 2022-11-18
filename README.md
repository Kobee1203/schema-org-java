# Schema.org Java library

- [Overview](#Overview)
- [Build](#Build)
- [Generator](#Generator)
  - [Maven Plugin](#Maven-Plugin)
  - [Programmatically](#Programmatically)
  - [Command line](#Command-line)
- [Issues](#Issues)
- [Contributing](#Contributing)
- [Contact](#Contact)
- [Social Networks](#Social-Networks)
- [License](#License)

## <a name="Overview">Overview</a>

`Schema-org-java` is a library for creating [schema.org](http://schema.org) entities.

The entities can be easily generated with the [maven plugin](https://github.com/Kobee1203/schema-org-java/tree/main/maven-plugin), programmatically, or in command line.

The entities can be easily serialized and deserialized with [JSON-LD](https://json-ld.org) format by using the JSON-LD serializer in the library.

The library has the following features:

* Fully supports the vocabulary defined in the http://schema.org namespace.
* Fully supports type multiple inheritance, multiple values per property in http://schema.org.
* Every schema.org type has a corresponding Java interface which provides convenient getter/setter methods for getting/setting the values of the
  properties of that particular type.
* Supports the generation of all Schema.org types at once, or specific ones.
* Fully supports Schema.org documentation of types and properties by adding Javadoc on generated classes and methods. 
* Supports different versions of the [Schema.org vocabulary](https://schema.org/docs/developers.html#defs).
* Supports serializing and deserializing schema.org objects to and from JSON-LD formats.

https://user-images.githubusercontent.com/1423193/199852384-e41869fb-beae-4106-be0f-1482393b9e0c.mp4

## <a name="Build">Build</a>
[![GitHub repo size](https://img.shields.io/github/repo-size/Kobee1203/schema-org-java)](https://github.com/Kobee1203/schema-org-java)
[![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/Kobee1203/schema-org-java)](https://github.com/Kobee1203/schema-org-java)

[![Build](https://img.shields.io/github/workflow/status/Kobee1203/schema-org-java/Build%20and%20Analyze)](https://github.com/Kobee1203/schema-org-java/actions?query=workflow%3A%22Build+and+Analyze%22)
[![Libraries.io dependency status for GitHub repo](https://img.shields.io/librariesio/github/Kobee1203/schema-org-java)](https://libraries.io/github/Kobee1203/schema-org-java)

[![Code Coverage](https://img.shields.io/sonar/coverage/schema-org-java?server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=schema-org-java)
[![Sonar Quality Gate](https://img.shields.io/sonar/quality_gate/schema-org-java?server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=schema-org-java)
[![Sonar Tech Debt](https://img.shields.io/sonar/tech_debt/schema-org-java?server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=schema-org-java)
[![Sonar Violations](https://img.shields.io/sonar/violations/schema-org-java?server=https%3A%2F%2Fsonarcloud.io)](https://sonarcloud.io/dashboard?id=schema-org-java)

## <a name="Generator">Generator</a>

[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/Kobee1203/schema-org-java?include_prereleases)](https://github.com/Kobee1203/schema-org-java/releases)
[![Downloads](https://img.shields.io/github/downloads/Kobee1203/schema-org-java/total)](https://github.com/Kobee1203/schema-org-java/releases)
[![Maven Central](https://img.shields.io/maven-central/v/com.weedow/schema-org-generator)](https://search.maven.org/search?q=g:com.weedow%20AND%20a:schema-org-generator*)

The library allows to generate Schema.org types in several ways.

### <a name="Maven-Plugin">Maven Plugin</a>

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

### <a name="Programmatically">Programmatically</a>

#### Example with default configuration

```java
import com.weedow.schemaorg.generator.SchemaModelGeneratorBuilder;
import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;

final class GeneratorUtils {

  private GeneratorUtils() {
  }

  public static void generate() {
    ParserOptions parserOptions = new ParserOptions();

    GeneratorOptions generatorOptions = new GeneratorOptions();

    final SchemaModelGenerator generator = schemaModelGeneratorBuilder()
            .parserOptions(parserOptions)
            .generatorOptions(generatorOptions)
            .build();
    generator.generate();
  }
}
```

#### Example with custom configuration

```java
import com.weedow.schemaorg.generator.SchemaModelGeneratorBuilder;
import com.weedow.schemaorg.generator.core.GeneratorOptions;
import com.weedow.schemaorg.generator.core.SchemaModelGenerator;
import com.weedow.schemaorg.generator.parser.ParserOptions;

final class GeneratorUtils {

  private GeneratorUtils() {
  }

  public static void generate(Path output, List<String> models, String schemaVersion, ...) {
    ParserOptions parserOptions = new ParserOptions();
    // Default is 'latest'. Pass null to use local resource  named `schemaorg-current-https.jsonld` present in the classpath
    parserOptions.setSchemaVersion(schemaVersion);

    GeneratorOptions generatorOptions = new GeneratorOptions()
            .setOutputFolder(output)
            .setModels(models)
            .setModelPackage(modelPackage)
            .setModelImplPackage(modelImplPackage)
            .setDataTypePackage(dataTypePackage);

    final SchemaModelGenerator generator = schemaModelGeneratorBuilder()
            .parserOptions(parserOptions)
            .generatorOptions(generatorOptions)
            .verbose(verbose)
            .build();
    generator.generate();
  }
}
```

### <a name="Command-line">Command line</a>

Download the last version of the [distribution zip](https://github.com/Kobee1203/schema-org-java/releases), and extract the `schema-org-generator-{version}-jar-with-dependencies.jar`  

```shell
$ java -jar schema-org-generator-{version}-jar-with-dependencies.jar --help

usage: java -jar schema-org-generator.jar SchemaModelGeneratorApp [-h] [-m
       <models>] [-V <version>] [-v]
 -h,--help                Show the help message
 -m,--models <models>     list of models to be generated. If not
                          specified, all models will be generated.
 -V,--version <version>   Schema version to be used: 'latest' to use the
                          latest version, or specific version (eg. 13.0).
                          If not specified, the generator uses the
                          resource in the JAR. see
                          https://github.com/schemaorg/schemaorg/tree/main
                          /data/releases
 -v,--verbose             Verbose
```

#### Example with default configuration
The default configuration generates all Schema.org entities.

```
$ java -jar schema-org-generator-{version}-jar-with-dependencies.jar
```

#### Example with custom configuration

```
$ java -jar schema-org-generator-{version}-jar-with-dependencies.jar -m Thing Hotel
```

## Issues
[![Issues](https://img.shields.io/github/issues/Kobee1203/schema-org-java)](https://github.com/Kobee1203/schema-org-java/issues)

## <a name="Contributing">Contributing</a>

Contributions are what make the open source community such an amazing place to be learned, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## <a name="Contact">Contact</a>

Nicolas Dos Santos - [@Kobee1203](https://twitter.com/Kobee1203)

Project Link: <https://github.com/Kobee1203/schema-org-java>

## <a name="Social-Networks">Social Networks</a>

[![Tweets](https://img.shields.io/twitter/url?style=social&url=https%3A%2F%2Fgithub.com%2FKobee1203%2Fschema-org-java)](https://twitter.com/intent/tweet?text=Schema.org%20Java%20library%0AJava%20library%20for%20working%20with%20Schema.org%20data%20in%20JSON-LD%20format.%0A%0A&url=https%3A%2F%2Fgithub.com%2FKobee1203%2Fschema-org-java&hashtags=schemaorg,java,github)

[![GitHub forks](https://img.shields.io/github/forks/Kobee1203/schema-org-java?style=social)]()
[![GitHub stars](https://img.shields.io/github/stars/Kobee1203/schema-org-java?style=social)]()
[![GitHub watchers](https://img.shields.io/github/watchers/Kobee1203/schema-org-java?style=social)]()

## <a name="License">License</a>

[![MIT License](https://img.shields.io/github/license/Kobee1203/schema-org-java)](https://github.com/Kobee1203/schema-org-java/blob/master/LICENSE.txt) \
_Copyright (c) 2022 Nicolas Dos Santos and other contributors_