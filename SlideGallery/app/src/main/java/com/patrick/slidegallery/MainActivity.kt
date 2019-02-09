package com.patrick.slidegallery

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity.javaClass.simpleName
    companion object {
        const val REQUEST_READ_EXTERNAL_STORAGE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 외부 저장소 읽기 권한이 허용되지 않았다면
        if(!checkReadPermission()) {
            // 권한 확인 요청
            executeReadPermission()
        } else {
            // 권한이 이미 허용됨
            getAllPhoto()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    //궈한 허용됨
                    getAllPhoto()
                else
                    //권한 거부됨
                    toast(R.string.request_rejected)
                return
            }
        }
    }

    // 모든 사진 정보 가져옴
    private fun getAllPhoto() {
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")

        val fragments = ArrayList<Fragment>()
        if(cursor != null) {
            while (cursor.moveToNext()) {
                // 사진 경로 Uri 가져오기
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.d(TAG, uri)
                // 사진 마다 프래그먼트 생성하면서 리스트에 추가
                fragments.add(PhotoFragment.newInstance(uri))
            }
            cursor.close()
        }

        // 어댑터
        val adapter = SlidePagerAdapter(supportFragmentManager)
        adapter.updateFragments(fragments)
        viewPager.adapter = adapter

        // 3초마다 자동 슬라이드
        timer(period =  3000) {
            runOnUiThread {
                if(viewPager.currentItem < adapter.count - 1) {
                    viewPager.currentItem++
                } else {
                    viewPager.currentItem = 0
                }
            }
        }
    }

    // 외부 저장소 읽기 권한이 허용되었는지 확인
    private fun checkReadPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED
    }

    // 외부 저장소 읽기 권한 요청
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
    }

    // 권한 요청 처리
    private fun executeReadPermission() {
        // 이전에 이미 권한을 거부한 적이 있는 경우
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // 권한 필요 설명
            alert (
                R.string.request_permission_text, R.string.request_permission_title
            ) {
                yesButton {
                    requestPermission()
                }
                noButton {
                }
            }.show()
        } else {
            requestPermission()
        }
    }
}
