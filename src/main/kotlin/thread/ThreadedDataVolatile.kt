package thread

class ThreadedDataVolatile(asyncRunner: ThreadRunner): ThreadedData(asyncRunner) {

    @Volatile
    override var mutableState: Int = 0
}