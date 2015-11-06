package be.swsb.fiazard.eventstore;


import org.apache.abdera.Abdera;
import org.apache.abdera.model.*;
import org.apache.abdera.parser.Parser;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static be.swsb.fiazard.eventstore.LinkRelation.*;
import static java.lang.Thread.sleep;

public class AtomPoller {

    private Link getLastLink(String url) throws IOException {
        Feed feed = getFeed(url);
        Link last = feed.getLink(LAST.getValue());

        return last == null ? feed.getLink(SELF.getValue()) : last;
    }

    private Link readPrevious(Link link, EventHandler handler) throws IOException {
        Feed feed = getFeed(link.getHref().toString());
        List<Entry> entries = new ArrayList(feed.getEntries());
        Collections.reverse(entries);
        for (Entry entry : entries) {
            Entry detail = getEntry(getAlternateEntryLink(entry));
            handler.handle(detail.getContentElement());
        }

        Link previous = feed.getLink(PREVIOUS.getValue());

        return previous == null ? link : previous;
    }

    private String getAlternateEntryLink(Entry entry) {
        return entry.getLink(ALTERNATE.getValue()).getHref().toString();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new AtomPoller().startPolling(new ConsoleOutputEventHandler());
    }

    private void startPolling(EventHandler handler) {
        try {
            Link last = null;

            while (last == null) {
                last = getLastLink("http://127.0.0.1:2113/streams/fiazard");
                if (last == null) {
                    sleep(1000);
                }
            }

            while (true) {
                Link current = readPrevious(last, handler);
                if (current.getHref() == last.getHref()) {
                    sleep(1000);
                }
                last = current;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Feed getFeed(String url) throws IOException {
        return getElement(url);
    }

    private Entry getEntry(String url) throws IOException {
        return getElement(url);
    }

    private <T extends Element> T getElement(String url) throws IOException {
        String username = "admin";
        String password = "changeit";
        URLConnection uc = new URL(url).openConnection();
        String userpass = username + ":" + password;
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
        uc.setRequestProperty("Authorization", basicAuth);
        uc.setRequestProperty("Accept", "application/atom+xml");

        Abdera abdera = new Abdera();
        Parser parser = abdera.getParser();
        Document<T> doc = parser.parse(uc.getInputStream(), url);

        return doc.getRoot();
    }

}