package com.group12.guardian.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CheckInAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) return;

        CheckInManager manager = new CheckInManager(context);
        manager.onAlarmTriggered(action);
    }
}
