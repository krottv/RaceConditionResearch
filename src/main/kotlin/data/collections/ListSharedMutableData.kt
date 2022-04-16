package data.collections

class ListSharedMutableData: BaseMutableListSharedData() {
    override val list: MutableList<String> = mutableListOf()
}