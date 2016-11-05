package com.mvp.framework.module.params;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;


import com.example.utillib.BitmapUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName: FileUploadParams
 * @author create by Tang
 * @date date 16/9/29 上午11:25
 * @Description: 文件上传参数类
 */
public class FileUploadParams {

    private String type = "0";

    //参数的名称
    private String mName = "imgs";
    //文件名
    private String mFileName ="shabi.png";

    //文件的 mime，需要根据文档查询
    private String mMime = "image/png";

    //文件路径
    private String mFilePath;

    private Bitmap mBitmap ;


    public FileUploadParams(Context context, Uri mFileUri) throws IOException {
        if (mFileUri != null){
            this.mBitmap = BitmapUtil.getBitmapFromUri(context,mFileUri);

        }
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public String getName() {
        return  mName;
    }

    public String getFileName() {
        return mFileName;
    }

    //对图片进行二进制转换
    public byte[] getValue() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
        mBitmap.compress(Bitmap.CompressFormat.JPEG,80,bos) ;
        return bos.toByteArray();
    }
    //因为我知道是 png 文件，所以直接根据文档查的
    public String getMime() {
        return mMime;
    }
}