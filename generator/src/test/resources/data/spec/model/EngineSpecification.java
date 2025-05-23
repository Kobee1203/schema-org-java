/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.QuantitativeValue;
import spec.model.datatype.Text;
import spec.model.datatype.URL;
import spec.model.QualitativeValue;

/**
 * Information about the engine of the vehicle. A vehicle can have multiple engines represented by multiple engine specification entities.
 *
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
 * @see <a href="https://schema.org/EngineSpecification">https://schema.org/EngineSpecification</a>
 */
public interface EngineSpecification extends StructuredValue {

    /**
     * The power of the vehicle's engine.
     *     Typical unit code(s): KWT for kilowatt, BHP for brake horsepower, N12 for metric horsepower (PS, with 1 PS = 735,49875 W)<br/><br/>* Note 1: There are many different ways of measuring an engine's power. For an overview, see  [http://en.wikipedia.org/wiki/Horsepower#Engine\_power\_test\_codes](http://en.wikipedia.org/wiki/Horsepower#Engine_power_test_codes).<br/>* Note 2: You can link to information about how the given value has been determined using the [[valueReference]] property.<br/>* Note 3: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @return {@link QuantitativeValue}
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    List<QuantitativeValue> getEnginePowerList();

    /**
     * The power of the vehicle's engine.
     *     Typical unit code(s): KWT for kilowatt, BHP for brake horsepower, N12 for metric horsepower (PS, with 1 PS = 735,49875 W)<br/><br/>* Note 1: There are many different ways of measuring an engine's power. For an overview, see  [http://en.wikipedia.org/wiki/Horsepower#Engine\_power\_test\_codes](http://en.wikipedia.org/wiki/Horsepower#Engine_power_test_codes).<br/>* Note 2: You can link to information about how the given value has been determined using the [[valueReference]] property.<br/>* Note 3: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @return {@link QuantitativeValue}
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    QuantitativeValue getEnginePower();

    /**
     * The power of the vehicle's engine.
     *     Typical unit code(s): KWT for kilowatt, BHP for brake horsepower, N12 for metric horsepower (PS, with 1 PS = 735,49875 W)<br/><br/>* Note 1: There are many different ways of measuring an engine's power. For an overview, see  [http://en.wikipedia.org/wiki/Horsepower#Engine\_power\_test\_codes](http://en.wikipedia.org/wiki/Horsepower#Engine_power_test_codes).<br/>* Note 2: You can link to information about how the given value has been determined using the [[valueReference]] property.<br/>* Note 3: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @param enginePower QuantitativeValue value to set.
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addEnginePower(QuantitativeValue enginePower);

    /**
     * The torque (turning force) of the vehicle's engine.<br/><br/>Typical unit code(s): NU for newton metre (N m), F17 for pound-force per foot, or F48 for pound-force per inch<br/><br/>* Note 1: You can link to information about how the given value has been determined (e.g. reference RPM) using the [[valueReference]] property.<br/>* Note 2: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @return {@link QuantitativeValue}
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    List<QuantitativeValue> getTorqueList();

    /**
     * The torque (turning force) of the vehicle's engine.<br/><br/>Typical unit code(s): NU for newton metre (N m), F17 for pound-force per foot, or F48 for pound-force per inch<br/><br/>* Note 1: You can link to information about how the given value has been determined (e.g. reference RPM) using the [[valueReference]] property.<br/>* Note 2: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @return {@link QuantitativeValue}
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    QuantitativeValue getTorque();

    /**
     * The torque (turning force) of the vehicle's engine.<br/><br/>Typical unit code(s): NU for newton metre (N m), F17 for pound-force per foot, or F48 for pound-force per inch<br/><br/>* Note 1: You can link to information about how the given value has been determined (e.g. reference RPM) using the [[valueReference]] property.<br/>* Note 2: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @param torque QuantitativeValue value to set.
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addTorque(QuantitativeValue torque);

    /**
     * The type of engine or engines powering the vehicle.
     *
     * @return {@link Text} or {@link URL} or {@link QualitativeValue}
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    <T> List<T> getEngineTypeList();

    /**
     * The type of engine or engines powering the vehicle.
     *
     * @return {@link Text} or {@link URL} or {@link QualitativeValue}
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    <T> T getEngineType();

    /**
     * The type of engine or engines powering the vehicle.
     *
     * @param engineType Text value to set.
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addEngineType(Text engineType);
    /**
     * The type of engine or engines powering the vehicle.
     *
     * @param engineType URL value to set.
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addEngineType(URL engineType);
    /**
     * The type of engine or engines powering the vehicle.
     *
     * @param engineType QualitativeValue value to set.
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addEngineType(QualitativeValue engineType);

    /**
     * The type of fuel suitable for the engine or engines of the vehicle. If the vehicle has only one engine, this property can be attached directly to the vehicle.
     *
     * @return {@link URL} or {@link QualitativeValue} or {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    <T> List<T> getFuelTypeList();

    /**
     * The type of fuel suitable for the engine or engines of the vehicle. If the vehicle has only one engine, this property can be attached directly to the vehicle.
     *
     * @return {@link URL} or {@link QualitativeValue} or {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    <T> T getFuelType();

    /**
     * The type of fuel suitable for the engine or engines of the vehicle. If the vehicle has only one engine, this property can be attached directly to the vehicle.
     *
     * @param fuelType URL value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addFuelType(URL fuelType);
    /**
     * The type of fuel suitable for the engine or engines of the vehicle. If the vehicle has only one engine, this property can be attached directly to the vehicle.
     *
     * @param fuelType QualitativeValue value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addFuelType(QualitativeValue fuelType);
    /**
     * The type of fuel suitable for the engine or engines of the vehicle. If the vehicle has only one engine, this property can be attached directly to the vehicle.
     *
     * @param fuelType Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addFuelType(Text fuelType);

    /**
     * The volume swept by all of the pistons inside the cylinders of an internal combustion engine in a single movement. <br/><br/>Typical unit code(s): CMQ for cubic centimeter, LTR for liters, INQ for cubic inches<br/>* Note 1: You can link to information about how the given value has been determined using the [[valueReference]] property.<br/>* Note 2: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @return {@link QuantitativeValue}
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    List<QuantitativeValue> getEngineDisplacementList();

    /**
     * The volume swept by all of the pistons inside the cylinders of an internal combustion engine in a single movement. <br/><br/>Typical unit code(s): CMQ for cubic centimeter, LTR for liters, INQ for cubic inches<br/>* Note 1: You can link to information about how the given value has been determined using the [[valueReference]] property.<br/>* Note 2: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @return {@link QuantitativeValue}
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    QuantitativeValue getEngineDisplacement();

    /**
     * The volume swept by all of the pistons inside the cylinders of an internal combustion engine in a single movement. <br/><br/>Typical unit code(s): CMQ for cubic centimeter, LTR for liters, INQ for cubic inches<br/>* Note 1: You can link to information about how the given value has been determined using the [[valueReference]] property.<br/>* Note 2: You can use [[minValue]] and [[maxValue]] to indicate ranges.
     *
     * @param engineDisplacement QuantitativeValue value to set.
     * @see <a href="https://auto.schema.org">https://auto.schema.org</a>
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group</a>
     */
    void addEngineDisplacement(QuantitativeValue engineDisplacement);
}
