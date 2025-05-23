/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.BioChemEntity;
import spec.model.Gene;
import spec.model.datatype.URL;
import spec.model.DefinedTerm;
import spec.model.datatype.Text;
import spec.model.Taxon;
import spec.model.PropertyValue;
import spec.model.Grant;
import spec.model.MedicalCondition;

/**
 * Any biological, chemical, or biochemical thing. For example: a protein; a gene; a chemical; a synthetic chemical.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://schema.org/BioChemEntity">https://schema.org/BioChemEntity</a>
 */
public interface BioChemEntity extends Thing {

    /**
     * Indicates a BioChemEntity that (in some sense) has this BioChemEntity as a part. 
     *
     * @return {@link BioChemEntity}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    List<BioChemEntity> getHasBioChemEntityPartList();

    /**
     * Indicates a BioChemEntity that (in some sense) has this BioChemEntity as a part. 
     *
     * @return {@link BioChemEntity}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    BioChemEntity getHasBioChemEntityPart();

    /**
     * Indicates a BioChemEntity that (in some sense) has this BioChemEntity as a part. 
     *
     * @param hasBioChemEntityPart BioChemEntity value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addHasBioChemEntityPart(BioChemEntity hasBioChemEntityPart);

    /**
     * Another BioChemEntity encoding by this one.
     *
     * @return {@link Gene}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    List<Gene> getIsEncodedByBioChemEntityList();

    /**
     * Another BioChemEntity encoding by this one.
     *
     * @return {@link Gene}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    Gene getIsEncodedByBioChemEntity();

    /**
     * Another BioChemEntity encoding by this one.
     *
     * @param isEncodedByBioChemEntity Gene value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addIsEncodedByBioChemEntity(Gene isEncodedByBioChemEntity);

    /**
     * The taxonomic grouping of the organism that expresses, encodes, or in some way related to the BioChemEntity.
     *
     * @return {@link URL} or {@link DefinedTerm} or {@link Text} or {@link Taxon}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> List<T> getTaxonomicRangeList();

    /**
     * The taxonomic grouping of the organism that expresses, encodes, or in some way related to the BioChemEntity.
     *
     * @return {@link URL} or {@link DefinedTerm} or {@link Text} or {@link Taxon}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> T getTaxonomicRange();

    /**
     * The taxonomic grouping of the organism that expresses, encodes, or in some way related to the BioChemEntity.
     *
     * @param taxonomicRange URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addTaxonomicRange(URL taxonomicRange);
    /**
     * The taxonomic grouping of the organism that expresses, encodes, or in some way related to the BioChemEntity.
     *
     * @param taxonomicRange DefinedTerm value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addTaxonomicRange(DefinedTerm taxonomicRange);
    /**
     * The taxonomic grouping of the organism that expresses, encodes, or in some way related to the BioChemEntity.
     *
     * @param taxonomicRange Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addTaxonomicRange(Text taxonomicRange);
    /**
     * The taxonomic grouping of the organism that expresses, encodes, or in some way related to the BioChemEntity.
     *
     * @param taxonomicRange Taxon value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addTaxonomicRange(Taxon taxonomicRange);

    /**
     * Subcellular location where this BioChemEntity is located; please use PropertyValue if you want to include any evidence.
     *
     * @return {@link URL} or {@link DefinedTerm} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> List<T> getIsLocatedInSubcellularLocationList();

    /**
     * Subcellular location where this BioChemEntity is located; please use PropertyValue if you want to include any evidence.
     *
     * @return {@link URL} or {@link DefinedTerm} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> T getIsLocatedInSubcellularLocation();

    /**
     * Subcellular location where this BioChemEntity is located; please use PropertyValue if you want to include any evidence.
     *
     * @param isLocatedInSubcellularLocation URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addIsLocatedInSubcellularLocation(URL isLocatedInSubcellularLocation);
    /**
     * Subcellular location where this BioChemEntity is located; please use PropertyValue if you want to include any evidence.
     *
     * @param isLocatedInSubcellularLocation DefinedTerm value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addIsLocatedInSubcellularLocation(DefinedTerm isLocatedInSubcellularLocation);
    /**
     * Subcellular location where this BioChemEntity is located; please use PropertyValue if you want to include any evidence.
     *
     * @param isLocatedInSubcellularLocation PropertyValue value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addIsLocatedInSubcellularLocation(PropertyValue isLocatedInSubcellularLocation);

    /**
     * A BioChemEntity that is known to interact with this item.
     *
     * @return {@link BioChemEntity}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    List<BioChemEntity> getBioChemInteractionList();

    /**
     * A BioChemEntity that is known to interact with this item.
     *
     * @return {@link BioChemEntity}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    BioChemEntity getBioChemInteraction();

    /**
     * A BioChemEntity that is known to interact with this item.
     *
     * @param bioChemInteraction BioChemEntity value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addBioChemInteraction(BioChemEntity bioChemInteraction);

    /**
     * A [[Grant]] that directly or indirectly provide funding or sponsorship for this item. See also [[ownershipFundingInfo]].
     *
     * @return {@link Grant}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/383">https://github.com/schemaorg/schemaorg/issues/383</a>
     */
    List<Grant> getFundingList();

