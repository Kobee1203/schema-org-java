/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.EnergyEfficiencyEnumeration;
import spec.model.EUEnergyEfficiencyEnumeration;

/**
 * EnergyConsumptionDetails represents information related to the energy efficiency of a product that consumes energy. The information that can be provided is based on international regulations such as for example [EU directive 2017/1369](https://eur-lex.europa.eu/eli/reg/2017/1369/oj) for energy labeling and the [Energy labeling rule](https://www.ftc.gov/enforcement/rules/rulemaking-regulatory-reform-proceedings/energy-water-use-labeling-consumer) under the Energy Policy and Conservation Act (EPCA) in the US.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
 * @see <a href="https://schema.org/EnergyConsumptionDetails">https://schema.org/EnergyConsumptionDetails</a>
 */
public interface EnergyConsumptionDetails extends Intangible {

    /**
     * Defines the energy efficiency Category (which could be either a rating out of range of values or a yes/no certification) for a product according to an international energy efficiency standard.
     *
     * @return {@link EnergyEfficiencyEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    List<EnergyEfficiencyEnumeration> getHasEnergyEfficiencyCategoryList();

    /**
     * Defines the energy efficiency Category (which could be either a rating out of range of values or a yes/no certification) for a product according to an international energy efficiency standard.
     *
     * @return {@link EnergyEfficiencyEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    EnergyEfficiencyEnumeration getHasEnergyEfficiencyCategory();

    /**
     * Defines the energy efficiency Category (which could be either a rating out of range of values or a yes/no certification) for a product according to an international energy efficiency standard.
     *
     * @param hasEnergyEfficiencyCategory EnergyEfficiencyEnumeration value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    void addHasEnergyEfficiencyCategory(EnergyEfficiencyEnumeration hasEnergyEfficiencyCategory);

    /**
     * Specifies the least energy efficient class on the regulated EU energy consumption scale for the product category a product belongs to. For example, energy consumption for televisions placed on the market after January 1, 2020 is scaled from D to A+++.
     *
     * @return {@link EUEnergyEfficiencyEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    List<EUEnergyEfficiencyEnumeration> getEnergyEfficiencyScaleMinList();

    /**
     * Specifies the least energy efficient class on the regulated EU energy consumption scale for the product category a product belongs to. For example, energy consumption for televisions placed on the market after January 1, 2020 is scaled from D to A+++.
     *
     * @return {@link EUEnergyEfficiencyEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    EUEnergyEfficiencyEnumeration getEnergyEfficiencyScaleMin();

    /**
     * Specifies the least energy efficient class on the regulated EU energy consumption scale for the product category a product belongs to. For example, energy consumption for televisions placed on the market after January 1, 2020 is scaled from D to A+++.
     *
     * @param energyEfficiencyScaleMin EUEnergyEfficiencyEnumeration value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    void addEnergyEfficiencyScaleMin(EUEnergyEfficiencyEnumeration energyEfficiencyScaleMin);

    /**
     * Specifies the most energy efficient class on the regulated EU energy consumption scale for the product category a product belongs to. For example, energy consumption for televisions placed on the market after January 1, 2020 is scaled from D to A+++.
     *
     * @return {@link EUEnergyEfficiencyEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    List<EUEnergyEfficiencyEnumeration> getEnergyEfficiencyScaleMaxList();

    /**
     * Specifies the most energy efficient class on the regulated EU energy consumption scale for the product category a product belongs to. For example, energy consumption for televisions placed on the market after January 1, 2020 is scaled from D to A+++.
     *
     * @return {@link EUEnergyEfficiencyEnumeration}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    EUEnergyEfficiencyEnumeration getEnergyEfficiencyScaleMax();

    /**
     * Specifies the most energy efficient class on the regulated EU energy consumption scale for the product category a product belongs to. For example, energy consumption for televisions placed on the market after January 1, 2020 is scaled from D to A+++.
     *
     * @param energyEfficiencyScaleMax EUEnergyEfficiencyEnumeration value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/2670">https://github.com/schemaorg/schemaorg/issues/2670</a>
     */
    void addEnergyEfficiencyScaleMax(EUEnergyEfficiencyEnumeration energyEfficiencyScaleMax);
}
