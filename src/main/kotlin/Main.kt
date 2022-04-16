
import data.IntSharedMutableData
import data.MutableDataOutputImpl
import data.NumOperations.NUM_TOTAL_OPERATIONS
import thread.ThreadRunnerImpl
import thread.ThreadWorkReentrantLock
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val d = ThreadWorkReentrantLock(ThreadRunnerImpl(), false, IntSharedMutableData())
    runAndPrint(d)
}


private fun runAndPrint(data: ConcurrentWork) {
    val time = measureTimeMillis { data.run() }
    MutableDataOutputImpl().print(data.sharedMutableData.numOperations, NUM_TOTAL_OPERATIONS, time)
}