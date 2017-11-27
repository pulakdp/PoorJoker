package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Code author: PulakDebasish
 */
public class PJRetrieverTest extends AndroidTestCase{

    public void testReturnsNonEmptyString() {
        String result = null;
        PJRetriever pjRetriever = new PJRetriever();
        pjRetriever.execute(getContext());
        try {
            result = pjRetriever.get();
        }catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
        assertNotSame("", result);
    }
}