package devy.hi.berkeley.db;

import com.sleepycat.collections.TransactionWorker;

public class DatabaseTransaction {

    public static boolean run(TransactionWorker worker) {
        try {
            DatabaseManager.instance().getTxnRunner().run(worker);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}