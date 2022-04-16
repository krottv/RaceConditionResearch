package data.collections

import java.util.concurrent.CopyOnWriteArrayList

class ConcurrentListSharedMutableData: BaseMutableListSharedData() {
    override val list: MutableList<String> = CopyOnWriteArrayList()
}