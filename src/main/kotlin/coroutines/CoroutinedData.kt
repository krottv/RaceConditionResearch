package coroutines

import SharedMutableData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

open class CoroutinedData(
    private val coroutineRunner: CoroutineRunner

) : SharedMutableData {
    override var mutableState: Int = 0

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
        mutableState++
    }
}