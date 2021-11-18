package gamerun;
//ID: 318720067
/**
 * The Counter Class.
 *
 * @author Or Spiegel
 * @version 1.0
 * @since 2.6.2021
 */

public class Counter {
    private int counter;
    /**
     * Counter constructor.
     * Initializes the counter to 0.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * add number to current count.
     * @param number = the number to increase.
     */
    void increase(int number) {
        this.counter = this.counter + number;
    }
    /**
     * subtract number from current count.
     * @param number = the number to derease.
     */
    void decrease(int number) {
        this.counter = this.counter - number;
    }
    /**
     * Counter getter.
     * @return = the counter.
     */
    public int getCounter() {
        return this.counter;
    }
    /**
     * subtract number from current count.
     * @return = the value of the counter.
     */
    int getValue() {
        return this.counter;
    }

    /**
     * Value setter.
     * @param val = the val to set
     */
    public void setValue(int val) {
        this.counter = val;
    }
}
