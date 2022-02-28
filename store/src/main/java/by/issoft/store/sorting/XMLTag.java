package by.issoft.store.sorting;

public enum XMLTag {
    SORT, NAME, PRICE, RATE;

    public String toString() {
        return name().toLowerCase();
    }
}
