/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.DataCatalog;
import spec.model.datatype.DateTime;
import spec.model.datatype.Text;
import spec.model.PropertyValue;
import spec.model.datatype.URL;
import spec.model.DataDownload;

/**
 * A body of structured information describing some topic(s) of interest.
 *
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_DatasetClass">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_DatasetClass</a>
 * @see <a href="https://schema.org/Dataset">https://schema.org/Dataset</a>
 */
public interface Dataset extends CreativeWork {

    /**
     * A data catalog which contains this dataset.
     *
     * @return {@link DataCatalog}
     */
    List<DataCatalog> getCatalogList();

    /**
     * A data catalog which contains this dataset.
     *
     * @return {@link DataCatalog}
     */
    DataCatalog getCatalog();

    /**
     * A data catalog which contains this dataset.
     *
     * @param catalog DataCatalog value to set.
     */
    void addCatalog(DataCatalog catalog);

    /**
     * The range of temporal applicability of a dataset, e.g. for a 2011 census dataset, the year 2011 (in ISO 8601 time interval format).
     *
     * @return {@link DateTime}
     */
    List<DateTime> getDatasetTimeIntervalList();

    /**
     * The range of temporal applicability of a dataset, e.g. for a 2011 census dataset, the year 2011 (in ISO 8601 time interval format).
     *
     * @return {@link DateTime}
     */
    DateTime getDatasetTimeInterval();

    /**
     * The range of temporal applicability of a dataset, e.g. for a 2011 census dataset, the year 2011 (in ISO 8601 time interval format).
     *
     * @param datasetTimeInterval DateTime value to set.
     */
    void addDatasetTimeInterval(DateTime datasetTimeInterval);

    /**
     * The variableMeasured property can indicate (repeated as necessary) the  variables that are measured in some dataset, either described as text or as pairs of identifier and description using PropertyValue.
     *
     * @return {@link Text} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1083">https://github.com/schemaorg/schemaorg/issues/1083</a>
     */
    <T> List<T> getVariableMeasuredList();

    /**
     * The variableMeasured property can indicate (repeated as necessary) the  variables that are measured in some dataset, either described as text or as pairs of identifier and description using PropertyValue.
     *
     * @return {@link Text} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1083">https://github.com/schemaorg/schemaorg/issues/1083</a>
     */
    <T> T getVariableMeasured();

    /**
     * The variableMeasured property can indicate (repeated as necessary) the  variables that are measured in some dataset, either described as text or as pairs of identifier and description using PropertyValue.
     *
     * @param variableMeasured Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1083">https://github.com/schemaorg/schemaorg/issues/1083</a>
     */
    void addVariableMeasured(Text variableMeasured);
    /**
     * The variableMeasured property can indicate (repeated as necessary) the  variables that are measured in some dataset, either described as text or as pairs of identifier and description using PropertyValue.
     *
     * @param variableMeasured PropertyValue value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1083">https://github.com/schemaorg/schemaorg/issues/1083</a>
     */
    void addVariableMeasured(PropertyValue variableMeasured);

    /**
     * A data catalog which contains this dataset (this property was previously 'catalog', preferred name is now 'includedInDataCatalog').
     *
     * @return {@link DataCatalog}
     */
    List<DataCatalog> getIncludedDataCatalogList();

    /**
     * A data catalog which contains this dataset (this property was previously 'catalog', preferred name is now 'includedInDataCatalog').
     *
     * @return {@link DataCatalog}
     */
    DataCatalog getIncludedDataCatalog();

    /**
     * A data catalog which contains this dataset (this property was previously 'catalog', preferred name is now 'includedInDataCatalog').
     *
     * @param includedDataCatalog DataCatalog value to set.
     */
    void addIncludedDataCatalog(DataCatalog includedDataCatalog);

    /**
     * A technique or technology used in a [[Dataset]] (or [[DataDownload]], [[DataCatalog]]),
     * corresponding to the method used for measuring the corresponding variable(s) (described using [[variableMeasured]]). This is oriented towards scientific and scholarly dataset publication but may have broader applicability; it is not intended as a full representation of measurement, but rather as a high level summary for dataset discovery.
     * 
     * For example, if [[variableMeasured]] is: molecule concentration, [[measurementTechnique]] could be: "mass spectrometry" or "nmr spectroscopy" or "colorimetry" or "immunofluorescence".
     * 
     * If the [[variableMeasured]] is "depression rating", the [[measurementTechnique]] could be "Zung Scale" or "HAM-D" or "Beck Depression Inventory".
     * 
     * If there are several [[variableMeasured]] properties recorded for some given data object, use a [[PropertyValue]] for each [[variableMeasured]] and attach the corresponding [[measurementTechnique]].
     *       
     *
     * @return {@link URL} or {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1425">https://github.com/schemaorg/schemaorg/issues/1425</a>
     */
    <T> List<T> getMeasurementTechniqueList();

