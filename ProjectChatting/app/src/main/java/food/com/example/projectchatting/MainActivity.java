package food.com.example.projectchatting;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.WrapperListAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ChatListVO> array;
   // RecyclerView list;
    ChatListAdapter adapter;
    Dialog MyDialog;
    Button start, close;
    SwipeMenuListView list;
    FirebaseDatabase db;
    DatabaseReference ref;
    Test t = Test.getTest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        // BottomNavigationView 메뉴를 선택할 때마다 위치가 변하지 않도록
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);


        db = FirebaseDatabase.getInstance();
        ref = db.getReference("chat");



        array = new ArrayList<ChatListVO>();
        list = findViewById(R.id.list);
        list.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        //list.setLayoutManager(manager);


// 데이터베이스 읽기 #3. ChildEventListener
        FirebaseDatabase.getInstance().getReference().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // Log.d("MainActivity", "ChildEventListener - onChildAdded : " + dataSnapshot.getValue());
              //  String z = dataSnapshot.child(t.getId()).getValue(String.class);
              //  System.out.println(z);

                for (DataSnapshot child : dataSnapshot.child(t.getId()).getChildren()) {
                    String test = child.getValue().toString();

                    String result = test.substring(31, 42);

                    ChatListVO vo=new ChatListVO();
                    vo.setChatName(result);
                    array.add(vo);

                }

                adapter = new ChatListAdapter();
                list.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

       // RecyclerView.LayoutManager manager = new LinearLayoutManager(this);



        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                switch (menu.getViewType()) {
                    case 0:
                        createMenu1(menu);
                        break;
                }

            }
            private void createMenu1(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,
                        0x5E)));
                item1.setWidth(200);
                item1.setIcon(R.drawable.ic_delete_black_24dp);
                menu.addMenuItem(item1);

            }

        };
        list.setMenuCreator(creator);
    }

    class ChatListAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_chatlist, parent, false);
            }
            TextView chatName = convertView.findViewById(R.id.chatName);
            TextView chatTime  = convertView.findViewById(R.id.chatTime );
            RelativeLayout item= convertView.findViewById(R.id.item);
            item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatRoomActivity.class);
                intent.putExtra("number",array.get(position).getChatName());
                intent.putExtra("me",t.getId());
                startActivity(intent);
            }
        });
            System.out.println("asdasdasdasd");
            chatName.setText(array.get(position).getChatName());
        // chatTime.setText(array.get(position).getChatTime());
            return convertView;
    }
    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chatlist_menu,menu);
        MenuItem search = menu.findItem(R.id.search);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.chat:
                    MyCustomAlertDialog();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void MyCustomAlertDialog(){
        MyDialog =new Dialog(MainActivity.this);
        MyDialog.setContentView(R.layout.customdialog);
        MyDialog.setTitle("");

        start = (Button) MyDialog.findViewById(R.id.start);
        close = (Button) MyDialog.findViewById(R.id.close);

        start.setEnabled(true);
        close.setEnabled(true);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
                Intent intent=new Intent(MainActivity.this, OpenChatActivity.class);
                startActivity(intent);

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });
        MyDialog.show();



    }
}
