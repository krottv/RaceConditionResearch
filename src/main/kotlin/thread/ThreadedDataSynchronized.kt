package thread

class ThreadedDataSynchronized(asyncRunner: ThreadRunner) : ThreadedData(asyncRunner) {

    @Synchronized
    override fun incrementState() {
        super.incrementState()
    }
}