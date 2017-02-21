package com.example.juhyeon.flink.UI.Join;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.juhyeon.flink.MainActivity;
import com.example.juhyeon.flink.Network.NetSSL;
import com.example.juhyeon.flink.R;
import com.example.juhyeon.flink.model.Req.Req_SignUp;
import com.example.juhyeon.flink.model.Res.Res_SignUp;
import com.example.juhyeon.flink.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.size.SmallSize;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 셀쇼 가입액티비티
 * 셀쇼 가입 후 버튼으로 메인페이지로 이동
 */
public class SignUpActivity extends AppCompatActivity {

    EditText username, password, nickname, phone, homepage_url;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    SweetAlertDialog alert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);
        //=======================파이어베이스 인증===============================
        username    = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        nickname = (EditText)findViewById(R.id.nickname);
        phone = (EditText)findViewById(R.id.phone);
        homepage_url = (EditText)findViewById(R.id.homepage_url);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //=======================프로필사진 등록===============================
        imageButton = (ImageButton) findViewById(R.id.signupprofile);

    }


    public void onJoin(){
        if(!isValidate()) return;
        //showProgress("회원가입중...");

        final String Username = username.getText().toString();
        String Password   = password.getText().toString();
        String Nickname   = nickname.getText().toString();
        String Phone   = phone.getText().toString();
        String Homepage_url   = homepage_url.getText().toString();
        //=========================파베인증 토큰따기=================================
        firebaseAuth.createUserWithEmailAndPassword(Username, Password)
                .addOnCompleteListener(new OnCompleteListener(){
                    @Override
                    public void onComplete(@NonNull Task task) {
                        // 4. 로딩닫기
                        //hideProgress();
                        if( task.isSuccessful() ){
                            Log.i("CHAT","성공");
                            // 4-1. 회원정보 디비에 입력
                            onUserSaved( Username );
                            // 5. 로그인 처리로 이동
                        }else{
                            // 실패
                            Log.i("CHAT","실패"+task.getException().getMessage());
                        }
                    }
                });
        String registration_token = firebaseAuth.getInstance().getCurrentUser().getUid();

        //=========================회원가입 틀맞춰 서버에 전송=================================
        Call<Res_SignUp> res = NetSSL.getInstance().getMemberImpFactory()
                .join(new Req_SignUp(Username,Password,Nickname,Phone,Homepage_url,registration_token));
        res.enqueue(new Callback<Res_SignUp>() {
            @Override
            public void onResponse(Call<Res_SignUp> call, Response<Res_SignUp> response) {
                if(response.body().getResult() !=null){
                    Log.e("RF","ERR"+response.body().getResult().toString());
                }else{

                }
            }
            @Override
            public void onFailure(Call<Res_SignUp> call, Throwable t) {
                Log.e("RF","ERR"+t.getMessage());
            }
        });
    }
    //====================================프로그레스 다이얼로그========================================
    private ProgressDialog pd;
    public void showProgress(String msg){
        // 객체를 1회만 생성한다.
        if( pd == null ) {
            // 생성한다.
            pd = new ProgressDialog(this);
            // 백키로 닫는 기능을 제거한다.
            pd.setCancelable(false);
        }
        // 원하는 메시지를 세팅한다.
        pd.setMessage(msg);
        // 화면에 띠워라
        pd.show();
    }
    public void hideProgress(){
        // 닫는다 : 객체가 존재하고, 보일때만
        if( pd != null && pd.isShowing() ) {
            pd.dismiss();
        }
    }
    //====================================입력체크===================================================
    public boolean isValidate(){
        if( TextUtils.isEmpty(username.getText().toString())) {
            username.setError("이메일을 입력하세요");
            return false;
        }else{
            username.setError(null);
        }
        if( TextUtils.isEmpty(password.getText().toString()) ) {
            password.setError("비밀번호를 입력하세요");
            return false;
        }else{
            if(password.getText().toString().length()<6){
                password.setError("비밀번호는 6자 이상이여야 합니다.");
                return false;
            }
            password.setError(null);
        }
        return true;
    }
    //====================================디비에저장==================================================
    public void onUserSaved(String emailParam){
        // 회원 정보 디비에 입력
        String id    = emailParam.split("@")[0];
        String email = emailParam;
        // 회원정보 생성
        User user = new User( id, email );
        // 디비 입력
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child("users").child(uid).setValue(user)
                .addOnCompleteListener(new OnCompleteListener(){
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            // 로그인
                        }else{

                        }
                    }
                });
    }
    //====================================중복체크===================================================
    public void onIDCheck(){

    }
    public void onNickNameCheck(){

    }
    //====================================프로필사진 등록=============================================
    public void onPhoto(View view) {
        alert = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("사진선택")
                .setContentText("사진을 선택할 방법을 고르세요!!")
                .setConfirmText("카메라")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        onCamera();
                    }
                })
                .setCancelText("포토앨범")
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        onGallery();
                    }
                });
        alert.setCancelable(true);
        alert.show();
    }
    public void onCamera() {
        //크롭작업을 하기 위해 옵션 설정(편집)
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        options.setMaxBitmapSize(1024 * 1024 * 1); //1MB 언더

        RxPaparazzo.takeImage(this)
                .size(new SmallSize())//사이즈(small,Screem,Original,CustomMax
                .crop(options)        //편집
                .useInternalStorage() //내부저장
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // See response.resultCode() doc
                    //실패처리
                    if (response.resultCode() != RESULT_OK) {
                        //response.targetUI().showUserCanceled();
                        return;
                    }
                    Log.e("Camera", response.data());
                    loadImage(response.data());
                });

    }
    public void onGallery() {
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        options.setMaxBitmapSize(1024 * 1024 * 1); //1MB 언더

        RxPaparazzo.takeImage(this)
                .size(new SmallSize())//사이즈(small,Screem,Original,CustomMax
                .crop(options)        //편집
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // See response.resultCode() doc
                    //실패처리
                    if (response.resultCode() != RESULT_OK) {
                        //response.targetUI().showUserCanceled();
                        return;
                    }
                    Log.e("PH", response.data());
                    loadImage(response.data());
                });
    }
    ImageButton imageButton;
    public void loadImage(String path) {

        alert.dismissWithAnimation();
        //이미지뷰에 이미지를 세팅
        String url = "file://" + path;
        Log.i("PP", "=======================");
        Log.i("PP", path);
        Log.i("PP", url);
        Log.i("PP", "=======================");
        Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this).setIndicatorsEnabled(true);
        Picasso.with(this).invalidate(url);
        Picasso.with(this).load(new File(path)).into(imageButton);

//        //파일 업로드======================================
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        //나무기둥의 주소
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://photoprocessing-6c7fb.appspot.com");
//        //내 프로필 사진이 등록되는 최종 경로
//        Uri uri = Uri.fromFile(new File(path));
//        //내 프로필 사진의 경로 gs:.../profile/IMG-...
//        String uploadName = "profile/" + uri.getLastPathSegment();
//        //기둥의 가지 등록
//        StorageReference riversRef = storageRef.child(uploadName);
//        //업로드
//        UploadTask uploadTask = riversRef.putFile(uri);
//        //이벤트 등록 및 처리
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // 실패 -> 재시도를 하게끔 유도!!
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                //downloadUrl.toString() => 프로필 정보로 업데이트!!
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                //진행율
//                Log.e("KK", taskSnapshot.toString());
//            }
//        });

    }
    //=====================================온클릭====================================================
    public void toMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void toSignUpSucess(View view){
        onJoin();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void toPolicy(View view){
        Intent intent = new Intent(this, SignUpPolicyActivity.class);
        startActivity(intent);
    }
}
