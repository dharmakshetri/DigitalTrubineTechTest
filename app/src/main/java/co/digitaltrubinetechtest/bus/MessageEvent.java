package co.digitaltrubinetechtest.bus;

import co.digitaltrubinetechtest.model.Ad;

/**
 * Created by Dharma kshetri on 2/7/17.
 */

public class MessageEvent {
    public final Ad ad;

    public MessageEvent(Ad ad) {
        this.ad = ad;
    }
}
