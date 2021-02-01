package filter;

import model.Item;
import java.util.List;
/**
 * This interface describe filters method implementation.
 */
public interface FilterImpl {
    /**
     * The method contain id for filter
     */
    Long getIdLong();
    /**
     * The method contain description of filter.
     */
    String getDescription();
    /**
     * The method filter items.
     * @param passed Input List to process.
     * @return Returns the list that has been filtered.
     */
    List<Item> execute(List<Item> passed);
}
