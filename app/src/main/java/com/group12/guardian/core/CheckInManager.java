package com.group12.guardian.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class CheckInManager {
    private static final long REMINDER_WINDOW_MS = 10 * 60 * 1000L;
    private static final long WARNING_WINDOW_MS = 10 * 60 * 1000L;

    private final Context context;
    private final CheckInPreferences prefs;
    private final AlarmManager alarmManager;

    private CheckInState currentState = CheckInState.safe(0);

    public CheckInManager(Context context) {
        this.context = context.getApplicationContext();
        this.prefs = new CheckInPreferences(this.context);
        this.alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
    }

    public CheckInState getCurrentState() {
        return currentState;
    }

    public void confirmOk() {
        long now = System.currentTimeMillis();
        int hours = prefs.getIntervalHours();
        long nextDue = now + hours * 60L * 60L * 1000L;

        prefs.saveNextDueTime(nextDue);
        prefs.saveCurrentPhase(0);
        prefs.clearWindows();

        currentState = CheckInState.safe(nextDue);
        scheduleAlarm(nextDue, "DUE_TIME", 0);
    }

    public void setInterval(int hours) {
        prefs.saveInterval(hours);
    }

    public void restoreOrComputeState() {
        long now = System.currentTimeMillis();
        long nextDue = prefs.getNextDueTime();
        int phase = prefs.getCurrentPhase();
        long reminderDeadline = prefs.getReminderDeadline();
        long warningDeadline = prefs.getWarningDeadline();

        if (nextDue == 0L) {
            currentState = CheckInState.safe(0);
        } else if (now < nextDue && phase == 0) {
            currentState = CheckInState.safe(nextDue);
            scheduleAlarm(nextDue, "DUE_TIME", 0);
        } else if (now >= nextDue && (phase == 0 || phase == 1)) {
            long deadline = (reminderDeadline > now) ? reminderDeadline : now + REMINDER_WINDOW_MS;
            prefs.saveReminderDeadline(deadline);
            prefs.saveCurrentPhase(1);
            currentState = CheckInState.reminder(deadline);
            scheduleAlarm(deadline, "REMINDER_END", 1);
        } else if (now >= reminderDeadline && phase <= 2) {
            long deadline = (warningDeadline > now) ? warningDeadline : now + WARNING_WINDOW_MS;
            prefs.saveWarningDeadline(deadline);
            prefs.saveCurrentPhase(2);
            currentState = CheckInState.warning(deadline);
            scheduleAlarm(deadline, "WARNING_END", 2);
        } else if (now >= warningDeadline) {
            prefs.saveCurrentPhase(3);
            currentState = CheckInState.escalation();
        } else {
            currentState = CheckInState.safe(nextDue);
        }
    }

    public void onAlarmTriggered(String action) {
        restoreOrComputeState();
    }

    private void scheduleAlarm(long triggerAt, String action, int requestCode) {
        Intent intent = new Intent(context, CheckInAlarmReceiver.class);
        intent.setAction(action);

        PendingIntent pending = PendingIntent.getBroadcast(
                context, requestCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pending);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAt, pending);
        }
    }
}
