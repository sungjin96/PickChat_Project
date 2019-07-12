package food.com.example.projectchatting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL="http://192.168.0.5:3000/user/";

    @GET("list")
    Call<List<UserVO>> listUser();

    @GET("read/{uid}")
    Call<UserVO> listUser(@Path("uid") String uid);
}
