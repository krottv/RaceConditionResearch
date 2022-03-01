package coroutines

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CoroutinedDataMutex(coroutineRunner: CoroutineRunner) :
    CoroutinedData(coroutineRunner) {

    private val mutex = Mutex(locked = false)

    override suspend fun incrementState() {
        mutex.withLock {
            super.incrementState()
        }
    }
}