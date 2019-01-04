package example.infrastructure.datasource.workrecord;

import example.domain.model.timerecord.WorkRecord;
import example.domain.model.timerecord.WorkDate;
import example.domain.model.worker.WorkerNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkRecordMapper {
    Integer newWorkTimeIdentifier();

    void insertWorkTimeHistory(@Param("id") Integer id, @Param("workerNumber") WorkerNumber workerNumber, @Param("work") WorkRecord work);

    void insertWorkTime(@Param("workerNumber") WorkerNumber workerNumber, @Param("workTimeId") Integer workTimeId, @Param("work") WorkRecord work);

    void deleteWorkTime(@Param("workerNumber") WorkerNumber workerNumber, @Param("workDate") WorkDate workDate);

    List<WorkRecord> selectByMonth(@Param("workerNumber") WorkerNumber workerNumber, @Param("yearMonth") String yearMonth);
}
