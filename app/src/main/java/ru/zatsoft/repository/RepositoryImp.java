package ru.zatsoft.repository;

import android.os.Build;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.zatsoft.UserDB;
import ru.zatsoft.dao.UserConvertor;
import ru.zatsoft.entity.UserEntity;
import ru.zatsoft.pojo.Otvet;
import ru.zatsoft.pojo.User;
import ru.zatsoft.pojo.UserInf;
import ru.zatsoft.sitecj.BuildConfig;
import static ru.zatsoft.sitecj.MainActivity.context;
import static ru.zatsoft.sitecj.MainActivity.users;


interface WebServer {
    @GET("/UKA_TRADE/hs/MobileClient/{imei}/authentication/")
    Call<Otvet> authentication(@Path("imei") String imei);

    @GET("/UKA_TRADE/hs/MobileClient/{imei}/form/users/")
    Call<UserInf> getUsers(@Path("imei") String imei);
}

public class RepositoryImp {
    public WebServer webServer;
    private long myCode;
    private Retrofit retrofit;

    public RepositoryImp(String username, String password) {
//  Соединение с  сервером
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        String credentials = Credentials.basic(username, password);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder = configureToIgnoreCertificate(builder);
        OkHttpClient.Builder client = builder
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .header("Authorization", credentials)
                            .build();
                    return chain.proceed(newRequest);
                });

        retrofit =
                new Retrofit.Builder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client.build())
                        .build();
        webServer = retrofit.create(WebServer.class);
    }

//---------    Получение своего кода на сервере -----------------------------------
    public long authent(String IMEINumber) {
        webServer.authentication(IMEINumber).enqueue(new Callback<Otvet>() {
            @Override
            public void onResponse(@NonNull Call<Otvet> call, @NonNull Response<Otvet> response) {
                assert response.body() != null;
                getCode(response);
            }

            @Override
            public void onFailure(@NonNull Call<Otvet> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return myCode;
    }

    private void getCode(@NonNull Response<Otvet> response) {
        myCode = response.body().getCode();
//        System.out.println("My application code " + myCode);
    }

//---------------  Получение списка users из сервера ------------------------------
    public void getUsers(String IMEINumber) {
        webServer.getUsers(IMEINumber).enqueue(new Callback<UserInf>() {
            @Override
            public void onResponse(Call<UserInf> call, Response<UserInf> response) {
                users = getListUsers(response);
                Thread fillDB = new DbThread();
                fillDB.start();
            }

            @Override
            public void onFailure(Call<UserInf> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<User> getListUsers(Response<UserInf> response) {
        assert response.body() != null;
        return response.body().getUsers().getListUsers();
    }

    //----------------   Обход проверки сертификата SSL -----------------------------------
    private static OkHttpClient.Builder configureToIgnoreCertificate(OkHttpClient.Builder builder) {
        try {
            // Create a trust manager that does not validate certificate chains
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

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            System.out.println("Exception while configuring IgnoreSslCertificate" + e);
        }
        return builder;
    }
}

//----------------------- Заполнение БД из списка users -----------------------------
class DbThread extends Thread {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        super.run();
        UserDB userDB = new UserDB();
        List<UserEntity> usersEntity = new ArrayList<>();
        for (User user : users) {
            usersEntity.add(UserConvertor.toUserEntity(user));
        }
        userDB.db.userDao().insertAll(usersEntity);
//            List<User> us = userDB.getAll();
//            System.out.println("------- rrrrrrrrrrrrrrr -------" + us);
    }
}
