/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.onehanded;

import static org.junit.Assert.assertEquals;

import androidx.test.filters.SmallTest;

import com.android.internal.logging.UiEventLogger;
import com.android.internal.logging.testing.UiEventLoggerFake;
import com.android.systemui.SysuiTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@SmallTest
public class OneHandedEventsTest extends SysuiTestCase {

    private UiEventLoggerFake mUiEventLogger;

    @Parameterized.Parameter
    public int mTag;

    @Parameterized.Parameter(1)
    public String mExpectedMessage;

    public UiEventLogger.UiEventEnum mUiEvent;

    @Before
    public void setFakeLoggers() {
        mUiEventLogger = new UiEventLoggerFake();
        OneHandedEvents.sUiEventLogger = mUiEventLogger;
    }

    @Test
    public void testLogEvent() {
        if (mUiEvent != null) {
            assertEquals(1, mUiEventLogger.numLogs());
            assertEquals(mUiEvent.getId(), mUiEventLogger.eventId(0));
        }
    }

    @Parameterized.Parameters(name = "{index}: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Triggers
                {OneHandedEvents.EVENT_ONE_HANDED_TRIGGER_GESTURE_IN,
                        "writeEvent one_handed_trigger_gesture_in"},
                {OneHandedEvents.EVENT_ONE_HANDED_TRIGGER_GESTURE_OUT,
                        "writeEvent one_handed_trigger_gesture_out"},
                {OneHandedEvents.EVENT_ONE_HANDED_TRIGGER_OVERSPACE_OUT,
                        "writeEvent one_handed_trigger_overspace_out"},
                {OneHandedEvents.EVENT_ONE_HANDED_TRIGGER_POP_IME_OUT,
                        "writeEvent one_handed_trigger_pop_ime_out"},
                {OneHandedEvents.EVENT_ONE_HANDED_TRIGGER_ROTATION_OUT,
                        "writeEvent one_handed_trigger_rotation_out"},
                {OneHandedEvents.EVENT_ONE_HANDED_TRIGGER_APP_TAPS_OUT,
                        "writeEvent one_handed_trigger_app_taps_out"},
                {OneHandedEvents.EVENT_ONE_HANDED_TRIGGER_TIMEOUT_OUT,
                        "writeEvent one_handed_trigger_timeout_out"},
                // Settings toggles
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_ENABLED_ON,
                        "writeEvent one_handed_settings_enabled_on"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_ENABLED_OFF,
                        "writeEvent one_handed_settings_enabled_off"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_APP_TAPS_EXIT_ON,
                        "writeEvent one_handed_settings_app_taps_exit_on"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_APP_TAPS_EXIT_OFF,
                        "writeEvent one_handed_settings_app_taps_exit_off"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_TIMEOUT_EXIT_ON,
                        "writeEvent one_handed_settings_timeout_exit_on"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_TIMEOUT_EXIT_OFF,
                        "writeEvent one_handed_settings_timeout_exit_off"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_TIMEOUT_SECONDS_NEVER,
                        "writeEvent one_handed_settings_timeout_seconds_never"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_TIMEOUT_SECONDS_4,
                        "writeEvent one_handed_settings_timeout_seconds_4"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_TIMEOUT_SECONDS_8,
                        "writeEvent one_handed_settings_timeout_seconds_8"},
                {OneHandedEvents.EVENT_ONE_HANDED_SETTINGS_TIMEOUT_SECONDS_12,
                        "writeEvent one_handed_settings_timeout_seconds_12"}
        });
    }
}
