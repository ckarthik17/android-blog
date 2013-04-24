package com.ckarthik.blog;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Button;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLocationManager;

import static android.location.LocationManager.NETWORK_PROVIDER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
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
    public void shouldReturnTheLatestLocation() {
        LocationManager locationManager = (LocationManager) Robolectric.application.getSystemService(Context.LOCATION_SERVICE);
        ShadowLocationManager shadowLocationManager = shadowOf(locationManager);
        Location expectedLocation = location(NETWORK_PROVIDER, 12.0, 20.0);

        shadowLocationManager.simulateLocation(expectedLocation);
        Location actualLocation = mainActivity.latestLocation();

        assertEquals(expectedLocation, actualLocation);
    }

    private Location location(String provider, double latitude, double longitude) {
        Location location = new Location(provider);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setTime(System.currentTimeMillis());
        return location;
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
