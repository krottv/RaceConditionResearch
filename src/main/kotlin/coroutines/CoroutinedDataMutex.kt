package coroutines

import data.SharedMutableData
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CoroutinedDataMutex(coroutineRunner: CoroutineRunner,
                          sharedMutableData: SharedMutableData
) :
    CoroutinedData(coroutineRunner, sharedMutableData) {

    private val mutex = Mutex(locked = false)

    override suspend fun incrementState() {
        mutex.withLock {
            super.incrementState()
        }
    }
}