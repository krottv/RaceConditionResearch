package coroutines

import data.SharedMutableData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuspendWorkConfinement(
    suspendRunner: SuspendRunner, sharedMutableData: SharedMutableData
) : SuspendWork(suspendRunner, sharedMutableData) {
    private val dispatcher = Dispatchers.Default.limitedParallelism(1)

    override suspend fun incrementState() {
        withContext(dispatcher) {
            super.incrementState()
        }
    }
}