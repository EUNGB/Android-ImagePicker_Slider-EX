package com.meltingB.imagepicker_slider_ex

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.esafirm.imagepicker.features.*
import com.esafirm.imagepicker.model.Image
import com.rd.PageIndicatorView

class SlideActivity : AppCompatActivity() {

    private lateinit var btnGallery: Button
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: PageIndicatorView

    private lateinit var launcher: ImagePickerLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

        btnGallery = findViewById(R.id.btnGallery)
        indicator = findViewById(R.id.indicator)
        viewPager = findViewById(R.id.viewPager)

        indicator.visibility = View.INVISIBLE

        launcher = registerImagePicker { result ->
            if (result.isNotEmpty()) {
                setSlideAdapter(result)
            }
        }

        btnGallery.setOnClickListener {
            val config = ImagePickerConfig {
                mode = ImagePickerMode.MULTIPLE // 여러 장 선택
                isFolderMode = false
                isIncludeVideo = false
                arrowColor = Color.WHITE
                imageTitle = "이미지를 선택하세요"
                doneButtonText = "추가"
                isShowCamera = true
                returnMode = ReturnMode.NONE
            }

            launcher.launch(config)
        }
    }

    private fun setSlideAdapter(images: List<Image>) {
        val pagerCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                indicator.setSelected(position) // ViewPager 의 position 값이 변경된 경우 Indicator Position 변경
            }
        }

        val adapter = ImageSliderAdapter(this, images)
        viewPager.adapter = adapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.registerOnPageChangeCallback(pagerCallback) // 콜백 등록

        // Indicator 설정
        indicator.setSelected(0) // 1번째 이미지가 선택된 것으로 초기화
        indicator.count = images.size // 이미지 리스트 사이즈만큼 생성
        indicator.visibility = View.VISIBLE
    }

}



