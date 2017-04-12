package com.vogella.android.test.juntexamples.hamcresttests;

import com.vogella.android.test.juntexamples.DataService;
import com.vogella.android.test.juntexamples.model.TolkienCharacter;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static com.vogella.android.test.juntexamples.model.Race.HOBBIT;
import static com.vogella.android.test.juntexamples.model.Race.ORC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


/**
 * Created by vogella on 19.06.16.
 */

public class HamcrestTests {


    @Test
    public void validateTolkeinCharactorsInitializationWorks() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        //age is 33
        //name is "Frodo"
        //name is not "Frodon"
        assertThat(frodo.getName(),is("Frodo"));
        assertThat(frodo.age,is(33));
        assertThat(frodo.getName(), is(not("Frodon")));
    }

    @Test
    public void ensureThatFellowsAreNotOrcs() {
        List<TolkienCharacter> fellowship = new DataService().getFellowship();
        // ensure that not any of the fellows is a orc

        for (TolkienCharacter t: fellowship) {
            assertThat(t.getRace(), not(is(ORC)));
        }
        assertThat(fellowship, everyItem(hasProperty("race",is(not(ORC))) ));
    }

    @Test
    public void fellowShipOfTheRingShouldContainer7() {
        assertThat("Gandalf", length(is(8)));
    }
    public static  Matcher<String> length(Matcher<? super Integer> matcher) {
        return new FeatureMatcher<String, Integer>(matcher, "a String of length that", "length") {
            @Override
            protected Integer featureValueOf(String actual) {
                return actual.length();
            }
        };
    }

}
