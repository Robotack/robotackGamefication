package com.robotack.robogamification.managers;


import static com.robotack.robogamification.helpers.ApiConstants.SDK_VERSION;

import android.content.Context;

import com.google.gson.JsonObject;
import com.grapesnberries.curllogger.CurlLoggerInterceptor;
import com.robotack.robogamification.helpers.LanguageHelper;
import com.robotack.robogamification.ui.Activites.GamificationActivity;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


/**
 * Created by moayed on 12/10/17.
 */

public class ConnectionManager {


    public static void GET(Context context, String URl, Map<String, String> Params,String token, final ApiCallResponse callResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .client(new ConnectionManager().getUserHeader(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ResponseBody> call = service.GET(URl, Params, LanguageHelper.getCurrentLanguage(context),token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body() == null) {
                            callResponse.onSuccess(response.body().toString(), response.message());
                        } else {
                            callResponse.onSuccess(response.body().string(), response.message());
                        }
                    } else {
                        callResponse.onFailure("error");
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callResponse.onFailure(t.toString());
            }
        });

    }

    public void PostRAW(Context context , JsonObject requestBody, String URl,String token, final ApiCallResponse callResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .client(new ConnectionManager().getUserHeader(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService  service = retrofit.create(APIService .class);
        Call<ResponseBody> call = service.POST_RAW(URl, requestBody,LanguageHelper.getCurrentLanguage(context),"test");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful())
                    {
                        callResponse.onSuccess(response.body().string(), response.code() + "");
                    }else {
                        callResponse.onFailure(response.code()+"");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callResponse.onFailure(t.toString());
            }
        });
    }


    public OkHttpClient getUserHeader(Context context) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS).build();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                builder.method(original.method(), original.body());
                builder.header("Accept-Language", LanguageHelper.getCurrentLanguage(context)).addHeader("sdk_version",SDK_VERSION).addHeader("android_os_version",android.os.Build.VERSION.SDK_INT+"");
                return chain.proceed(builder.build());
            }
        });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = createOkHttpClient();
        return client;

    }


    private OkHttpClient createOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public interface APIService {
        public static String BASE_URL = "https://loyalty-test.capitalbank.jo:8443/api/v1.3/";
//      public static String BASE_URL = "https://cbu-test.capitalbank.jo:8453/api/v1.3/";

        @Headers({"sdk_version:1","android_os_version:1"})
        @GET()
        public Call<ResponseBody> GET(@Url String url, @QueryMap Map<String, String> params,@Header("Accept-Language") String lang,@Header("Authorization") String Authorization);


        @Headers({"sdk_version:1","android_os_version:1"})
        @POST()
        Call<ResponseBody> POST_RAW(@Url String url, @Body JsonObject requestBody,@Header("Accept-Language") String lang,@Header("Authorization") String Authorization);

    }

}
