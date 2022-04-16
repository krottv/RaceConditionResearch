package thread

import data.SharedMutableData

class ThreadWorkSynchronized(asyncRunner: ThreadRunner, sharedMutableData: SharedMutableData) : ThreadWork(
    asyncRunner,
    sharedMutableData
) {

    @Synchronized
    override fun incrementState() {
        super.incrementState()
    }
}