package thread

import data.SharedMutableData
import java.util.concurrent.locks.ReentrantLock

class ThreadWorkReentrantLock(asyncRunner: ThreadRunner, val isFair: Boolean,
                              sharedMutableData: SharedMutableData
) : ThreadWork(asyncRunner, sharedMutableData) {

    private val lock = ReentrantLock(isFair)

    override fun incrementState() {

        try {
            lock.lock()
            super.incrementState()
        } finally {
            lock.unlock()
        }
    }
}