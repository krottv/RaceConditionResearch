interface MutableDataOutput {
    fun print(numOperationsActual: Int, numOperationsExpected: Int, timeTook: Long)
}

class MutableDataOutputImpl() : MutableDataOutput {
    override fun print(numOperationsActual: Int, numOperationsExpected: Int, timeTook: Long) {
        println("coroutine runner has finished actual ${numOperationsActual}. " +
                "expected ${numOperationsExpected}, timeTook ${timeTook}, percentOfCompleted ${numOperationsActual.toDouble() / numOperationsExpected}")
    }
}