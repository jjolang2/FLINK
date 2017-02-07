package com.example.juhyeon.flink.Network;

/**
 * Created by Juhyeon on 2017-01-20.
 */

public class Network {
    private static Network ourInstance = new Network();

    public static Network getInstance() {
        return ourInstance;
    }

    private Network() {

    }

    ////////////////////////////////////////////////////////////////////////////
    //통신큐
    ////////////////////////////////////////////////////////////////////////////
    //통신 API


    /*public void sendAddress(Context context, ArrayList<AddressModel> cc) {
        U.getInstance().log("서버전송");
        //전송 : {header:{code:AD}, body[{uid:xx, name:xx, tel:xx}]}
        //응답 : {code:1, msg:"ok"}

        //1. 파라미터 구성
        //2. 요청구성
        //3. 통신
        //4. 응답(성공, 실패)
        ReqInsertAddress reqInsertAddress = new ReqInsertAddress();
        ReqHeader reqHeader = new ReqHeader();
        reqInsertAddress.setHeader(reqHeader);
        reqInsertAddress.setBody(cc);
        // 2. 요청객체 준비
        try {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            "http://52.78.246.63:3000/insertAddr",
                            new JSONObject(new Gson().toJson(reqInsertAddress)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.i("서버반응", response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );
            // 3. 요청 (타임아웃 설정 추가 필요)
            U.getInstance().getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
        }
    }

    //전화번호 검색
    public void searchHp(Context context, String tel) {
        //전송 : {header:{code:SH}, body{hp:xx}}
        //응답 : {code:1, msg:{name:xxx, .... nickname:xxx}}
        //1. 파라미터 구성

        ReqSearchHp reqSearchHp = new ReqSearchHp();
        ReqHeader reqHeader = new ReqHeader();
        ReqSearchHpBody reqSearchHpBody = new ReqSearchHpBody(tel, ContactsService.uid);
        reqSearchHp.setHeader(reqHeader);
        reqSearchHp.setBody(reqSearchHpBody);

        //2. 요청생성 :/selectTel

        try {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(
                            Request.Method.POST,
                            "http://52.78.246.63:3000/selectTel",
                            new JSONObject(new Gson().toJson(reqSearchHp)),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //4. 응답
                                    ResSearchHp resSearchHp =
                                            new Gson().fromJson(response.toString(), ResSearchHp.class);
                                    //이벤트 발생 등록
                                    OTTOBus.getInstance().getBus().post(resSearchHp);
                                    Log.i("서버반응", response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }
                    );
            // 3. 요청 (타임아웃 설정 추가 필요)
            U.getInstance().getRequestQueue(context).add(jsonObjectRequest);
        } catch (Exception e) {
        }
    }*/
}
