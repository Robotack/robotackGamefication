package com.robotack.robogamification.managers;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.robotack.robogamification.helpers.ApiConstants;
import com.robotack.robogamification.models.CheckTaskModel;
import com.robotack.robogamification.models.GenderModel;
import com.robotack.robogamification.models.InfoModel;
import com.robotack.robogamification.models.LoginUserModel;
import com.robotack.robogamification.models.SeasonDetailsModel;
import com.robotack.robogamification.models.SeasonModel;
import com.robotack.robogamification.utilities.Utils;

import java.util.HashMap;
import java.util.Map;

public class BusinessManager {

    public void registerAPI(Context context, JsonObject gsonObject, String token, final ApiCallResponse callResponse) {

        String url = ApiConstants.registerAPI + new Utils().getUserId(context);
        new ConnectionManager().PostRAW(context,gsonObject, url,token, new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                try {
                    Gson gson = new Gson();
                    String json = responseObject.toString();
                    LoginUserModel parseObject = gson.fromJson(json, LoginUserModel.class);
                    callResponse.onSuccess(parseObject, responseMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);
            }
        });
    }
    public void getUserInfoApiCall(Context context, String token, final ApiCallResponse callResponse) {
        Map<String, String> Params = new HashMap<>();
        String url = ApiConstants.loginUserAPI + new Utils().getUserId(context);
        ConnectionManager.GET(context, url, Params,token, new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                try {
                    Gson gson = new Gson();
                    String json = responseObject.toString();
                    LoginUserModel parseObject = gson.fromJson(json, LoginUserModel.class);
                    callResponse.onSuccess(parseObject, responseMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);
            }
        });
    }

    public void getInfoApiCall(Context context, String token, final ApiCallResponse callResponse) {
        Map<String, String> Params = new HashMap<>();
        String url = ApiConstants.gameInfo + new Utils().getUserId(context);
        ConnectionManager.GET(context, url, Params,token, new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                try {
                    Gson gson = new Gson();
                    String json = responseObject.toString();
                    InfoModel parseObject = gson.fromJson(json, InfoModel.class);
                    callResponse.onSuccess(parseObject, responseMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);
            }
        });
    }
    public void getGenderAPI(Context context, String token, final ApiCallResponse callResponse) {
        Map<String, String> Params = new HashMap<>();
        String url = ApiConstants.genderAPI + new Utils().getUserId(context);
        ConnectionManager.GET(context, url, Params,token, new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                try {
                    Gson gson = new Gson();
                    String json = responseObject.toString();
                    GenderModel parseObject = gson.fromJson(json, GenderModel.class);
                    callResponse.onSuccess(parseObject, responseMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);
            }
        });
    }

    public void getAvatarAPI(Context context,String id, String token, final ApiCallResponse callResponse) {
        Map<String, String> Params = new HashMap<>();
        String url = ApiConstants.avatarsAPI+id+"/" + new Utils().getUserId(context);
        ConnectionManager.GET(context, url, Params,token, new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                try {
                    Gson gson = new Gson();
                    String json = responseObject.toString();
                    GenderModel parseObject = gson.fromJson(json, GenderModel.class);
                    callResponse.onSuccess(parseObject, responseMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);
            }
        });
    }

    public void checkTaskAPI(Context context,String id, String token, final ApiCallResponse callResponse) {
        Map<String, String> Params = new HashMap<>();
        String url = ApiConstants.checkTask+id+"/" + new Utils().getUserId(context);
        ConnectionManager.GET(context, url, Params,token, new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                try {
                    Gson gson = new Gson();
                    String json = responseObject.toString();
                    CheckTaskModel parseObject = gson.fromJson(json, CheckTaskModel.class);
                    callResponse.onSuccess(parseObject, responseMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);
            }
        });
    }

    public void getUntiHistory(Context context, String token, final ApiCallResponse callResponse) {
        Map<String, String> Params = new HashMap<>();
        String url = ApiConstants.unitHistoryAPI + new Utils().getUserId(context);
        ConnectionManager.GET(context, url, Params,token, new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                try {
                    Gson gson = new Gson();
                    String json = responseObject.toString();
                    SeasonModel parseObject = gson.fromJson(json, SeasonModel.class);
                    callResponse.onSuccess(parseObject, responseMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);
            }
        });
    }

    public void getUnitDetailsAPI(Context context,String id, String token, final ApiCallResponse callResponse) {
        Map<String, String> Params = new HashMap<>();
        String url = ApiConstants.unitDetailsAPI+id+"/" + new Utils().getUserId(context);
        ConnectionManager.GET(context, url, Params,token, new ApiCallResponse() {
            @Override
            public void onSuccess(Object responseObject, String responseMessage) {
                try {
                    Gson gson = new Gson();
                    String json = responseObject.toString();
                    SeasonDetailsModel parseObject = gson.fromJson(json, SeasonDetailsModel.class);
                    callResponse.onSuccess(parseObject, responseMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                callResponse.onFailure(errorResponse);
            }
        });
    }


}
