package com.ckarthik.blog;

import android.telephony.SmsManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowSmsManager;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SMSTestActivityTest {
    private SMSTestActivity smsTestActivity;

    @Before
    public void setup() {
        smsTestActivity = new SMSTestActivity();
        smsTestActivity.onCreate(null);
    }

    @Test
    public void shouldSendSMSToTheGivenPhoneNumber() {
        String message = "Android is cool";
        String phoneNumber = "123-123-1222";

        smsTestActivity.sendSMS(phoneNumber, message);

        ShadowSmsManager shadowSmsManager = shadowOf(SmsManager.getDefault());
        ShadowSmsManager.TextSmsParams lastSentTextMessageParams = shadowSmsManager.getLastSentTextMessageParams();

        assertEquals(phoneNumber, lastSentTextMessageParams.getDestinationAddress());
        assertEquals(message, lastSentTextMessageParams.getText());
    }

}