    /**
     * A technique or technology used in a [[Dataset]] (or [[DataDownload]], [[DataCatalog]]),
     * corresponding to the method used for measuring the corresponding variable(s) (described using [[variableMeasured]]). This is oriented towards scientific and scholarly dataset publication but may have broader applicability; it is not intended as a full representation of measurement, but rather as a high level summary for dataset discovery.
     * 
     * For example, if [[variableMeasured]] is: molecule concentration, [[measurementTechnique]] could be: "mass spectrometry" or "nmr spectroscopy" or "colorimetry" or "immunofluorescence".
     * 
     * If the [[variableMeasured]] is "depression rating", the [[measurementTechnique]] could be "Zung Scale" or "HAM-D" or "Beck Depression Inventory".
     * 
     * If there are several [[variableMeasured]] properties recorded for some given data object, use a [[PropertyValue]] for each [[variableMeasured]] and attach the corresponding [[measurementTechnique]].
     *       
     *
     * @return {@link URL} or {@link Text}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1425">https://github.com/schemaorg/schemaorg/issues/1425</a>
     */
    <T> T getMeasurementTechnique();

    /**
     * A technique or technology used in a [[Dataset]] (or [[DataDownload]], [[DataCatalog]]),
     * corresponding to the method used for measuring the corresponding variable(s) (described using [[variableMeasured]]). This is oriented towards scientific and scholarly dataset publication but may have broader applicability; it is not intended as a full representation of measurement, but rather as a high level summary for dataset discovery.
     * 
     * For example, if [[variableMeasured]] is: molecule concentration, [[measurementTechnique]] could be: "mass spectrometry" or "nmr spectroscopy" or "colorimetry" or "immunofluorescence".
     * 
     * If the [[variableMeasured]] is "depression rating", the [[measurementTechnique]] could be "Zung Scale" or "HAM-D" or "Beck Depression Inventory".
     * 
     * If there are several [[variableMeasured]] properties recorded for some given data object, use a [[PropertyValue]] for each [[variableMeasured]] and attach the corresponding [[measurementTechnique]].
     *       
     *
     * @param measurementTechnique URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1425">https://github.com/schemaorg/schemaorg/issues/1425</a>
     */
    void addMeasurementTechnique(URL measurementTechnique);
    /**
     * A technique or technology used in a [[Dataset]] (or [[DataDownload]], [[DataCatalog]]),
     * corresponding to the method used for measuring the corresponding variable(s) (described using [[variableMeasured]]). This is oriented towards scientific and scholarly dataset publication but may have broader applicability; it is not intended as a full representation of measurement, but rather as a high level summary for dataset discovery.
     * 
     * For example, if [[variableMeasured]] is: molecule concentration, [[measurementTechnique]] could be: "mass spectrometry" or "nmr spectroscopy" or "colorimetry" or "immunofluorescence".
     * 
     * If the [[variableMeasured]] is "depression rating", the [[measurementTechnique]] could be "Zung Scale" or "HAM-D" or "Beck Depression Inventory".
     * 
     * If there are several [[variableMeasured]] properties recorded for some given data object, use a [[PropertyValue]] for each [[variableMeasured]] and attach the corresponding [[measurementTechnique]].
     *       
     *
     * @param measurementTechnique Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1425">https://github.com/schemaorg/schemaorg/issues/1425</a>
     */
    void addMeasurementTechnique(Text measurementTechnique);

    /**
     * A downloadable form of this dataset, at a specific location, in a specific format. This property can be repeated if different variations are available. There is no expectation that different downloadable distributions must contain exactly equivalent information (see also [DCAT](https://www.w3.org/TR/vocab-dcat-3/#Class:Distribution) on this point). Different distributions might include or exclude different subsets of the entire dataset, for example.
     *
     * @return {@link DataDownload}
     */
    List<DataDownload> getDistributionList();

    /**
     * A downloadable form of this dataset, at a specific location, in a specific format. This property can be repeated if different variations are available. There is no expectation that different downloadable distributions must contain exactly equivalent information (see also [DCAT](https://www.w3.org/TR/vocab-dcat-3/#Class:Distribution) on this point). Different distributions might include or exclude different subsets of the entire dataset, for example.
     *
     * @return {@link DataDownload}
     */
    DataDownload getDistribution();

    /**
     * A downloadable form of this dataset, at a specific location, in a specific format. This property can be repeated if different variations are available. There is no expectation that different downloadable distributions must contain exactly equivalent information (see also [DCAT](https://www.w3.org/TR/vocab-dcat-3/#Class:Distribution) on this point). Different distributions might include or exclude different subsets of the entire dataset, for example.
     *
     * @param distribution DataDownload value to set.
     */
    void addDistribution(DataDownload distribution);

    /**
     * A data catalog which contains this dataset.
     *
     * @return {@link DataCatalog}
     */
    List<DataCatalog> getIncludedInDataCatalogList();

    /**
     * A data catalog which contains this dataset.
     *
     * @return {@link DataCatalog}
     */
    DataCatalog getIncludedInDataCatalog();

    /**
     * A data catalog which contains this dataset.
     *
     * @param includedInDataCatalog DataCatalog value to set.
     */
    void addIncludedInDataCatalog(DataCatalog includedInDataCatalog);

    /**
     * The International Standard Serial Number (ISSN) that identifies this serial publication. You can repeat this property to identify different formats of, or the linking ISSN (ISSN-L) for, this serial publication.
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    List<Text> getIssnList();

    /**
     * The International Standard Serial Number (ISSN) that identifies this serial publication. You can repeat this property to identify different formats of, or the linking ISSN (ISSN-L) for, this serial publication.
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    Text getIssn();

    /**
     * The International Standard Serial Number (ISSN) that identifies this serial publication. You can repeat this property to identify different formats of, or the linking ISSN (ISSN-L) for, this serial publication.
     *
     * @param issn Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    void addIssn(Text issn);
}
