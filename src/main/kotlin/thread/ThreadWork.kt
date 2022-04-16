package thread

import ConcurrentWork
import data.SharedMutableData

open class ThreadWork (
    private val concurrentRunner: ThreadRunner,
    override val sharedMutableData: SharedMutableData,
): ConcurrentWork {

    override fun run() {
        started()
        concurrentRunner.run {
            incrementState()
        }
        finished()
    }

    open fun started() {

    }

    open fun finished() {

    }

    open fun incrementState() {
        sharedMutableData.increment()
    }
}