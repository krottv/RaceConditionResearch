package thread

import SharedMutableData

open class ThreadedData(
    private val asyncRunner: ThreadRunner

): SharedMutableData {

    override var mutableState: Int = 0

    override fun run() {
        started()
        asyncRunner.run {
            incrementState()
        }
        finished()
    }

    open fun started() {

    }

    open fun finished() {

    }

    open fun incrementState() {
        mutableState++
    }
}