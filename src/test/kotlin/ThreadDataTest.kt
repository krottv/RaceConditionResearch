import org.junit.FixMethodOrder
import thread.*
import kotlin.test.Test

@FixMethodOrder
class ThreadDataTest {

    @Test(timeout = CoroutineDataTest.TEST_TIMEOUT)
    fun testThreadBasic() {
        CoroutineDataTest.testClass(
            ThreadedData(ThreadRunnerImpl()),
            canBeLong = false,
            expectedToBeUnstable = true
        )
    }

    @Test(timeout = CoroutineDataTest.TEST_TIMEOUT)
    fun testThreadReentrantLock() {
        CoroutineDataTest.testClass(
            ThreadedDataReentrantLock(ThreadRunnerImpl(), isFair = false),
            canBeLong = true,
            expectedToBeUnstable = false
        )
    }

    @Test(timeout = CoroutineDataTest.TEST_TIMEOUT)
    fun testThreadVolatile() {
        CoroutineDataTest.testClass(
            ThreadedDataVolatile(ThreadRunnerImpl()),
            canBeLong = false,
            expectedToBeUnstable = true
        )
    }

    @Test(timeout = CoroutineDataTest.TEST_TIMEOUT)
    fun testThreadSync() {
        CoroutineDataTest.testClass(
            ThreadedDataSynchronized(ThreadRunnerImpl()),
            canBeLong = false,
            expectedToBeUnstable = false
        )
    }
}