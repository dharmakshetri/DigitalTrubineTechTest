package co.digitaltrubinetechtest.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Dharma Kshetri on 2/7/17.
 */
/*@Root(name = "ads", strict = false)
public class Ads {

    @ElementList (inline = true)
    List<Ad> adList;

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }
}*/

@Root
public class Ads {

    /*@ElementList(entry="ad")
    private Ad[] ads;

    public Ad[] getAds() {
        return ads;
    }*/

    @Element
    private String responseTime;

    @Element
    private String serverId;

    @Element
    private int totalCampaignsRequested;

    @ElementList(inline = true)
    List<Ad> adList;

    @Element
    private  String version;

    public List<Ad> getAdList() {
        return adList;
    }
}