package top.wuhaojie.week.data;

import io.realm.Realm;
import io.realm.RealmResults;
import top.wuhaojie.week.entities.TaskDetailEntity;
import top.wuhaojie.week.entities.TaskState;
import top.wuhaojie.week.utils.DateUtils;

/**
 * Created by wuhaojie on 2016/11/30 9:26.
 */

public class DataDao {

    private static volatile DataDao mDataDao;

    private DataDao() {
    }


    public static DataDao getInstance() {
        if (mDataDao == null) {
            synchronized (DataDao.class) {
                if (mDataDao == null) {
                    mDataDao = new DataDao();
                }
            }
        }
        return mDataDao;
    }

    public void insertTask(TaskDetailEntity taskDetailEntity) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.copyToRealm(taskDetailEntity);
        });
    }

    public RealmResults<TaskDetailEntity> findAllTask() {
        return Realm.getDefaultInstance().where(TaskDetailEntity.class)
                .findAllSorted("timeStamp");
    }


    public RealmResults<TaskDetailEntity> findAllTask(int dayOfWeek) {
        return Realm.getDefaultInstance().where(TaskDetailEntity.class)
                .equalTo("dayOfWeek", dayOfWeek)
                .findAllSorted("timeStamp");
    }

    public RealmResults<TaskDetailEntity> findUnFinishedTasks(int dayOfWeek) {
        return Realm
                .getDefaultInstance()
                .where(TaskDetailEntity.class)
                .equalTo("dayOfWeek", dayOfWeek)
                .notEqualTo("state", TaskState.FINISHED)
                .findAllSorted("timeStamp");
    }


    public RealmResults<TaskDetailEntity> findAllTaskOfThisWeekFromSunday() {
        long sundayTimeMillisOfWeek = DateUtils.getFirstSundayTimeMillisOfWeek();
        return Realm.getDefaultInstance().where(TaskDetailEntity.class)
                .greaterThan("timeStamp", sundayTimeMillisOfWeek)
                .findAll();
    }


    public void editTask(TaskDetailEntity oldTask, TaskDetailEntity newTask) {
        Realm.getDefaultInstance().executeTransaction(realm -> oldTask.setTaskDetailEntity(newTask));
    }


    public void deleteTask(TaskDetailEntity entity) {

        Realm.getDefaultInstance().executeTransaction(realm -> {
            TaskDetailEntity first = realm.where(TaskDetailEntity.class)
                    .equalTo("dayOfWeek", entity.getDayOfWeek())
                    .equalTo("title", entity.getTitle())
                    .equalTo("content", entity.getContent())
                    .equalTo("icon", entity.getIcon())
                    .equalTo("priority", entity.getPriority())
                    .equalTo("state", entity.getState())
                    .equalTo("timeStamp", entity.getTimeStamp())
                    .findFirst();
            if (first != null)
                first.deleteFromRealm();
        });


    }


    public void switchTaksState(TaskDetailEntity entity, int state) {
        Realm.getDefaultInstance().executeTransaction(realm -> entity.setState(state));
    }


    public void close() {
        mDataDao = null;
    }

}
