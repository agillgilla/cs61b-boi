package lab9;

/**
 * Created by Arjun on 3/17/2017.
 */
public interface MapEntry<Key,Val> {
    /** The key part of THIS. */
    Key getKey();
    /** The value part of THIS. */
    Val getValue();
    /** Cause getValue() to become VAL, returning the previous value. */
    Val setValue(Val val);
    /** True iff E is a Map.Entry and both represent the same (key,value)
     * pair (i.e., keys are both null, or are .equal, and likewise for
     * values).
     boolean equals(Object e);
     /** An integer hash value that depends only on the hashCode values
     * of getKey() and getValue() according to the formula:
     * (getKey() == null ? 0 : getKey().hashCode ())
     * ^ (getValue() == null ? 0 : getValue.hashCode ()) */
    int hashCode();
}
