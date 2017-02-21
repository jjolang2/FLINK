package com.example.juhyeon.flink.model;

/**
 * Created by Juhyeon on 2017-02-12.
 */

public class model_main {

    int uid;
    String usernamepassword, phone, nickname, homepage_url, kakaoId, kakaoToken, kakao_mail;
    boolean user_type, push;

    public model_main() {
    }

    public model_main(int uid, String usernamepassword, String phone, String nickname, String homepage_url, String kakaoId,
                      String kakaoToken, String kakao_mail, boolean user_type, boolean push) {
        this.uid = uid;
        this.usernamepassword = usernamepassword;
        this.phone = phone;
        this.nickname = nickname;
        this.homepage_url = homepage_url;
        this.kakaoId = kakaoId;
        this.kakaoToken = kakaoToken;
        this.kakao_mail = kakao_mail;
        this.user_type = user_type;
        this.push = push;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsernamepassword() {
        return usernamepassword;
    }

    public void setUsernamepassword(String usernamepassword) {
        this.usernamepassword = usernamepassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHomepage_url() {
        return homepage_url;
    }

    public void setHomepage_url(String homepage_url) {
        this.homepage_url = homepage_url;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public String getKakaoToken() {
        return kakaoToken;
    }

    public void setKakaoToken(String kakaoToken) {
        this.kakaoToken = kakaoToken;
    }

    public String getKakao_mail() {
        return kakao_mail;
    }

    public void setKakao_mail(String kakao_mail) {
        this.kakao_mail = kakao_mail;
    }

    public boolean isUser_type() {
        return user_type;
    }

    public void setUser_type(boolean user_type) {
        this.user_type = user_type;
    }

    public boolean isPush() {
        return push;
    }

    public void setPush(boolean push) {
        this.push = push;
    }
}
