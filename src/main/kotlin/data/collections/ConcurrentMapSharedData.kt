package data.collections

import data.SharedMutableData
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class ConcurrentMapSharedData: SharedMutableData {
    private val map: MutableMap<Int, String> = ConcurrentHashMap()

    private val size = AtomicInteger(0)

    override fun increment() {
        val newSize = size.getAndIncrement()
        map[newSize] = "${newSize}_element"
    }

    override val numOperations: Int
        get() = map.size
}