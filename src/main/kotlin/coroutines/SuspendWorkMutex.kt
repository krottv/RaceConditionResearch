package coroutines

import data.SharedMutableData
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SuspendWorkMutex(suspendRunner: SuspendRunner,
                       sharedMutableData: SharedMutableData
) :
    SuspendWork(suspendRunner, sharedMutableData) {

    private val mutex = Mutex(locked = false)

    override suspend fun incrementState() {
        mutex.withLock {
            super.incrementState()
        }
    }
}