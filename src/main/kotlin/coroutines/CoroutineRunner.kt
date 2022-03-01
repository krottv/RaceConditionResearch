package coroutines

import kotlinx.coroutines.*

interface CoroutineRunner {
    suspend fun run(action: suspend () -> Unit)
    val numTotalOperations: Int
}

class CoroutineRunnerImpl : CoroutineRunner {

    override suspend fun run(action: suspend () -> Unit) {

        withContext(Dispatchers.Default) {
            coroutineScope {
                repeat(NUM_THREADS) {
                    launch {
                        repeat(NUM_OPERATIONS) {
                            action()
                        }
                    }
                }
            }
        }
    }

    override val numTotalOperations: Int
        get() = NUM_TOTAL_OPERATIONS

    companion object {
        const val NUM_THREADS = 100
        const val NUM_OPERATIONS = 1000
        const val NUM_TOTAL_OPERATIONS = NUM_THREADS * NUM_OPERATIONS
    }
}

