package com.example.furkan1904569.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.furkan1904569.R
import com.huawei.agconnect.api.AGConnectApi
import com.huawei.agconnect.auth.*
import com.huawei.hmf.tasks.OnFailureListener
import com.huawei.hmf.tasks.OnSuccessListener
import com.huawei.hmf.tasks.Task
import com.huawei.hmf.tasks.TaskExecutors
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [Auth_Service.newInstance] factory method to
 * create an instance of this fragment.
 */
class Auth_Service : Fragment() {

    val user = AGConnectAuth.getInstance().currentUser
    val authParams: AccountAuthParams = AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
        .setAccessToken()
        .createParams();
    service = AccountAuthManager.getService(this@HWIDActivity, authParams)
    startActivityForResult(service!!.signInIntent, SIGN_CODE)

    val credential = HwIdAuthProvider.credentialWithToken(accessToken)
    AGConnectAuth.getInstance().signIn(credential).addOnSuccessListener{
        val user =it.user
    }.addOnFailureListener {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        AGConnectApi.getInstance().activityLifecycle().onActivityResult(requestCode, resultCode, data)
    }
    AGConnectAuth.getInstance().signIn(this, AGConnectAuthCredential.HMS_Provider).addOnSuccessListener{
    }.addOnFailureListener{}
}
