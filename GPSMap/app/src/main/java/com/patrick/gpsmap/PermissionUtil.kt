package com.patrick.gpsmap

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

const val REQUEST_ACCESS_FINE_LOCATION = 1001

class PermissionUtil {
    companion object {
        // 위치 권한이 있는지 검사
        @JvmStatic
        private fun isPermissionAccepted(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
        }

        @JvmStatic
        private fun requestPermission(context: Context) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                 REQUEST_ACCESS_FINE_LOCATION)
        }

        //권한 요청 처리
        @JvmStatic
        fun checkPermission(context: Context): Boolean {
            var flag = true
            //퍼미션이 허용되지 않은 상태면
            if(!isPermissionAccepted(context)) {
                //이전에 권한을 거부한 적이 있는 경우
                if(ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    context.alert(R.string.dialog_message, R.string.dialog_title) {
                        yesButton {
                            requestPermission(context)
                        }
                        noButton {
                            flag = false
                        }
                    }.show()
                } else {
                    requestPermission(context)
                }
            }
            return flag
        }
    }
}