package data.collections

import data.SharedMutableData
import kotlin.collections.HashMap

class SynchronizedMapSharedData: SharedMutableData {
    private val map: MutableMap<Int, String> = HashMap()

    override fun increment() {
        map[map.size] = "${map.size}_element"
    }

    override val numOperations: Int
        get() {
            return map.size
        }
}