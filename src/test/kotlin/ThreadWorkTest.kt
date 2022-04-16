import data.IntSharedMutableData
import data.VolatileIntMutableData
import org.junit.FixMethodOrder
import thread.*
import kotlin.test.Test

@FixMethodOrder
class ThreadWorkTest {

    @Test(timeout = ConcurrentTestUtils.TEST_TIMEOUT)
    fun testThreadBasic() {
        ConcurrentTestUtils.testClass(
            ThreadWork(ThreadRunnerImpl(), IntSharedMutableData()),
            canBeLong = false,
            expectedToBeUnstable = true
        )
    }

    @Test(timeout = ConcurrentTestUtils.TEST_TIMEOUT)
    fun testThreadReentrantLock() {
        ConcurrentTestUtils.testClass(
            ThreadWorkReentrantLock(ThreadRunnerImpl(), false, IntSharedMutableData()),
            canBeLong = true,
            expectedToBeUnstable = false
        )
    }

    @Test(timeout = ConcurrentTestUtils.TEST_TIMEOUT)
    fun testThreadVolatile() {
        ConcurrentTestUtils.testClass(
            ThreadWork(ThreadRunnerImpl(), VolatileIntMutableData()),
            canBeLong = false,
            expectedToBeUnstable = true
        )
    }

    @Test(timeout = ConcurrentTestUtils.TEST_TIMEOUT)
    fun testThreadSync() {
        ConcurrentTestUtils.testClass(
            ThreadWorkSynchronized(ThreadRunnerImpl(), IntSharedMutableData()),
            canBeLong = false,
            expectedToBeUnstable = false
        )
    }
}