package listeners;
//ID: 318720067
/**
 * The HitNotifier interface.
 * @author Or Spiegel
 * @version 1.0
 * @since 2.6.2021
 */

public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl = hit listener.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl = the hit listener.
     */
    void removeHitListener(HitListener hl);
}
