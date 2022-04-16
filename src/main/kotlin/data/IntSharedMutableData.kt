package data

open class IntSharedMutableData(initialValue: Int = 0): SharedMutableData {
    protected open var i = initialValue

    override fun increment() {
        i++
    }

    override val numOperations: Int
        get() = i

    override fun toString(): String {
        return "IntSharedMutableData(i=$i)"
    }
}