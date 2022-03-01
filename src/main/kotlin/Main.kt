import coroutines.CoroutineRunnerImpl
import thread.ThreadRunnerImpl
import thread.ThreadedDataReentrantLock
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val d = ThreadedDataReentrantLock(ThreadRunnerImpl(), isFair = false)
    runAndPrint(d)
}


private fun runAndPrint(data: SharedMutableData) {
    val time = measureTimeMillis { data.run() }
    MutableDataOutputImpl().print(data.mutableState, CoroutineRunnerImpl.NUM_TOTAL_OPERATIONS, time)
}