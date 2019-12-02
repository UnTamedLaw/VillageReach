package com.lawk.villagereach;

import android.content.Context;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import static java.security.AccessController.getContext;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testFormActivityLineItemEditable() {
        FormActivityLineItemEditable test = new FormActivityLineItemEditable("id",1,1, 1, null, null);
        test.setNotes("helo");
        assertThat(test.notes, equalTo("helo"));
    }
}
