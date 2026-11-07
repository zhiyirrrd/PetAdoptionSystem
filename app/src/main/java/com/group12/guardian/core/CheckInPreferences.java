package com.group12.guardian.core;

import android.content.Context;
import android.content.SharedPreferences;

public class CheckInPreferences {
    private static final String PREF_NAME = "check_in_prefs";
    private static final String KEY_INTERVAL_HOURS = "interval_hours";
    private static final String KEY_NEXT_DUE_TIME = "next_due_time";
    private static final String KEY_REMINDER_DEADLINE = "reminder_deadline";
    private static final String KEY_WARNING_DEADLINE = "warning_deadline";
    private static final String KEY_CURRENT_PHASE = "current_phase";

    private final SharedPreferences prefs;

    public CheckInPreferences(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public int getIntervalHours() {
        return prefs.getInt(KEY_INTERVAL_HOURS, 12);
    }

    public long getNextDueTime() {
        return prefs.getLong(KEY_NEXT_DUE_TIME, 0L);
    }

    public long getReminderDeadline() {
        return prefs.getLong(KEY_REMINDER_DEADLINE, 0L);
    }

    public long getWarningDeadline() {
        return prefs.getLong(KEY_WARNING_DEADLINE, 0L);
    }

    public int getCurrentPhase() {
        return prefs.getInt(KEY_CURRENT_PHASE, 0);
    }

    public void saveInterval(int hours) {
        prefs.edit().putInt(KEY_INTERVAL_HOURS, hours).apply();
    }

    public void saveNextDueTime(long time) {
        prefs.edit().putLong(KEY_NEXT_DUE_TIME, time).apply();
    }

    public void saveReminderDeadline(long time) {
        prefs.edit().putLong(KEY_REMINDER_DEADLINE, time).apply();
    }

    public void saveWarningDeadline(long time) {
        prefs.edit().putLong(KEY_WARNING_DEADLINE, time).apply();
    }

    public void saveCurrentPhase(int phase) {
        prefs.edit().putInt(KEY_CURRENT_PHASE, phase).apply();
    }

    public void clearWindows() {
        prefs.edit()
                .putLong(KEY_REMINDER_DEADLINE, 0L)
                .putLong(KEY_WARNING_DEADLINE, 0L)
                .apply();
    }
}
