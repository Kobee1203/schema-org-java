# Schema.org Java Generator

[Home](https://github.com/Kobee1203/schema-org-java)

---

This is a library for generating [schema.org](http://schema.org) entities.

The library has the following features:

* Fully supports the vocabulary defined in the http://schema.org namespace.
* Fully supports type multiple inheritance, multiple values per property in http://schema.org.
* Every schema.org type has a corresponding Java interface which provides convenient getter/setter methods for getting/setting the values of the properties of that particular type.
* Supports the generation of all Schema.org types at once, or specific ones.
* Fully supports Schema.org documentation of types and properties by adding Javadoc on generated classes and methods.
* Supports different versions of the [Schema.org vocabulary](https://schema.org/docs/developers.html#defs).

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
        // Default is `null`: uses local resource named `schemaorg-current-https.jsonld` present in the classpath.
        // Pass 'latest' to use the latest Schema.org version.
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

Download the last version of the [distribution zip](https://github.com/Kobee1203/schema-org-java/releases), and extract the `schema-org-generator-{version}-jar-with-dependencies.jar`.

```shell
$ java -jar schema-org-generator-{version}-jar-with-dependencies.jar --help

Usage: java -jar schema-org-generator-{version}-jar-with-dependencies.jar
       [-hVjv] [-m=<models>]... [-o=<output>] [-r=<schemaResource>]
       [-s=<schemaVersion>] [-D=<dataTypePackage>] [-I=<modelImplPackage>]
       [-M=<modelPackage>] [-c=<TYPE=JAVA_TYPE>]...

A CLI tool to generate Java models and data types from Schema.org definitions.

  -h, --help              Show this help message and exit.
  -V, --version           Print version information and exit.
  -m, --models=<models>   list of models to be generated. If not specified, all
                            models will be generated.
  -o, --output=<output>   Location of the output directory (default:
                            target/generated-sources/schemaorg)
  -r, --resource=<schemaResource>
                          Schema resource to be used: either a "classpath:"
                            pseudo URL, a "file:" URL, an URL or a plain file
                            path.
  -s, --schema-version=<schemaVersion>
                          Schema version to be used: 'latest' to use the latest
                            version, or specific version (eg. 13.0). If not
                            specified, the generator uses the resource in the
                            JAR.
  -D, --datatype-package=<dataTypePackage>
                          Package of the data types
                            Default: org.schema.model.datatype
  -I, --model-impl-package=<modelImplPackage>
                          Package of the model implementations
                            Default: org.schema.model.impl
  -M, --model-package=<modelPackage>
                          Package of the models
                            Default: org.schema.model.models
  -j, --javatypes         Use Java types instead of schema.org DataTypes. If
                            not specified, schema.org DataTypes are used.
  -c, --custom-datatypes=<TYPE=JAVA_TYPE>
                          Configures Java types to be used for Schema.org data
                            types during code generation (eg. DateTime=java.
                            time.ZonedDateTime)
  -v, --verbose           Verbose mode. Helpful for troubleshooting.

Please report issues at https://github.com/Kobee1203/schema-org-java/issues
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

---

[Home](https://github.com/Kobee1203/schema-org-java)