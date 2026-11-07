package com.group12.guardian.core;

public class CheckInState {
    public enum Phase {
        SAFE, REMINDER, WARNING, ESCALATION
    }

    public final Phase phase;
    public final long nextCheckInTime;
    public final long deadline;

    public CheckInState(Phase phase, long nextCheckInTime, long deadline) {
        this.phase = phase;
        this.nextCheckInTime = nextCheckInTime;
        this.deadline = deadline;
    }

    public static CheckInState safe(long nextCheckInTime) {
        return new CheckInState(Phase.SAFE, nextCheckInTime, 0);
    }

    public static CheckInState reminder(long deadline) {
        return new CheckInState(Phase.REMINDER, 0, deadline);
    }

    public static CheckInState warning(long deadline) {
        return new CheckInState(Phase.WARNING, 0, deadline);
    }

    public static CheckInState escalation() {
        return new CheckInState(Phase.ESCALATION, 0, 0);
    }
}
