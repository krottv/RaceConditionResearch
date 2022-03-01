package coroutines

class CoroutinedDataVolatile(coroutineRunner: CoroutineRunner) : CoroutinedData(coroutineRunner) {

    @Volatile
    override var mutableState: Int = 0
}