package com.rippleInv.sidemung.Model;

import com.google.gson.annotations.SerializedName;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageRequestBody {
    @SerializedName("image")
    private File imageFile;

    public ImageRequestBody(File imageFile) {
        this.imageFile = imageFile;
    }

    public RequestBody getRequestBody() {
        return RequestBody.create(MediaType.parse("image/*"), imageFile);
    }

    public MultipartBody.Part getMultipart() {
        return MultipartBody.Part.createFormData("image", imageFile.getName(), getRequestBody());
    }
}
