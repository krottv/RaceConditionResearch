package thread

import coroutines.CoroutineRunnerImpl
import java.util.concurrent.CountDownLatch

interface ThreadRunner {
    fun run(action: () -> Unit)
    val numTotalOperations: Int
}

class ThreadRunnerImpl : ThreadRunner {

    private val countDownLatch = CountDownLatch(CoroutineRunnerImpl.NUM_THREADS)

    override fun run(action: () -> Unit) {

        repeat(CoroutineRunnerImpl.NUM_THREADS) {
            val childThread = Thread {

                repeat(CoroutineRunnerImpl.NUM_OPERATIONS) {
                    action()
                }

                countDownLatch.countDown()
            }
            childThread.start()
        }

        countDownLatch.await()

    }

    override val numTotalOperations: Int
        get() = CoroutineRunnerImpl.NUM_TOTAL_OPERATIONS

}