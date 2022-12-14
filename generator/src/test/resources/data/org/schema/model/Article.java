/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package org.schema.model;

import org.schema.model.datatype.Text;
import org.schema.model.datatype.Integer;
import org.schema.model.CreativeWork;
import org.schema.model.datatype.URL;
import org.schema.model.SpeakableSpecification;

/**
 * An article, such as a news article or piece of investigative report. Newspapers and magazines have articles of many different types and this is intended to cover them all.<br/><br/>See also [blog post](http://blog.schema.org/2014/09/schemaorg-support-for-bibliographic_2.html).
 *
 * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews</a>
 * @see <a href="https://schema.org/Article">https://schema.org/Article</a>
 */
public interface Article extends CreativeWork {

    /**
     * The page on which the work starts; for example "135" or "xiii".
     *
     * @return {@link Text} or {@link Integer}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    <T> T getPageStart();

    /**
     * The page on which the work starts; for example "135" or "xiii".
     *
     * @param pageStart Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    void setPageStart(Text pageStart);
    /**
     * The page on which the work starts; for example "135" or "xiii".
     *
     * @param pageStart Integer value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    void setPageStart(Integer pageStart);

    /**
     * Articles may belong to one or more 'sections' in a magazine or newspaper, such as Sports, Lifestyle, etc.
     *
     * @return {@link Text}
     */
    Text getArticleSection();

    /**
     * Articles may belong to one or more 'sections' in a magazine or newspaper, such as Sports, Lifestyle, etc.
     *
     * @param articleSection Text value to set.
     */
    void setArticleSection(Text articleSection);

    /**
     * The page on which the work ends; for example "138" or "xvi".
     *
     * @return {@link Text} or {@link Integer}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    <T> T getPageEnd();

    /**
     * The page on which the work ends; for example "138" or "xvi".
     *
     * @param pageEnd Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    void setPageEnd(Text pageEnd);
    /**
     * The page on which the work ends; for example "138" or "xvi".
     *
     * @param pageEnd Integer value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    void setPageEnd(Integer pageEnd);

    /**
     * For an [[Article]], typically a [[NewsArticle]], the backstory property provides a textual summary giving a brief explanation of why and how an article was created. In a journalistic setting this could include information about reporting process, methods, interviews, data sources, etc.
     *
     * @return {@link Text} or {@link CreativeWork}
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1688">https://github.com/schemaorg/schemaorg/issues/1688</a>
     */
    <T> T getBackstory();

    /**
     * For an [[Article]], typically a [[NewsArticle]], the backstory property provides a textual summary giving a brief explanation of why and how an article was created. In a journalistic setting this could include information about reporting process, methods, interviews, data sources, etc.
     *
     * @param backstory Text value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1688">https://github.com/schemaorg/schemaorg/issues/1688</a>
     */
    void setBackstory(Text backstory);
    /**
     * For an [[Article]], typically a [[NewsArticle]], the backstory property provides a textual summary giving a brief explanation of why and how an article was created. In a journalistic setting this could include information about reporting process, methods, interviews, data sources, etc.
     *
     * @param backstory CreativeWork value to set.
     * @see <a href="https://pending.schema.org">https://pending.schema.org</a>
     * @see <a href="https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP">https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP</a>
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1688">https://github.com/schemaorg/schemaorg/issues/1688</a>
     */
    void setBackstory(CreativeWork backstory);

    /**
     * The number of words in the text of the Article.
     *
     * @return {@link Integer}
     */
    Integer getWordCount();

    /**
     * The number of words in the text of the Article.
     *
     * @param wordCount Integer value to set.
     */
    void setWordCount(Integer wordCount);

    /**
     * The actual body of the article.
     *
     * @return {@link Text}
     */
    Text getArticleBody();

    /**
     * The actual body of the article.
     *
     * @param articleBody Text value to set.
     */
    void setArticleBody(Text articleBody);

