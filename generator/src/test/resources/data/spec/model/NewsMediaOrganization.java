/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.AboutPage;
import spec.model.datatype.Text;
import spec.model.CreativeWork;
import spec.model.datatype.URL;
import spec.model.Article;

/**
 * A News/Media organization such as a newspaper or TV station.
 *
 * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
 * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
 * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
 * @see <a href="https://schema.org/NewsMediaOrganization">https://schema.org/NewsMediaOrganization</a>
 */
public interface NewsMediaOrganization extends Organization {

    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a description of organizational ownership structure; funding and grants. In a news/media setting, this is with particular reference to editorial independence.   Note that the [[funder]] is also available and can be used to make basic funder information machine-readable.
     *
     * @return {@link AboutPage} or {@link Text} or {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> List<T> getOwnershipFundingInfoList();

    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a description of organizational ownership structure; funding and grants. In a news/media setting, this is with particular reference to editorial independence.   Note that the [[funder]] is also available and can be used to make basic funder information machine-readable.
     *
     * @return {@link AboutPage} or {@link Text} or {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> T getOwnershipFundingInfo();

    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a description of organizational ownership structure; funding and grants. In a news/media setting, this is with particular reference to editorial independence.   Note that the [[funder]] is also available and can be used to make basic funder information machine-readable.
     *
     * @param ownershipFundingInfo AboutPage value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addOwnershipFundingInfo(AboutPage ownershipFundingInfo);
    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a description of organizational ownership structure; funding and grants. In a news/media setting, this is with particular reference to editorial independence.   Note that the [[funder]] is also available and can be used to make basic funder information machine-readable.
     *
     * @param ownershipFundingInfo Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addOwnershipFundingInfo(Text ownershipFundingInfo);
    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a description of organizational ownership structure; funding and grants. In a news/media setting, this is with particular reference to editorial independence.   Note that the [[funder]] is also available and can be used to make basic funder information machine-readable.
     *
     * @param ownershipFundingInfo CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addOwnershipFundingInfo(CreativeWork ownershipFundingInfo);
    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a description of organizational ownership structure; funding and grants. In a news/media setting, this is with particular reference to editorial independence.   Note that the [[funder]] is also available and can be used to make basic funder information machine-readable.
     *
     * @param ownershipFundingInfo URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addOwnershipFundingInfo(URL ownershipFundingInfo);

    /**
     * For a [[NewsMediaOrganization]], a statement on coverage priorities, including any public agenda or stance on issues.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    <T> List<T> getMissionCoveragePrioritiesPolicyList();

    /**
     * For a [[NewsMediaOrganization]], a statement on coverage priorities, including any public agenda or stance on issues.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    <T> T getMissionCoveragePrioritiesPolicy();

    /**
     * For a [[NewsMediaOrganization]], a statement on coverage priorities, including any public agenda or stance on issues.
     *
     * @param missionCoveragePrioritiesPolicy CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void addMissionCoveragePrioritiesPolicy(CreativeWork missionCoveragePrioritiesPolicy);
    /**
     * For a [[NewsMediaOrganization]], a statement on coverage priorities, including any public agenda or stance on issues.
     *
     * @param missionCoveragePrioritiesPolicy URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void addMissionCoveragePrioritiesPolicy(URL missionCoveragePrioritiesPolicy);

    /**
     * For a [[NewsMediaOrganization]] or other news-related [[Organization]], a statement about public engagement activities (for news media, the newsroom’s), including involving the public - digitally or otherwise -- in coverage decisions, reporting and activities after publication.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> List<T> getActionableFeedbackPolicyList();

    /**
     * For a [[NewsMediaOrganization]] or other news-related [[Organization]], a statement about public engagement activities (for news media, the newsroom’s), including involving the public - digitally or otherwise -- in coverage decisions, reporting and activities after publication.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> T getActionableFeedbackPolicy();

    /**
     * For a [[NewsMediaOrganization]] or other news-related [[Organization]], a statement about public engagement activities (for news media, the newsroom’s), including involving the public - digitally or otherwise -- in coverage decisions, reporting and activities after publication.
     *
     * @param actionableFeedbackPolicy CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addActionableFeedbackPolicy(CreativeWork actionableFeedbackPolicy);
    /**
     * For a [[NewsMediaOrganization]] or other news-related [[Organization]], a statement about public engagement activities (for news media, the newsroom’s), including involving the public - digitally or otherwise -- in coverage decisions, reporting and activities after publication.
     *
     * @param actionableFeedbackPolicy URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addActionableFeedbackPolicy(URL actionableFeedbackPolicy);

    /**
     * For a [[NewsMediaOrganization]] or other news-related [[Organization]], a statement explaining when authors of articles are not named in bylines.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1688">https://github.com/schemaorg/schemaorg/issues/1688</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    <T> List<T> getNoBylinesPolicyList();

    /**
     * For a [[NewsMediaOrganization]] or other news-related [[Organization]], a statement explaining when authors of articles are not named in bylines.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1688">https://github.com/schemaorg/schemaorg/issues/1688</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    <T> T getNoBylinesPolicy();

    /**
     * For a [[NewsMediaOrganization]] or other news-related [[Organization]], a statement explaining when authors of articles are not named in bylines.
     *
     * @param noBylinesPolicy CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1688">https://github.com/schemaorg/schemaorg/issues/1688</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void addNoBylinesPolicy(CreativeWork noBylinesPolicy);
    /**
     * For a [[NewsMediaOrganization]] or other news-related [[Organization]], a statement explaining when authors of articles are not named in bylines.
     *
     * @param noBylinesPolicy URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1688">https://github.com/schemaorg/schemaorg/issues/1688</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void addNoBylinesPolicy(URL noBylinesPolicy);

    /**
     * For a [[NewsMediaOrganization]], a link to the masthead page or a page listing top editorial management.
     *
     * @return {@link URL} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    <T> List<T> getMastheadList();

    /**
     * For a [[NewsMediaOrganization]], a link to the masthead page or a page listing top editorial management.
     *
     * @return {@link URL} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    <T> T getMasthead();

    /**
     * For a [[NewsMediaOrganization]], a link to the masthead page or a page listing top editorial management.
     *
     * @param masthead URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void addMasthead(URL masthead);
    /**
     * For a [[NewsMediaOrganization]], a link to the masthead page or a page listing top editorial management.
     *
     * @param masthead CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void addMasthead(CreativeWork masthead);

    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a report on staffing diversity issues. In a news context this might be for example ASNE or RTDNA (US) reports, or self-reported.
     *
     * @return {@link Article} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> List<T> getDiversityStaffingReportList();

    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a report on staffing diversity issues. In a news context this might be for example ASNE or RTDNA (US) reports, or self-reported.
     *
     * @return {@link Article} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> T getDiversityStaffingReport();

    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a report on staffing diversity issues. In a news context this might be for example ASNE or RTDNA (US) reports, or self-reported.
     *
     * @param diversityStaffingReport Article value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addDiversityStaffingReport(Article diversityStaffingReport);
    /**
     * For an [[Organization]] (often but not necessarily a [[NewsMediaOrganization]]), a report on staffing diversity issues. In a news context this might be for example ASNE or RTDNA (US) reports, or self-reported.
     *
     * @param diversityStaffingReport URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addDiversityStaffingReport(URL diversityStaffingReport);

    /**
     * For an [[Organization]] (typically a [[NewsMediaOrganization]]), a statement about policy on use of unnamed sources and the decision process required.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> List<T> getUnnamedSourcesPolicyList();

    /**
     * For an [[Organization]] (typically a [[NewsMediaOrganization]]), a statement about policy on use of unnamed sources and the decision process required.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> T getUnnamedSourcesPolicy();

    /**
     * For an [[Organization]] (typically a [[NewsMediaOrganization]]), a statement about policy on use of unnamed sources and the decision process required.
     *
     * @param unnamedSourcesPolicy CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addUnnamedSourcesPolicy(CreativeWork unnamedSourcesPolicy);
    /**
     * For an [[Organization]] (typically a [[NewsMediaOrganization]]), a statement about policy on use of unnamed sources and the decision process required.
     *
     * @param unnamedSourcesPolicy URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addUnnamedSourcesPolicy(URL unnamedSourcesPolicy);

    /**
     * Statement on diversity policy by an [[Organization]] e.g. a [[NewsMediaOrganization]]. For a [[NewsMediaOrganization]], a statement describing the newsroom’s diversity policy on both staffing and sources, typically providing staffing data.
     *
     * @return {@link URL} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> List<T> getDiversityPolicyList();

    /**
     * Statement on diversity policy by an [[Organization]] e.g. a [[NewsMediaOrganization]]. For a [[NewsMediaOrganization]], a statement describing the newsroom’s diversity policy on both staffing and sources, typically providing staffing data.
     *
     * @return {@link URL} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> T getDiversityPolicy();

    /**
     * Statement on diversity policy by an [[Organization]] e.g. a [[NewsMediaOrganization]]. For a [[NewsMediaOrganization]], a statement describing the newsroom’s diversity policy on both staffing and sources, typically providing staffing data.
     *
     * @param diversityPolicy URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addDiversityPolicy(URL diversityPolicy);
    /**
     * Statement on diversity policy by an [[Organization]] e.g. a [[NewsMediaOrganization]]. For a [[NewsMediaOrganization]], a statement describing the newsroom’s diversity policy on both staffing and sources, typically providing staffing data.
     *
     * @param diversityPolicy CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addDiversityPolicy(CreativeWork diversityPolicy);

    /**
     * Disclosure about verification and fact-checking processes for a [[NewsMediaOrganization]] or other fact-checking [[Organization]].
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    <T> List<T> getVerificationFactCheckingPolicyList();

    /**
     * Disclosure about verification and fact-checking processes for a [[NewsMediaOrganization]] or other fact-checking [[Organization]].
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    <T> T getVerificationFactCheckingPolicy();

    /**
     * Disclosure about verification and fact-checking processes for a [[NewsMediaOrganization]] or other fact-checking [[Organization]].
     *
     * @param verificationFactCheckingPolicy CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void addVerificationFactCheckingPolicy(CreativeWork verificationFactCheckingPolicy);
    /**
     * Disclosure about verification and fact-checking processes for a [[NewsMediaOrganization]] or other fact-checking [[Organization]].
     *
     * @param verificationFactCheckingPolicy URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     */
    void addVerificationFactCheckingPolicy(URL verificationFactCheckingPolicy);

    /**
     * Statement about ethics policy, e.g. of a [[NewsMediaOrganization]] regarding journalistic and publishing practices, or of a [[Restaurant]], a page describing food source policies. In the case of a [[NewsMediaOrganization]], an ethicsPolicy is typically a statement describing the personal, organizational, and corporate standards of behavior expected by the organization.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> List<T> getEthicsPolicyList();

    /**
     * Statement about ethics policy, e.g. of a [[NewsMediaOrganization]] regarding journalistic and publishing practices, or of a [[Restaurant]], a page describing food source policies. In the case of a [[NewsMediaOrganization]], an ethicsPolicy is typically a statement describing the personal, organizational, and corporate standards of behavior expected by the organization.
     *
     * @return {@link CreativeWork} or {@link URL}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> T getEthicsPolicy();

    /**
     * Statement about ethics policy, e.g. of a [[NewsMediaOrganization]] regarding journalistic and publishing practices, or of a [[Restaurant]], a page describing food source policies. In the case of a [[NewsMediaOrganization]], an ethicsPolicy is typically a statement describing the personal, organizational, and corporate standards of behavior expected by the organization.
     *
     * @param ethicsPolicy CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addEthicsPolicy(CreativeWork ethicsPolicy);
    /**
     * Statement about ethics policy, e.g. of a [[NewsMediaOrganization]] regarding journalistic and publishing practices, or of a [[Restaurant]], a page describing food source policies. In the case of a [[NewsMediaOrganization]], an ethicsPolicy is typically a statement describing the personal, organizational, and corporate standards of behavior expected by the organization.
     *
     * @param ethicsPolicy URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addEthicsPolicy(URL ethicsPolicy);

    /**
     * For an [[Organization]] (e.g. [[NewsMediaOrganization]]), a statement describing (in news media, the newsroom’s) disclosure and correction policy for errors.
     *
     * @return {@link URL} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> List<T> getCorrectionsPolicyList();

    /**
     * For an [[Organization]] (e.g. [[NewsMediaOrganization]]), a statement describing (in news media, the newsroom’s) disclosure and correction policy for errors.
     *
     * @return {@link URL} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    <T> T getCorrectionsPolicy();

    /**
     * For an [[Organization]] (e.g. [[NewsMediaOrganization]]), a statement describing (in news media, the newsroom’s) disclosure and correction policy for errors.
     *
     * @param correctionsPolicy URL value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addCorrectionsPolicy(URL correctionsPolicy);
    /**
     * For an [[Organization]] (e.g. [[NewsMediaOrganization]]), a statement describing (in news media, the newsroom’s) disclosure and correction policy for errors.
     *
     * @param correctionsPolicy CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1525">https://github.com/schemaorg/schemaorg/issues/1525</a>
     */
    void addCorrectionsPolicy(CreativeWork correctionsPolicy);
}
