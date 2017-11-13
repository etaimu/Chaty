package com.etaimuallem.chaty;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.etaimuallem.chaty.models.UserChatMessage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {


    @BindView(R.id.etMessage)
    EditText etMessage;
    @BindView(R.id.btnSend)
    Button btnSend;
    @BindView(R.id.rvChat)
    RecyclerView rvChat;
    //    @BindView(R.id.btnCamera)
//    Button btnCamera;
    Unbinder unbinder;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance(String tableName) {

        Bundle args = new Bundle();
        args.putString("tableName", tableName);
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        String tableName = arguments.getString("tableName");
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setStackFromEnd(true);
        rvChat.setLayoutManager(lm);
        Query chatQuery = FirebaseDatabase.getInstance().getReference(tableName);
        ChatAdapter chatAdapter = new ChatAdapter(chatQuery);
        rvChat.setAdapter(chatAdapter);
        //rvChat.scrollToPosition(chatAdapter.getItemCount()-1);
        //rvChat.smoothScrollToPosition(chatAdapter.getItemCount()-1);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnSend)
    public void onBtnSendClicked() {
        if (etMessage.getText().toString().equals("") || etMessage == null) {
        } else {
            String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
            Bundle arguments = getArguments();
            String tableName = arguments.getString("tableName");
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/YY");
            String formattedDate = df.format(c.getTime());
            FirebaseDatabase.getInstance().
                    getReference(tableName).
                    push().
                    setValue(new UserChatMessage(userName, etMessage.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid(), formattedDate.toString()));
            etMessage.setText(null);
        }
    }

    public static class ChatAdapter extends FirebaseRecyclerAdapter<UserChatMessage, ChatAdapter.ChatViewHolder> {
        public ChatAdapter(Query query) {
            super(UserChatMessage.class, R.layout.chat_item, ChatViewHolder.class, query);
        }

        @Override
        protected void populateViewHolder(ChatViewHolder viewHolder, UserChatMessage model, int position) {
            viewHolder.tvDateAndTime.setText(model.getMessageTime());
            viewHolder.tvChat.setText(model.getMessage());
            if (model.getUserId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                viewHolder.tvChat.setBackgroundColor(0xff00dd00);
                viewHolder.con.setBackgroundColor(0xff00dd00);
                viewHolder.tvDateAndTime.setBackgroundColor(0xff00dd00);
            }
        }

        @Override
        public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return super.onCreateViewHolder(parent, viewType);
        }

        public static class ChatViewHolder extends RecyclerView.ViewHolder {
            TextView tvChat;
            TextView tvDateAndTime;
            ConstraintLayout con;

            //CardView card;
            //TextView tvChatRight;
            //TextView tvDateAndTimeRight;
            //TextView tvChatLeft;
            //TextView tvDateAndTimeLeft;
            //ConstraintLayout conRight;
            public ChatViewHolder(View itemView) {
                super(itemView);
                tvChat = (TextView) itemView.findViewById(R.id.tvChat);
                tvDateAndTime = (TextView) itemView.findViewById(R.id.tvDataTime);
                con = (ConstraintLayout) itemView.findViewById(R.id.con);
                //card = (CardView) itemView.findViewById(R.id.card);
                //conRight = (ConstraintLayout) itemView.findViewById(R.id.conRight);
                //tvChatRight = (TextView) itemView.findViewById(R.id.tvChatRight);
                //tvDateAndTimeRight = (TextView) itemView.findViewById(R.id.tvDataTimeRight);
                //tvChatLeft = (TextView) itemView.findViewById(R.id.tvChatLeft);
                //tvDateAndTimeLeft = (TextView) itemView.findViewById(R.id.tvDataTimeLeft);
            }
        }
    }
}
