package data

interface SharedMutableDataOutput {
    val numOperations: Int
}

interface SharedMutableData: SharedMutableDataOutput {
    fun increment()
}
