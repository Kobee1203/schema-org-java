
# Change Log
All notable changes to this project will be documented in this file.

## [Unreleased]

### Added

### Changed

### Fixed

## [0.8.0]

### Added

- [generator, maven-plugin] Add option to use direct Java data-types [#181](https://github.com/Kobee1203/schema-org-java/issues/181)
- [generator] Add CompleteHandler to process a code when type generation is complete

### Changed

- Bump lombok from 1.18.26 to 1.18.38 
- Bump logback-classic from 1.5.6 to 1.5.18 
- Bump jackson from 2.17.2 tp 2.18.3 
- Bump handlebars-helpers from 4.3.1 to 4.4.0 
- Bump commons-cli from 1.5.0 to 1.9.0
- Bump junit-jupiter-engine from 5.11.0 to 5.12.2
- Bump assertj-core from 3.24.2 to 3.27.3
- Bump mockito-junit-jupiter from 5.3.1 to 5.17.0
- Bump to-string-verifier from 1.4.8 to 3.19.3
- Bump json-unit-assertj from 3.4.1 to 4.1.0
- Bump system-stubs-jupiter from 2.0.2 to 2.1.8
- Bump logcaptor from 2.9.0 to 2.11.0
- Bump inject-resources-junit-jupiter from 0.3.2 to 1.0.0
- Bump org.apache.maven:maven-core from 3.9.7 to 3.9.9
- Bump org.apache.maven.plugins:maven-failsafe-plugin from 3.2.5 to 3.5.3
- Bump maven-dependency-plugin from 3.8.0 to 3.8.1
- Bump maven-javadoc-plugin from 3.5.0 to 3.11.2
- Bump maven-compiler-plugin from 3.11.0 to 3.14.0
- Bump maven-source-plugin from 3.2.1 to 3.3.1
- Bump maven-surefire-plugin from 3.2.5 to 3.5.3
- Bump maven-release-plugin from 3.0.0 to 3.1.1
- Bump maven-jxr-plugin from 3.3.0 to 3.6.0
- Bump org.apache.maven.plugins:maven-plugin-plugin from 3.15.0 to 3.15.1
- Bump org.apache.maven.plugins:maven-site-plugin from 3.20.0 to 3.21.0
- Bump maven-project-info-reports-plugin from 3.4.3 to 3.9.0
- Bump org.sonarsource.scanner.maven:sonar-maven-plugin from 3.9.1.2184 to 5.1.0.4751
- Bump jacoco-maven-plugin from 0.8.12 to 0.8.13
- Bump nexus-staging-maven-plugin from 1.6.13 to 1.7.0
- Bump org.simplify4u.plugins:sign-maven-plugin from 1.0.1 to 1.1.0


- Updated documentation
- [generator] Replace System.exit(0) by a simple return and prevent SecurityManager issue in unit tests with Java 17 or greater
- Use the 'sonar.token' property instead of 'sonar.login' property that is deprecated

### Fixed

- Fix Sonar issues

## [0.7.0] - 2024-09-23

### Changed

- ⚠️ **Breaking change: requires java 17 or newer**
- Bump jackson-bom from 2.14.2 to 2.17.2
- Bump net.javacrumbs.json-unit:json-unit-assertj from 2.37.0 to 3.4.1
- Bump de.jutzig:github-release-plugin from 1.4.0 to 1.6.0
- Bump maven-it-extension.version from 0.12.0 to 0.13.1
- Bump org.jacoco:jacoco-maven-plugin from 0.8.9 to 0.8.12
- Bump junit-jupiter-engine.version from 5.9.3 to 5.11.0
- Bump ch.qos.logback:logback-classic from 1.4.7 to 1.5.6
- Bump org.apache.maven:maven-core from 3.9.1 to 3.9.7
- Bump org.apache.maven:maven-plugin-api from 3.9.1 to 3.9.9
- Bump org.apache.maven.plugins:maven-dependency-plugin from 3.5.0 to 3.8.0
- Bump org.apache.maven.plugins:maven-site-plugin from 3.12.1 to 3.20.0
- Bump org.apache.maven.plugins:maven-plugin-plugin from 3.8.1 to 3.15.0
- Bump org.apache.maven.plugin-tools:maven-plugin-annotations from 3.8.2 to 3.13.0
- Bump org.apache.maven.plugins:maven-assembly-plugin from 3.5.0 to 3.7.1
- Bump org.apache.maven.plugins:maven-surefire-plugin from 3.0.0 to 3.2.5
- Bump org.apache.maven.plugins:maven-failsafe-plugin from 3.0.0 to 3.2.5
- Bump junit-jupiter-engine.version from 5.9.2 to 5.9.3

### Fixed

[serializer] Bump jackson version and fix JsonLdTypeFactory constructor [#145](https://github.com/Kobee1203/schema-org-java/issues/145)

## [0.6.0] - 2023-04-23

### Added

- [serializer] Deserialize a List of JSON-LD objects [#91](https://github.com/Kobee1203/schema-org-java/issues/91)
- [serializer] Unit test to check serialization/deserialization of field with special characters (eg. ':' -> prov:wasDerivedFrom) [#84](https://github.com/Kobee1203/schema-org-java/issues/84), [8af18334](https://github.com/Kobee1203/schema-org-java/commit/8af18334ab3c956abfc10a5e27382ebaeb026d8e)

### Changed

- Bump json-unit-assertj from 2.36.1 to 2.37.0
- Bump maven-core from 3.9.0 to 3.9.1
- Bump logcaptor from 2.8.0 to 2.9.0

### Fixed

- [serializer] Exclude interfaces from the package that contains custom types [453b0b84](https://github.com/Kobee1203/schema-org-java/commit/453b0b84dccb93fd5caa774b799452fea7764e7e)

## [0.5.0] - 2023-03-29

### Added

- [generator] Handle Enumeration Member defined for multiple Enumeration types [#74](https://github.com/Kobee1203/schema-org-java/issues/74)

### Changed

- [generator] Upgrade the version of the internal Vocabulary Definition File (schemaorg-current-https.jsonld) to 15.0 [#62](https://github.com/Kobee1203/schema-org-java/issues/62)
- Bump logback-classic from 1.4.5 to 1.4.6
- Bump mockito-junit-jupiter from 5.1.1 to 5.2.0
- Bump maven-compiler-plugin from 3.10.1 to 3.11.0
- Bump maven-plugin-api from 3.9.0 to 3.9.1
- Bump maven-plugin-plugin from 3.7.1 to 3.8.1
- Bump maven-plugin-annotations from 3.7.1 to 3.8.1
- Bump maven-assembly-plugin from 3.4.2 to 3.5.0
- Bump maven-javadoc-plugin from 3.4.1 to 3.5.0
- Bump maven-release-plugin from 3.0.0-M7 to 3.0.0
- Bump maven-surefire-plugin from 3.0.0-M8 to 3.0.0
- Bump maven-failsafe-plugin from 3.0.0-M8 to 3.0.0
- Bump equalsverifier from 3.13 to 3.14.1

### Fixed

- [generator] Fix getters method name for DataTypes without 'List' prefix

## [0.4.0] - 2023-02-07

### Added

- Supports deserialization of multiple values for a property [#53](https://github.com/Kobee1203/schema-org-java/issues/53)
  - **BREAKING CHANGE**: The `setXXX` methods of the generated classes are replaced by the `addXXX` methods
- Generated toString() method in abstract DataType

### Changed

- Updated documentation
- Bump assertj-core from 3.23.1 to 3.24.2
- Bump equalsverifier from 3.12.3 to 3.13
- Bump jackson-bom from 2.14.1 to 2.14.2
- Bump json-unit-assertj from 2.36.0 to 2.36.1
- Bump junit-jupiter-engine.version from 5.9.1 to 5.9.2
- Bump logcaptor from 2.7.10 to 2.8.0
- Bump lombok from 1.18.24 to 1.18.26
- Bump maven-core from 3.8.6 to 3.9.0
- Bump maven-dependency-plugin from 3.4.0 to 3.5.0
- Bump maven-failsafe-plugin from 3.0.0-M7 to 3.0.0-M8
- Bump maven-it-extension.version from 0.11.0 to 0.12.0
- Bump maven-plugin-annotations from 3.7.0 to 3.7.1
- Bump maven-plugin-api from 3.8.6 to 3.9.0
- Bump maven-plugin-plugin from 3.7.0 to 3.7.1
- Bump maven-project-info-reports-plugin from 3.4.1 to 3.4.2
- Bump maven-surefire-plugin from 3.0.0-M7 to 3.0.0-M8
- Bump mockito-junit-jupiter from 4.10.0 to 5.1.1
- Bump system-stubs-jupiter from 2.0.1 to 2.0.2

## [0.3.1] - 2022-12-27

### Added

- 'maven-plugin': New option `schemaResource` to load a custom JSON-LD file [#23](https://github.com/Kobee1203/schema-org-java/issues/23)

## [0.3.0] - 2022-12-17

### Added

- 'generator' module: Generates a property file with paths of models, models-impl, data-types, and commons models
- 'maven-plugin' module: Added generated property file as resource
- **BREAKING CHANGE**:\
  'maven-plugin' module: `addCompileSourceRoot` replaced by a new parameter `sourcesAndResourcesProcessing` that allows to add generated java types + generated resources (except .java file) as _source_ root or _test-source_ root
- 'serializer' module: Deserializer implementation
- 'commons' module: Added @JsonLdSubTypes annotation to specify subtypes of Schema.org type
- 'commons' module: Added JsonLdFieldTypes Annotation to indicate the types allowed for a field.

### Changed

- Bump jackson-bom from 2.14.0 to 2.14.1
- Bump logback-classic from 1.4.4 to 1.4.5
- Bump maven-dependency-plugin from 3.3.0 to 3.4.0
- Bump equalsverifier from 3.11.1 to 3.12.3
- Bump mockito-junit-jupiter from 4.9.0 to 4.10.0
- 'commons' module: Added equals/hashCode/toString for JsonLdNodeImpl class
- 'generator' module: Generated multiple setters for fields allowing multiple types and fields allowing a type inheriting from DataType.
- Updated javadoc
- Updated documentation

### Fixed

- Fixed json-LD type without 'schema' prefix

## [0.2.2] - 2022-11-21

### Changed

- Bump maven-plugin-annotations from 3.6.4 to 3.7.0
- Bump mockito-junit-jupiter from 4.8.1 to 4.9.0
- Bump maven-plugin-plugin from 3.6.4 to 3.7.0
- Bump equalsverifier from 3.11 to 3.11.1
- Bump jackson-bom from 2.13.4.20221013 to 2.14.0
- Updated documentation

### Fixed

- Added missing jars in the distribution: commons and serializer modules

## [0.2.1] - 2022-11-18

### Added

- Added Javadoc jars

## [0.2.0] - 2022-11-18

### Added

- New module 'commons' with common classes
  - JsonLdNode: Interface of JSON-LD node.
  - JsonLdNodeImpl: Implementation of JsonLdNode.
  - JsonLdTypeName: Annotation used for binding logical name that the annotated class has.
  - JsonLdDataType: extended by all DataType objects
- New module 'serializer' to serialize JSON-LD data
- Added BaseType representing the base parent (JsonLdNode) extended by Thing
- Java source files from 'commons' module added into the JAR to be able to copy them while generating the entities and the current project using the maven plugin has no dependencies with the 'commons' module
  - New option 'copyCommonModels' added in 'generator' module to copy JSON-LD models files from 'commons' module
  - 'maven-plugin' module: Copy common models if the artifact 'schema-org-java-commons' is not present in the current project

### Changed

- Updated documentation
- Remove jsonld_*** templates during generation, and use classes from 'commons' module

## [0.1.2] - 2022-11-03

### Changed

- Updated documentation

## [0.1.1] - 2022-11-03

### Fixed

- Update configuration to deploy release successfully

## [0.1.0] - 2022-11-03

schema-org-java is alive!

[unreleased]: https://github.com/Kobee1203/schema-org-java/compare/0.6.0...HEAD
[0.6.0]: https://github.com/Kobee1203/schema-org-java/compare/0.5.0...0.6.0
[0.5.0]: https://github.com/Kobee1203/schema-org-java/compare/0.4.0...0.5.0
[0.4.0]: https://github.com/Kobee1203/schema-org-java/compare/0.3.1...0.4.0
[0.3.1]: https://github.com/Kobee1203/schema-org-java/compare/0.3.0...0.3.1
[0.3.0]: https://github.com/Kobee1203/schema-org-java/compare/0.2.2...0.3.0
[0.2.2]: https://github.com/Kobee1203/schema-org-java/compare/0.2.1...0.2.2
[0.2.1]: https://github.com/Kobee1203/schema-org-java/compare/0.2.0...0.2.1
[0.2.0]: https://github.com/Kobee1203/schema-org-java/compare/0.1.2...0.2.0
[0.1.2]: https://github.com/Kobee1203/schema-org-java/compare/0.1.1...0.1.2
[0.1.1]: https://github.com/Kobee1203/schema-org-java/compare/0.1.0...0.1.1
[0.1.0]: https://github.com/Kobee1203/schema-org-java/commits/0.1.0
