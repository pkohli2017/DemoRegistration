package com.one.app.demo.registration.registration

import com.one.app.demo.registration.registration.activities.presenter.LoginPresenter

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginPresenterTest {
    @Mock
    internal var mLoginPresenterView: LoginPresenter.LoginPresenterView? = null
    private var mLoginPresenter: LoginPresenter? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mLoginPresenter = LoginPresenter.newInstance(mLoginPresenterView)
    }

    @Test
    fun checkPasscodeTest() {
        if (mLoginPresenter == null) {
            Assert.assertTrue("LoginPresenterTest object null", false)
        } else {
            val isPasswordValid = mLoginPresenter!!.checkPasscode(DUMMY_PASSCODE)
            Assert.assertTrue("Password InValid", isPasswordValid)
        }
    }

    companion object {
        private val DUMMY_PASSCODE = 123456
    }
}
