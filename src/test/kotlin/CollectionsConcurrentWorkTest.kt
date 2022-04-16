import coroutines.SuspendRunnerImpl
import coroutines.SuspendWork
import data.collections.*
import kotlin.test.Test

class CollectionsConcurrentWorkTest {

    @Test()
    fun simpleList() {
        try {
            ConcurrentTestUtils.testClass(
                SuspendWork(SuspendRunnerImpl(), ListSharedMutableData()),
                canBeLong = false,
                expectedToBeUnstable = true
            )
        } catch (e: IndexOutOfBoundsException) {
            // nothing. Excepted here because of concurrent modifications, but it is not always thrown
        }
    }

    @Test
    fun synchronizedList() {
        ConcurrentTestUtils.testClass(
            SuspendWork(SuspendRunnerImpl(), SynchronizedListSharedMutableData()),
            canBeLong = false, expectedToBeUnstable = false
        )
    }


    @Test
    fun concurrentList() {
        ConcurrentTestUtils.testClass(
            SuspendWork(SuspendRunnerImpl(), ConcurrentListSharedMutableData()),
            canBeLong = true, expectedToBeUnstable = false
        )
    }

    @Test
    fun simpleMap() {
        ConcurrentTestUtils.testClass(
            SuspendWork(SuspendRunnerImpl(), HashMapSharedMutableData()),
            canBeLong = false,
            expectedToBeUnstable = true
        )
    }

    @Test
    fun synchronizedMap() {
        ConcurrentTestUtils.testClass(
            SuspendWork(SuspendRunnerImpl(), SynchronizedMapSharedData()),
            canBeLong = false,
            expectedToBeUnstable = false
        )
    }

    @Test
    fun concurrentMap() {
        ConcurrentTestUtils.testClass(
            SuspendWork(SuspendRunnerImpl(), ConcurrentMapSharedData()),
            canBeLong = false,
            expectedToBeUnstable = false
        )
    }
}