    /**
     * Indicates sections of a Web page that are particularly 'speakable' in the sense of being highlighted as being especially appropriate for text-to-speech conversion. Other sections of a page may also be usefully spoken in particular circumstances; the 'speakable' property serves to indicate the parts most likely to be generally useful for speech.
     * 
     * The *speakable* property can be repeated an arbitrary number of times, with three kinds of possible 'content-locator' values:
     * 
     * 1.) *id-value* URL references - uses *id-value* of an element in the page being annotated. The simplest use of *speakable* has (potentially relative) URL values, referencing identified sections of the document concerned.
     * 
     * 2.) CSS Selectors - addresses content in the annotated page, eg. via class attribute. Use the [[cssSelector]] property.
     * 
     * 3.)  XPaths - addresses content via XPaths (assuming an XML view of the content). Use the [[xpath]] property.
     * 
     * 
     * For more sophisticated markup of speakable sections beyond simple ID references, either CSS selectors or XPath expressions to pick out document section(s) as speakable. For this
     * we define a supporting type, [[SpeakableSpecification]]  which is defined to be a possible value of the *speakable* property.
     *          
     *
     * @return {@link URL} or {@link SpeakableSpecification}
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1389">https://github.com/schemaorg/schemaorg/issues/1389</a>
     */
    <T> T getSpeakable();

    /**
     * Indicates sections of a Web page that are particularly 'speakable' in the sense of being highlighted as being especially appropriate for text-to-speech conversion. Other sections of a page may also be usefully spoken in particular circumstances; the 'speakable' property serves to indicate the parts most likely to be generally useful for speech.
     * 
     * The *speakable* property can be repeated an arbitrary number of times, with three kinds of possible 'content-locator' values:
     * 
     * 1.) *id-value* URL references - uses *id-value* of an element in the page being annotated. The simplest use of *speakable* has (potentially relative) URL values, referencing identified sections of the document concerned.
     * 
     * 2.) CSS Selectors - addresses content in the annotated page, eg. via class attribute. Use the [[cssSelector]] property.
     * 
     * 3.)  XPaths - addresses content via XPaths (assuming an XML view of the content). Use the [[xpath]] property.
     * 
     * 
     * For more sophisticated markup of speakable sections beyond simple ID references, either CSS selectors or XPath expressions to pick out document section(s) as speakable. For this
     * we define a supporting type, [[SpeakableSpecification]]  which is defined to be a possible value of the *speakable* property.
     *          
     *
     * @param speakable URL value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1389">https://github.com/schemaorg/schemaorg/issues/1389</a>
     */
    void setSpeakable(URL speakable);
    /**
     * Indicates sections of a Web page that are particularly 'speakable' in the sense of being highlighted as being especially appropriate for text-to-speech conversion. Other sections of a page may also be usefully spoken in particular circumstances; the 'speakable' property serves to indicate the parts most likely to be generally useful for speech.
     * 
     * The *speakable* property can be repeated an arbitrary number of times, with three kinds of possible 'content-locator' values:
     * 
     * 1.) *id-value* URL references - uses *id-value* of an element in the page being annotated. The simplest use of *speakable* has (potentially relative) URL values, referencing identified sections of the document concerned.
     * 
     * 2.) CSS Selectors - addresses content in the annotated page, eg. via class attribute. Use the [[cssSelector]] property.
     * 
     * 3.)  XPaths - addresses content via XPaths (assuming an XML view of the content). Use the [[xpath]] property.
     * 
     * 
     * For more sophisticated markup of speakable sections beyond simple ID references, either CSS selectors or XPath expressions to pick out document section(s) as speakable. For this
     * we define a supporting type, [[SpeakableSpecification]]  which is defined to be a possible value of the *speakable* property.
     *          
     *
     * @param speakable SpeakableSpecification value to set.
     * @see <a href="https://github.com/schemaorg/schemaorg/issues/1389">https://github.com/schemaorg/schemaorg/issues/1389</a>
     */
    void setSpeakable(SpeakableSpecification speakable);

    /**
     * Any description of pages that is not separated into pageStart and pageEnd; for example, "1-6, 9, 55" or "10-12, 46-49".
     *
     * @return {@link Text}
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    Text getPagination();

    /**
     * Any description of pages that is not separated into pageStart and pageEnd; for example, "1-6, 9, 55" or "10-12, 46-49".
     *
     * @param pagination Text value to set.
     * @see <a href="http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex">http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex</a>
     */
    void setPagination(Text pagination);
}
