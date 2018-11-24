package example.domain.model.attendance;

import example.domain.type.time.Minute;

/**
 * 休憩時間（深夜）
 */
public class MidnightBreakTime {
    Minute value;

    public MidnightBreakTime(String value) {
        this(new Minute(value));
    }

    public MidnightBreakTime(Minute value) {
        this.value = value;
    }

    public Minute subtractFrom(Minute workMinute) {
        return workMinute.subtract(value.quarterHourRoundUp());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
