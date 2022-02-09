package com.meltingB.imagepicker_slider_ex

import android.graphics.Color
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.*

class MainActivity : AppCompatActivity() {

    private lateinit var launcher: ImagePickerLauncher
    private lateinit var profileImageView: ImageView
    private lateinit var profileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileImageView = findViewById(R.id.ivProfile)
        profileButton = findViewById(R.id.btnProfile)

        // 프로필 사진 업로드 버튼 클릭 이벤트
        profileButton.setOnClickListener {
            val config = ImagePickerConfig {
                mode = ImagePickerMode.SINGLE // 1장만 선택
                isFolderMode = false
                isIncludeVideo = false
                arrowColor = Color.WHITE
                imageTitle = "이미지를 선택하세요"
                doneButtonText = "추가"
                isShowCamera = true
                returnMode = ReturnMode.ALL
            }

            launcher.launch(config)
        }

        launcher = registerImagePicker { result ->
            if (result.isNotEmpty()) {
                val profileImage = result.first() // 1장만 선택하기 때문에
                // 이미지 Uri를 통해 이미지뷰에 이미지를 넣어준다.
                setProfileImage(profileImage.uri)
            }
        }
    }

    private fun setProfileImage(imageUri: Uri) {
        Glide.with(this).load(imageUri).circleCrop().into(profileImageView)
    }
}