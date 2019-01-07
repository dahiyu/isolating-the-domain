package example.domain.model.attendance;

import example.domain.model.timerecord.TimeRecord;
import example.domain.model.timerecord.WorkDate;
import example.domain.model.timerecord.WorkMonth;
import example.domain.model.timerecord.WorkRecords;
import example.domain.model.worker.WorkerNumber;
import example.domain.type.time.QuarterHour;

import java.util.List;

/**
 * 勤怠
 */
public class Attendance {

    WorkerNumber workerNumber;
    WorkMonth month;
    WorkRecords workRecords;

    public Attendance(WorkerNumber workerNumber, WorkMonth month, WorkRecords workRecords) {
        this.workerNumber = workerNumber;
        this.month = month;
        this.workRecords = workRecords;
    }

    public WorkMonth month() {
        return month;
    }

    public List<WorkDate> listWorkDates() {
        return month.days();
    }

    public TimeRecord at(WorkDate workDate) {
        return workRecords.at(workDate);
    }

    public AttendanceStatus statusOf(WorkDate workDate) {
        return AttendanceStatus.from(workRecords.recordedAt(workDate));
    }

    public boolean notWorking() {
        return workRecords.list().isEmpty();
    }

    public WorkDate firstWorkDate() {
        List<TimeRecord> list = workRecords.list();
        return list.get(0).workDate();
    }

    public TotalWorkTime totalWorkTime() {
        return new TotalWorkTime(workRecords.list().stream()
                .map(workRecord -> workRecord.actualWorkTime().workTime().quarterHour())
                .reduce(QuarterHour::add)
                .orElseGet(QuarterHour::new));
    }

    public List<TimeRecord> listAvailableWorkRecord() {
        return workRecords.list();
    }
}