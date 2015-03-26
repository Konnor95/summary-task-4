package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Extract;

/**
 * Reader class.
 *
 * @author Dmitry Bekuzarov
 */
public class Reader extends User {

    @Extract("subscription")
    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
