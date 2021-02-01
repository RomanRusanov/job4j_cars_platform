package filter.app;

import filter.FilterImpl;
import model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 01.02.2021
 * email roman9628@gmail.com
 * The class describe filter what pass item with photo.
 */
public class WithPhoto implements FilterImpl {
    /**
     * The instance with logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(WithPhoto.class.getName());
    /**
     * The marker for logger.
     */
    private static final Marker MARKER = MarkerFactory.getMarker("WithPhoto");
    /**
     * The method contain id for filter
     */
    @Override
    public Long getIdLong() {
        return 2L;
    }

    /**
     * The method contain description of filter.
     */
    @Override
    public String getDescription() {
        return "есть фото";
    }

    /**
     * The method filter items.
     *
     * @param passed Input List to process.
     * @return Returns the list that has been filtered.
     */
    @Override
    public List<Item> execute(List<Item> passed) {
        ArrayList<Item> itemsToRemove = new ArrayList<>();
        for (Item item : passed) {
            String itemCreateDate = item.getDateCreateItem().toString();
            if(item.getImages().size() == 0) {
                itemsToRemove.add(item);
            }
        }
        for (Item item : itemsToRemove) {
            passed.remove(item);
        }
        LOG.info(MARKER, "Server main page applied filter : {}", this.getDescription());
        return passed;
    }
}