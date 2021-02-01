package filter.app;

import filter.FilterImpl;
import model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 01.02.2021
 * email roman9628@gmail.com
 * The class describe filter for main page.
 */
public class ThisDay implements FilterImpl {
    /**
     * The instance with logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ThisDay.class.getName());
    /**
     * The marker for logger.
     */
    private static final Marker MARKER = MarkerFactory.getMarker("ThisDay");

    /**
     * The method contain id for filter
     */
    @Override
    public Long getIdLong() {
        return 1L;
    }

    /**
     * The method contain description of filter.
     */
    @Override
    public String getDescription() {
        return "за последний день";
    }

    /**
     * The method filter items.
     *
     * @param passed Input List to process.
     * @return Returns the list that has been filtered.
     */
    @Override
    public List<Item> execute(List<Item> passed) {
        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String currDate = formatter.format(date);
        ArrayList<Item> itemsToRemove = new ArrayList<>();
        for (Item item : passed) {
            String itemCreateDate = item.getDateCreateItem().toString();
            if(!itemCreateDate.equals(currDate)) {
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