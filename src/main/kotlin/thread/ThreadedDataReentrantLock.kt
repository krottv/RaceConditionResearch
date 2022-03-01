package thread

import java.util.concurrent.locks.ReentrantLock

class ThreadedDataReentrantLock(asyncRunner: ThreadRunner, val isFair: Boolean) : ThreadedData(asyncRunner) {

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