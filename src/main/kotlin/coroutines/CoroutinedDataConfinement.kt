package coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoroutinedDataConfinement(
    coroutineRunner: CoroutineRunner
) : CoroutinedData(coroutineRunner) {
    private val dispatcher = Dispatchers.Default.limitedParallelism(1)

    override suspend fun incrementState() {
        withContext(dispatcher) {
            super.incrementState()
        }
    }
}