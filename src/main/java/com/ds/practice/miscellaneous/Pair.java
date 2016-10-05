package com.ds.practice.miscellaneous;

/**
 * Created by Kishore Routhu on 5/10/16 4:28 PM.
 */
public class Pair<T1, T2> {

    private T1 v1;
    private T2 v2;

    public Pair(T1 v1, T2 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public T1 getFirst() {
        return v1;
    }

    public T2 getSecond() {
        return v2;
    }


}
