package data.collections

class HashMapSharedMutableData: BaseMutableMapSharedData() {
    override val map: MutableMap<Int, String> = HashMap()
}