package thread

import coroutines.CoroutineRunnerImpl
import data.NumOperations
import java.util.concurrent.CountDownLatch

interface ThreadRunner {
    fun run(action: () -> Unit)
    val numTotalOperations: Int
}

class ThreadRunnerImpl : ThreadRunner {

    private val countDownLatch = CountDownLatch(NumOperations.NUM_THREADS)

    override fun run(action: () -> Unit) {

        repeat(NumOperations.NUM_THREADS) {
            val childThread = Thread {

                repeat(NumOperations.NUM_OPERATIONS) {
                    action()
                }

                countDownLatch.countDown()
            }
            childThread.start()
        }

        countDownLatch.await()

    }

    override val numTotalOperations: Int
        get() = NumOperations.NUM_TOTAL_OPERATIONS

}