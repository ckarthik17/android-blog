package com.ckarthik.blog;

import android.content.Intent;
import android.widget.Button;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity mainActivity;
    private Button button;

    @Before
    public void setUp() {
        mainActivity = new MainActivity();
        mainActivity.onCreate(null);
        button = (Button) mainActivity.findViewById(R.id.open_button);
    }

    @Test
    public void shouldStartTheSecondActivityOnButtonClick() throws Exception {
        ShadowActivity shadowActivity = shadowOf(mainActivity);

        button.performClick();

        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertThat(startedIntent.getComponent().getClassName(),
                equalTo(SecondActivity.class.getName()));
    }
}
