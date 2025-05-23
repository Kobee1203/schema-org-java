/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.datatype.Text;

/**
 * A NewsArticle is an article whose content reports news, or provides background context and supporting materials for understanding the news.
 * 
 * A more detailed overview of [schema.org News markup](/docs/news.html) is also available.
 *
 * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP-draws">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP-draws</a>
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews</a>
 * @see <a href="https://schema.org/NewsArticle">https://schema.org/NewsArticle</a>
 */
public interface NewsArticle extends Article {

    /**
     * The number of the column in which the NewsArticle appears in the print edition.
     *
     * @return {@link Text}
     */
    List<Text> getPrintColumnList();

    /**
     * The number of the column in which the NewsArticle appears in the print edition.
     *
     * @return {@link Text}
     */
    Text getPrintColumn();

    /**
     * The number of the column in which the NewsArticle appears in the print edition.
     *
     * @param printColumn Text value to set.
     */
    void addPrintColumn(Text printColumn);

    /**
     * The edition of the print product in which the NewsArticle appears.
     *
     * @return {@link Text}
     */
    List<Text> getPrintEditionList();

    /**
     * The edition of the print product in which the NewsArticle appears.
     *
     * @return {@link Text}
     */
    Text getPrintEdition();

    /**
     * The edition of the print product in which the NewsArticle appears.
     *
     * @param printEdition Text value to set.
     */
    void addPrintEdition(Text printEdition);

    /**
     * If this NewsArticle appears in print, this field indicates the print section in which the article appeared.
     *
     * @return {@link Text}
     */
    List<Text> getPrintSectionList();

    /**
     * If this NewsArticle appears in print, this field indicates the print section in which the article appeared.
     *
     * @return {@link Text}
     */
    Text getPrintSection();

    /**
     * If this NewsArticle appears in print, this field indicates the print section in which the article appeared.
     *
     * @param printSection Text value to set.
     */
    void addPrintSection(Text printSection);

    /**
     * If this NewsArticle appears in print, this field indicates the name of the page on which the article is found. Please note that this field is intended for the exact page name (e.g. A5, B18).
     *
     * @return {@link Text}
     */
    List<Text> getPrintPageList();

    /**
     * If this NewsArticle appears in print, this field indicates the name of the page on which the article is found. Please note that this field is intended for the exact page name (e.g. A5, B18).
     *
     * @return {@link Text}
     */
    Text getPrintPage();

    /**
     * If this NewsArticle appears in print, this field indicates the name of the page on which the article is found. Please note that this field is intended for the exact page name (e.g. A5, B18).
     *
     * @param printPage Text value to set.
     */
    void addPrintPage(Text printPage);

    /**
     * A [dateline](https://en.wikipedia.org/wiki/Dateline) is a brief piece of text included in news articles that describes where and when the story was written or filed though the date is often omitted. Sometimes only a placename is provided.
     * 
     * Structured representations of dateline-related information can also be expressed more explicitly using [[locationCreated]] (which represents where a work was created, e.g. where a news report was written).  For location depicted or described in the content, use [[contentLocation]].
     * 
     * Dateline summaries are oriented more towards human readers than towards automated processing, and can vary substantially. Some examples: "BEIRUT, Lebanon, June 2.", "Paris, France", "December 19, 2017 11:43AM Reporting from Washington", "Beijing/Moscow", "QUEZON CITY, Philippines".
     *       
     *
     * @return {@link Text}
     */
    List<Text> getDatelineList();

    /**
     * A [dateline](https://en.wikipedia.org/wiki/Dateline) is a brief piece of text included in news articles that describes where and when the story was written or filed though the date is often omitted. Sometimes only a placename is provided.
     * 
     * Structured representations of dateline-related information can also be expressed more explicitly using [[locationCreated]] (which represents where a work was created, e.g. where a news report was written).  For location depicted or described in the content, use [[contentLocation]].
     * 
     * Dateline summaries are oriented more towards human readers than towards automated processing, and can vary substantially. Some examples: "BEIRUT, Lebanon, June 2.", "Paris, France", "December 19, 2017 11:43AM Reporting from Washington", "Beijing/Moscow", "QUEZON CITY, Philippines".
     *       
     *
     * @return {@link Text}
     */
    Text getDateline();

    /**
     * A [dateline](https://en.wikipedia.org/wiki/Dateline) is a brief piece of text included in news articles that describes where and when the story was written or filed though the date is often omitted. Sometimes only a placename is provided.
     * 
     * Structured representations of dateline-related information can also be expressed more explicitly using [[locationCreated]] (which represents where a work was created, e.g. where a news report was written).  For location depicted or described in the content, use [[contentLocation]].
     * 
     * Dateline summaries are oriented more towards human readers than towards automated processing, and can vary substantially. Some examples: "BEIRUT, Lebanon, June 2.", "Paris, France", "December 19, 2017 11:43AM Reporting from Washington", "Beijing/Moscow", "QUEZON CITY, Philippines".
     *       
     *
     * @param dateline Text value to set.
     */
    void addDateline(Text dateline);
}
