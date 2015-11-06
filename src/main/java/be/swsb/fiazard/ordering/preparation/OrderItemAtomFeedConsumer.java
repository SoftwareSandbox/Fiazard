package be.swsb.fiazard.ordering.preparation;

import com.github.nordinh.atomfeed.core.AtomFeedBookmark;
import com.github.nordinh.atomfeed.core.AtomFeedConsumer;
import com.github.nordinh.atomfeed.core.ConcurrentInMemoryAtomFeedBookmark;
import org.jboss.resteasy.plugins.providers.atom.Entry;

public class OrderItemAtomFeedConsumer extends AtomFeedConsumer{

    public OrderItemAtomFeedConsumer(AtomFeedBookmark atomFeedBookmark) {
        super(atomFeedBookmark);
    }

    @Override
    public String getURL() {
        return "http://127.0.0.1:2113/streams/fiazard";
    }

    @Override
    public void consumeEntry(Entry entry) {
        entry.getLinks();
    }

    public static void main(String[] args) {
        new OrderItemAtomFeedConsumer(new ConcurrentInMemoryAtomFeedBookmark()).start();
    }
}
