
# Change Log
All notable changes to this project will be documented in this file.

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