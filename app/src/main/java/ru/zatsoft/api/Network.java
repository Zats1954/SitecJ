package ru.zatsoft.api;

import android.util.Base64;
import android.widget.Toast;

import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.zatsoft.pojo.Otvet;
import ru.zatsoft.sitecj.BuildConfig;



// interface WebServer {
//   @GET("/UKA_TRADE/hs/MobileClient/{imei}/authentication/")
//   Call<Otvet> authentication(@Path("imei") String imei);
//}

public class Network {
     protected static String IMEINumber;
     public Network(String IMEINumber){
         this.IMEINumber = IMEINumber;
     }
//
//   //      Соединение с  сервером
//   HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
//
//   // concatenate username and password with colon for authentication
//   String credentials = Credentials.basic(username ,password);
//   // create Base64 encodet string
//   final String basic =
//           "basic " + Base64.encode(credentials.getBytes(), Base64.NO_WRAP);
//
//   OkHttpClient.Builder builder = new OkHttpClient.Builder();
//   builder = configureToIgnoreCertificate(builder);
//   OkHttpClient.Builder client = builder
//           .addInterceptor(interceptor)
//           .addInterceptor(new Interceptor() {
//              @Override
//              public okhttp3.Response intercept(Chain chain) throws IOException {
//                 Request newRequest  = chain.request().newBuilder()
//                         .header("Authorization",credentials)
//                         .build();
//                 return chain.proceed(newRequest);
//              }
//           });
//
//   Retrofit retrofit =
//           new Retrofit.Builder()
//                   .baseUrl(BuildConfig.BASE_URL)
//                   .addConverterFactory(GsonConverterFactory.create())
//                   .client(client.build())
//                   .build();
//
//   WebServer webServer = retrofit.create(WebServer.class);
//
//   String uid = "http";
//   String passw = "http";
//   String copyFromDevice = "false";
//   String nfc = " ";
//
////      Авторизация на сервере
//        webServer.authentication(IMEINumber).enqueue(new Callback<Otvet>  (){
//      @Override
//      public void onResponse(Call< Otvet >   call, Response<Otvet>   response) {
//         System.out.println(response.body().getCode());
//      }
//      @Override
//      public void onFailure(Call<Otvet> call, Throwable t){
//         System.out.println(call.toString());
//         Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();}
//
//   });
//
//   private static OkHttpClient.Builder configureToIgnoreCertificate(OkHttpClient.Builder builder) {
//      try {
//         // Create a trust manager that does not validate certificate chains
//         final TrustManager[] trustAllCerts = new TrustManager[] {
//                 new X509TrustManager() {
//                    @Override
//                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
//                    }
//
//                    @Override
//                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
//                    }
//
//                    @Override
//                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                       return new java.security.cert.X509Certificate[]{};
//                    }
//                 }
//         };
//
//         // Install the all-trusting trust manager
//         final SSLContext sslContext = SSLContext.getInstance("SSL");
//         sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//         // Create an ssl socket factory with our all-trusting manager
//         final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//
//         builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
//         builder.hostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//               return true;
//            }
//         });
//      } catch (Exception e) {
//         System.out.println("Exception while configuring IgnoreSslCertificate" + e);
//      }
//      return builder;
//   }
}
