package com.example.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.listner.CancelListener;
import kr.co.bootpay.listner.CloseListener;
import kr.co.bootpay.listner.ConfirmListener;
import kr.co.bootpay.listner.DoneListener;
import kr.co.bootpay.listner.ErrorListener;
import kr.co.bootpay.listner.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.login.RemoteService.BASE_URL;

public class HWJ_BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    RelativeLayout charge1, charge2, charge3, charge4, charge5, charge6;
    TextView userpoint;
    static int point;
    Retrofit retrofit;
    RemoteService rs;
    String strUser;

    //부트페이
    private int stuck = 10;

    public static HWJ_BottomSheetDialog getInstance(int intPoint) {
        point = intPoint;
        return new HWJ_BottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hwj_bottom_sheet_dialog, container, false);

        //로그인부터 유저값 받아오기
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("userid",MODE_PRIVATE);
        strUser = sharedPreferences.getString("userid","");

        userpoint = view.findViewById(R.id.userpoint);
        userpoint.setText(point + "p");

        charge1 = view.findViewById(R.id.charge1);
        charge2 = view.findViewById(R.id.charge2);
        charge3 = view.findViewById(R.id.charge3);
        charge4 = view.findViewById(R.id.charge4);
        charge5 = view.findViewById(R.id.charge5);
        charge6 = view.findViewById(R.id.charge6);

        charge1.setOnClickListener(this);
        charge2.setOnClickListener(this);
        charge3.setOnClickListener(this);
        charge4.setOnClickListener(this);
        charge5.setOnClickListener(this);
        charge6.setOnClickListener(this);

        //서버 연결
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        //부트페이
        BootpayAnalytics.init(getActivity(), "5d2d7a04b6d49c613d079228");
        return view;
    }

    @Override
    public void onClick(View v) {
        // 결제호출
        BootUser bootUser = new BootUser().setPhone("010-1234-5678");
        BootExtra bootExtra = new BootExtra().setQuotas(new int[]{0, 2, 3});

        switch (v.getId()) {
            case R.id.charge1:
                Bootpay.init(getActivity())
                        .setApplicationId("5d2d7a04b6d49c613d079228") // 해당 프로젝트(안드로이드)의 application id 값
                        .setPG(PG.DANAL) // 결제할 PG 사
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setMethod(Method.CARD) // 결제수단
                        .setName("PickChat 50p") // 결제할 상품명
                        .setOrderId("1001") // 결제 고유번호expire_month
                        .setPrice(2500) // 결제할 금액
                        .addItem("픽챗 50p", 1, "ITEM_CODE_POINT50", 2500) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {
                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Call<Void> call = rs.updatePoint(strUser, 50); //아이디 값 받아오는 구간
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful()){
                                            //결제금액 총결산
                                            Call<Void> call2 = rs.insertProfit(strUser, 2500); //아이디 값 받아오는 구간
                                            call2.enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call2, Response<Void> response) {
                                                    Toast.makeText(getContext(), "충전 완료", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onFailure(Call<Void> call2, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {
                                Toast.makeText(getContext(), "결제가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Toast.makeText(getContext(), "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();
                break;
            case R.id.charge2:
                Bootpay.init(getActivity())
                        .setApplicationId("5d2d7a04b6d49c613d079228") // 해당 프로젝트(안드로이드)의 application id 값
                        .setPG(PG.DANAL) // 결제할 PG 사
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setMethod(Method.CARD) // 결제수단
                        .setName("PickChat 100p") // 결제할 상품명
                        .setOrderId("1002") // 결제 고유번호expire_month
                        .setPrice(5000) // 결제할 금액
                        .addItem("픽챗 100p", 1, "ITEM_CODE_POINT100", 5000) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {
                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Call<Void> call = rs.updatePoint(strUser, 100); //아이디 값 받아오는 구간
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful()){
                                            //결제금액 총결산
                                            Call<Void> call2 = rs.insertProfit(strUser, 2500); //아이디 값 받아오는 구간
                                            call2.enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call2, Response<Void> response) {
                                                    Toast.makeText(getContext(), "충전 완료", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onFailure(Call<Void> call2, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });

                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {
                                Toast.makeText(getContext(), "결제가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Toast.makeText(getContext(), "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();
                break;
            case R.id.charge3:
                Bootpay.init(getActivity())
                        .setApplicationId("5d2d7a04b6d49c613d079228") // 해당 프로젝트(안드로이드)의 application id 값
                        .setPG(PG.DANAL) // 결제할 PG 사
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setMethod(Method.CARD) // 결제수단
                        .setName("PickChat 200p") // 결제할 상품명
                        .setOrderId("1003") // 결제 고유번호expire_month
                        .setPrice(10000) // 결제할 금액
                        .addItem("픽챗 200p", 1, "ITEM_CODE_POINT200", 10000) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {
                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Call<Void> call = rs.updatePoint(strUser, 200); //아이디 값 받아오는 구간
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful()){
                                            //결제금액 총결산
                                            Call<Void> call2 = rs.insertProfit(strUser, 2500); //아이디 값 받아오는 구간
                                            call2.enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call2, Response<Void> response) {
                                                    Toast.makeText(getContext(), "충전 완료", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onFailure(Call<Void> call2, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });

                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {
                                Toast.makeText(getContext(), "결제가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Toast.makeText(getContext(), "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();
                break;
            case R.id.charge4:
                Bootpay.init(getActivity())
                        .setApplicationId("5d2d7a04b6d49c613d079228") // 해당 프로젝트(안드로이드)의 application id 값
                        .setPG(PG.DANAL) // 결제할 PG 사
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setMethod(Method.CARD) // 결제수단
                        .setName("PickChat 500p") // 결제할 상품명
                        .setOrderId("1004") // 결제 고유번호expire_month
                        .setPrice(25000) // 결제할 금액
                        .addItem("픽챗 500p", 1, "ITEM_CODE_POINT500", 25000) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {
                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Call<Void> call = rs.updatePoint(strUser, 500); //아이디 값 받아오는 구간
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful()){
                                            //결제금액 총결산
                                            Call<Void> call2 = rs.insertProfit(strUser, 2500); //아이디 값 받아오는 구간
                                            call2.enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call2, Response<Void> response) {
                                                    Toast.makeText(getContext(), "충전 완료", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onFailure(Call<Void> call2, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });

                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {
                                Toast.makeText(getContext(), "결제가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Toast.makeText(getContext(), "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();
                break;
            case R.id.charge5:
                Bootpay.init(getActivity())
                        .setApplicationId("5d2d7a04b6d49c613d079228") // 해당 프로젝트(안드로이드)의 application id 값
                        .setPG(PG.DANAL) // 결제할 PG 사
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setMethod(Method.CARD) // 결제수단
                        .setName("PickChat 1000p") // 결제할 상품명
                        .setOrderId("1005") // 결제 고유번호expire_month
                        .setPrice(50000) // 결제할 금액
                        .addItem("픽챗 1000p", 1, "ITEM_CODE_POINT1000", 50000) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {
                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Call<Void> call = rs.updatePoint(strUser, 1000); //아이디 값 받아오는 구간
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful()){
                                            //결제금액 총결산
                                            Call<Void> call2 = rs.insertProfit(strUser, 2500); //아이디 값 받아오는 구간
                                            call2.enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call2, Response<Void> response) {
                                                    Toast.makeText(getContext(), "충전 완료", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onFailure(Call<Void> call2, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });

                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {
                                Toast.makeText(getContext(), "결제가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Toast.makeText(getContext(), "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();
                break;
            case R.id.charge6:
                Bootpay.init(getActivity())
                        .setApplicationId("5d2d7a04b6d49c613d079228") // 해당 프로젝트(안드로이드)의 application id 값
                        .setPG(PG.DANAL) // 결제할 PG 사
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setMethod(Method.CARD) // 결제수단
                        .setName("PickChat 2000p") // 결제할 상품명
                        .setOrderId("1006") // 결제 고유번호expire_month
                        .setPrice(100000) // 결제할 금액
                        .addItem("픽챗 2000p", 1, "ITEM_CODE_POINT2000", 100000) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {
                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Call<Void> call = rs.updatePoint(strUser, 2000); //아이디 값 받아오는 구간
                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful()){
                                            //결제금액 총결산
                                            Call<Void> call2 = rs.insertProfit(strUser, 2500); //아이디 값 받아오는 구간
                                            call2.enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call2, Response<Void> response) {
                                                    Toast.makeText(getContext(), "충전 완료", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onFailure(Call<Void> call2, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });

                                Log.d("done", message);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {
                                Toast.makeText(getContext(), "결제가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Toast.makeText(getContext(), "알 수 없는 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {
                                        Log.d("close", "close");
                                    }
                                })
                        .request();
                break;
        }
    }
}