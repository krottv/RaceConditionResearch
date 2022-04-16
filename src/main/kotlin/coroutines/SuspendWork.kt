package coroutines

import ConcurrentWork
import data.SharedMutableData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

open class SuspendWork (
    private val suspendRunner: SuspendRunner,
    override var sharedMutableData: SharedMutableData
) : ConcurrentWork {


    override fun run() {
        runBlocking {
            started()
            suspendRunner.run {
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