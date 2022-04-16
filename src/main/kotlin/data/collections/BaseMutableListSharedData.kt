package data.collections

import data.SharedMutableData

abstract class BaseMutableListSharedData: SharedMutableData {
    protected abstract val list: MutableList<String>

    override fun increment() {
        list.add("${list.size}_element")
    }

    override val numOperations: Int
        get() = list.size
}