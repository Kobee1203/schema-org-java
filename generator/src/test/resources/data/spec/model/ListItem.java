/*
 * This class is auto generated by the schema.org code generator program.
 * https://github.com/Kobee1203/schema-org-java
 * Do not edit the class manually.
 */
package spec.model;

import java.util.List;
import spec.model.Thing;
import spec.model.ListItem;
import spec.model.datatype.Text;
import spec.model.datatype.Integer;

/**
 * An list item, e.g. a step in a checklist or how-to description.
 *
 * @see <a href="https://schema.org/ListItem">https://schema.org/ListItem</a>
 */
public interface ListItem extends Intangible {

    /**
     * An entity represented by an entry in a list or data feed (e.g. an 'artist' in a list of 'artists').
     *
     * @return {@link Thing}
     */
    List<Thing> getItemList();

    /**
     * An entity represented by an entry in a list or data feed (e.g. an 'artist' in a list of 'artists').
     *
     * @return {@link Thing}
     */
    Thing getItem();

    /**
     * An entity represented by an entry in a list or data feed (e.g. an 'artist' in a list of 'artists').
     *
     * @param item Thing value to set.
     */
    void addItem(Thing item);

    /**
     * A link to the ListItem that follows the current one.
     *
     * @return {@link ListItem}
     */
    List<ListItem> getNextItemList();

    /**
     * A link to the ListItem that follows the current one.
     *
     * @return {@link ListItem}
     */
    ListItem getNextItem();

    /**
     * A link to the ListItem that follows the current one.
     *
     * @param nextItem ListItem value to set.
     */
    void addNextItem(ListItem nextItem);

    /**
     * A link to the ListItem that precedes the current one.
     *
     * @return {@link ListItem}
     */
    List<ListItem> getPreviousItemList();

    /**
     * A link to the ListItem that precedes the current one.
     *
     * @return {@link ListItem}
     */
    ListItem getPreviousItem();

    /**
     * A link to the ListItem that precedes the current one.
     *
     * @param previousItem ListItem value to set.
     */
    void addPreviousItem(ListItem previousItem);

    /**
     * The position of an item in a series or sequence of items.
     *
     * @return {@link Text} or {@link Integer}
     */
    <T> List<T> getPositionList();

    /**
     * The position of an item in a series or sequence of items.
     *
     * @return {@link Text} or {@link Integer}
     */
    <T> T getPosition();

    /**
     * The position of an item in a series or sequence of items.
     *
     * @param position Text value to set.
     */
    void addPosition(Text position);
    /**
     * The position of an item in a series or sequence of items.
     *
     * @param position Integer value to set.
     */
    void addPosition(Integer position);
}