    /**
     * A [[Grant]] that directly or indirectly provide funding or sponsorship for this item. See also [[ownershipFundingInfo]].
     *
     * @return {@link Grant}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/383">https://github.com/schemaorg/schemaorg/issues/383</a>
     */
    Grant getFunding();

    /**
     * A [[Grant]] that directly or indirectly provide funding or sponsorship for this item. See also [[ownershipFundingInfo]].
     *
     * @param funding Grant value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/383">https://github.com/schemaorg/schemaorg/issues/383</a>
     */
    void addFunding(Grant funding);

    /**
     * Indicates a BioChemEntity that is (in some sense) a part of this BioChemEntity. 
     *
     * @return {@link BioChemEntity}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    List<BioChemEntity> getIsPartOfBioChemEntityList();

    /**
     * Indicates a BioChemEntity that is (in some sense) a part of this BioChemEntity. 
     *
     * @return {@link BioChemEntity}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    BioChemEntity getIsPartOfBioChemEntity();

    /**
     * Indicates a BioChemEntity that is (in some sense) a part of this BioChemEntity. 
     *
     * @param isPartOfBioChemEntity BioChemEntity value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addIsPartOfBioChemEntity(BioChemEntity isPartOfBioChemEntity);

    /**
     * A similar BioChemEntity, e.g., obtained by fingerprint similarity algorithms.
     *
     * @return {@link BioChemEntity}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    List<BioChemEntity> getBioChemSimilarityList();

    /**
     * A similar BioChemEntity, e.g., obtained by fingerprint similarity algorithms.
     *
     * @return {@link BioChemEntity}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    BioChemEntity getBioChemSimilarity();

    /**
     * A similar BioChemEntity, e.g., obtained by fingerprint similarity algorithms.
     *
     * @param bioChemSimilarity BioChemEntity value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addBioChemSimilarity(BioChemEntity bioChemSimilarity);

    /**
     * A common representation such as a protein sequence or chemical structure for this entity. For images use schema.org/image.
     *
     * @return {@link Text} or {@link URL} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> List<T> getHasRepresentationList();

    /**
     * A common representation such as a protein sequence or chemical structure for this entity. For images use schema.org/image.
     *
     * @return {@link Text} or {@link URL} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> T getHasRepresentation();

    /**
     * A common representation such as a protein sequence or chemical structure for this entity. For images use schema.org/image.
     *
     * @param hasRepresentation Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addHasRepresentation(Text hasRepresentation);
    /**
     * A common representation such as a protein sequence or chemical structure for this entity. For images use schema.org/image.
     *
     * @param hasRepresentation URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addHasRepresentation(URL hasRepresentation);
    /**
     * A common representation such as a protein sequence or chemical structure for this entity. For images use schema.org/image.
     *
     * @param hasRepresentation PropertyValue value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addHasRepresentation(PropertyValue hasRepresentation);

    /**
     * A role played by the BioChemEntity within a biological context.
     *
     * @return {@link DefinedTerm}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    List<DefinedTerm> getBiologicalRoleList();

    /**
     * A role played by the BioChemEntity within a biological context.
     *
     * @return {@link DefinedTerm}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    DefinedTerm getBiologicalRole();

    /**
     * A role played by the BioChemEntity within a biological context.
     *
     * @param biologicalRole DefinedTerm value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addBiologicalRole(DefinedTerm biologicalRole);

    /**
     * Biological process this BioChemEntity is involved in; please use PropertyValue if you want to include any evidence.
     *
     * @return {@link DefinedTerm} or {@link PropertyValue} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> List<T> getIsInvolvedInBiologicalProcessList();

    /**
     * Biological process this BioChemEntity is involved in; please use PropertyValue if you want to include any evidence.
     *
     * @return {@link DefinedTerm} or {@link PropertyValue} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> T getIsInvolvedInBiologicalProcess();

    /**
     * Biological process this BioChemEntity is involved in; please use PropertyValue if you want to include any evidence.
     *
     * @param isInvolvedInBiologicalProcess DefinedTerm value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addIsInvolvedInBiologicalProcess(DefinedTerm isInvolvedInBiologicalProcess);
    /**
     * Biological process this BioChemEntity is involved in; please use PropertyValue if you want to include any evidence.
     *
     * @param isInvolvedInBiologicalProcess PropertyValue value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addIsInvolvedInBiologicalProcess(PropertyValue isInvolvedInBiologicalProcess);
    /**
     * Biological process this BioChemEntity is involved in; please use PropertyValue if you want to include any evidence.
     *
     * @param isInvolvedInBiologicalProcess URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addIsInvolvedInBiologicalProcess(URL isInvolvedInBiologicalProcess);

    /**
     * Disease associated to this BioChemEntity. Such disease can be a MedicalCondition or a URL. If you want to add an evidence supporting the association, please use PropertyValue.
     *
     * @return {@link MedicalCondition} or {@link URL} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> List<T> getAssociatedDiseaseList();

    /**
     * Disease associated to this BioChemEntity. Such disease can be a MedicalCondition or a URL. If you want to add an evidence supporting the association, please use PropertyValue.
     *
     * @return {@link MedicalCondition} or {@link URL} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> T getAssociatedDisease();

    /**
     * Disease associated to this BioChemEntity. Such disease can be a MedicalCondition or a URL. If you want to add an evidence supporting the association, please use PropertyValue.
     *
     * @param associatedDisease MedicalCondition value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addAssociatedDisease(MedicalCondition associatedDisease);
    /**
     * Disease associated to this BioChemEntity. Such disease can be a MedicalCondition or a URL. If you want to add an evidence supporting the association, please use PropertyValue.
     *
     * @param associatedDisease URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addAssociatedDisease(URL associatedDisease);
    /**
     * Disease associated to this BioChemEntity. Such disease can be a MedicalCondition or a URL. If you want to add an evidence supporting the association, please use PropertyValue.
     *
     * @param associatedDisease PropertyValue value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addAssociatedDisease(PropertyValue associatedDisease);

    /**
     * Molecular function performed by this BioChemEntity; please use PropertyValue if you want to include any evidence.
     *
     * @return {@link URL} or {@link DefinedTerm} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> List<T> getHasMolecularFunctionList();

    /**
     * Molecular function performed by this BioChemEntity; please use PropertyValue if you want to include any evidence.
     *
     * @return {@link URL} or {@link DefinedTerm} or {@link PropertyValue}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    <T> T getHasMolecularFunction();

    /**
     * Molecular function performed by this BioChemEntity; please use PropertyValue if you want to include any evidence.
     *
     * @param hasMolecularFunction URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addHasMolecularFunction(URL hasMolecularFunction);
    /**
     * Molecular function performed by this BioChemEntity; please use PropertyValue if you want to include any evidence.
     *
     * @param hasMolecularFunction DefinedTerm value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addHasMolecularFunction(DefinedTerm hasMolecularFunction);
    /**
     * Molecular function performed by this BioChemEntity; please use PropertyValue if you want to include any evidence.
     *
     * @param hasMolecularFunction PropertyValue value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     */
    void addHasMolecularFunction(PropertyValue hasMolecularFunction);
}
