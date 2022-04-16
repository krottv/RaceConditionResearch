package coroutines

import ConcurrentWork
import data.SharedMutableData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

open class CoroutinedData (
    private val coroutineRunner: CoroutineRunner,
    override var sharedMutableData: SharedMutableData
) : ConcurrentWork {


    override fun run() {
        runBlocking {
            started()
            coroutineRunner.run {
                incrementState()
            }
            finished()
        }
    }

    open fun CoroutineScope.started() {

    }

    open suspend fun finished() {

    }

    open suspend fun incrementState() {
        sharedMutableData.increment()
    }
}