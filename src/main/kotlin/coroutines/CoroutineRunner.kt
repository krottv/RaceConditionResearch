package coroutines

import data.NumOperations.NUM_OPERATIONS
import data.NumOperations.NUM_THREADS
import data.NumOperations.NUM_TOTAL_OPERATIONS
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

}

