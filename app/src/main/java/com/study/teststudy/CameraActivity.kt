package com.study.teststudy

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.study.teststudy.databinding.ActivityMainBinding
import com.study.teststudy.databinding.ActivityPageBinding
import java.lang.Exception
import java.text.SimpleDateFormat

class CameraActivity : BaseActivity {
    val PERM_STORAGE=9
    val PERM_CAMERA=10
    val REQ_CAMERA=11

    //원본 이미지 주소 저장할 변수
    var realUri: Uri? = null

    val binding by lazy { ActivityPageBinding.inflate(layoutInflater) }

    constructor(){
        setContentView(binding.root)
        //공용저장소 권한 확인
        requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),PERM_STORAGE)
    }

    //카메라 권한 확인->승인 상태 카메라 호출
    fun initViews(){
        binding.btnPhoto.setOnClickListener{
            requirePermissions(arrayOf(Manifest.permission.CAMERA),PERM_CAMERA)
        }
    }

    //카메라에 찍은 사진 저장할 uri 전송
    fun openCamera(){
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        createImageUir(newFileName(),"image/jpg")?.let{
                uri ->
            realUri=uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT,realUri)
        }

        startActivityForResult(intent,REQ_CAMERA)
    }

    //원본 이미지 저장할 Uri를 MediaStore에 생성:android db
    fun createImageUir(filename:String, mineType:String):Uri?{
        val values= ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME,filename)
        values.put(MediaStore.Images.Media.MIME_TYPE,mineType)

        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
    }

    //파일 이름을 생성하는 함수
    fun newFileName():String{
        val sdf= SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename=sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }

    //원본 이미지 불러오는 함수
    fun loadBitmap(photoUri: Uri): Bitmap?{
        var image: Bitmap?=null
        try{
            if(Build.VERSION.SDK_INT> Build.VERSION_CODES.O_MR1){
                val source: ImageDecoder.Source= ImageDecoder.createSource(contentResolver, photoUri)
                image= ImageDecoder.decodeBitmap(source)
            }
            else{
                image=MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
            }
        }catch(e: Exception){
            e.printStackTrace()
        }
        return image
    }

    override fun permissionGranted(requestCode: Int) {
        when(requestCode){
            PERM_STORAGE -> initViews()
            PERM_CAMERA -> openCamera()
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode){
            PERM_STORAGE ->{
                Toast.makeText(this,"공용저장 권한 승인 필요", Toast.LENGTH_SHORT).show()
                finish()
            }
            PERM_CAMERA -> Toast.makeText(this,"카메라 권한 승인 필요", Toast.LENGTH_SHORT).show()
        }
    }

    //카메라 찍은 후 호출
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode){
                REQ_CAMERA -> {
                    realUri?.let{
                            uri->
                        val bitmap=loadBitmap(uri)
                        binding.ivPhoto.setImageBitmap(bitmap)

                        realUri=null
                    }
                }
            }
        }
    }
}