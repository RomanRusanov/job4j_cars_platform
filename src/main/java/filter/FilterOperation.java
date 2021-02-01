package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 01.02.2021
 * email roman9628@gmail.com
 * The class .
 */
public class FilterOperation {
    /**
     * The instance with logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FilterOperation.class.getName());
    /**
     * The marker for logger.
     */
    private static final Marker MARKER = MarkerFactory.getMarker("FilterOperation");
    /**
     * The filed contain all filters.
     */
    private Map<Long, FilterImpl> allFilters = new HashMap<>();

    /**
     * The method add filter to collection.
     * @param filter Filter instance.
     */
    public void addFilter(FilterImpl filter) {
        this.allFilters.put(filter.getIdLong(), filter);
    }

    /**
     * The method get filter by passed id.
     * @param id Long id.
     * @return Filter implementations.
     */
    public FilterImpl getFilterById(Long id) {
        return allFilters.get(id);
    }

    /**
     * The getter method.
     * @return Map with all filters.
     */
    public Map<Long, FilterImpl> getAllFilters() {
        return this.allFilters;
    }
}