package data.collections

import data.SharedMutableData

abstract class BaseMutableMapSharedData: SharedMutableData {
    protected abstract val map: MutableMap<Int, String>

    override fun increment() {
        map[map.size] = "${map.size}_element"
    }

    override val numOperations: Int
        get() = map.size
}