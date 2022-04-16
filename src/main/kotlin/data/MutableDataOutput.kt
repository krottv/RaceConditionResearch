package data

import ConcurrentWork

interface MutableDataOutput {
    fun print(work: ConcurrentWork, numOperationsExpected: Int, timeTook: Long)
}

class MutableDataOutputImpl : MutableDataOutput {
    override fun print(work: ConcurrentWork, numOperationsExpected: Int, timeTook: Long) {
        val numOperationsActual = work.sharedMutableData.numOperations
        val type = work.sharedMutableData::class.java.simpleName

        println("concurrentWork $type has finished actual ${numOperationsActual}. " +
                "expected ${numOperationsExpected}, timeTook ${timeTook}, percentOfCompleted ${numOperationsActual.toDouble() / numOperationsExpected}")
    }
}