package webalertmisc;

import com.google.gson.internal.LinkedTreeMap;
import org.apache.http.protocol.RequestUserAgent;

import java.util.AbstractMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents an instance of a page chron job configuration.
 * Objects of this type will be instantiated from a json based object.
 */
public class PageConfiguration {
    String name;
    String address;
    String userAgent;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUserAgent() {
        return userAgent;
    }
}

