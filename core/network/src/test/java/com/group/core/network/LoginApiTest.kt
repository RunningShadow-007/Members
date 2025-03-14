import com.group.core.model.ERROR
import com.group.core.model.NetworkResponse
import com.group.core.model.SUCCESS
import com.group.core.network.datasource.user.api.LoginApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoginApiTest {
    // 1. 创建 mock 对象
    private val loginApi = mockk<LoginApi>()


    @Test
    fun testLoginSuccess() = runTest {
        // 2. 设置 mock 行为
        coEvery {
            loginApi.login(any())  // 匹配任意参数
        } returns NetworkResponse(data = "登录失败", code = SUCCESS)  // 模拟成功响应

        // 3. 执行测试
        val response = loginApi.login(LoginApi.Request("13800138000", "password123"))  // 触发真实调用
        println("testLoginSuccess: ${response.data}")
        // 4. 验证结果
        assert(response.code == SUCCESS)  // 断言响应类型
        coVerify(exactly = 1) { loginApi.login(any()) }  // 验证方法调用次数
    }

    @Test
    fun testLoginFailure() = runTest {
        coEvery { loginApi.login(any()) } returns NetworkResponse(
            code = ERROR,
            message = "失败",
            data = Any()
        )

        val response = loginApi.login(LoginApi.Request("wrong", "password"))
        println("testLoginFailure: ${response.message}")

        assert(response.code == ERROR)
    }
}
