package com.estsoft.myapp.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estsoft.android.network.SafeAsyncTask;
import com.estsoft.myapp.R;
import com.estsoft.myapp.core.domain.User;
import com.estsoft.myapp.core.service.UserService;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by bit on 2016-05-31.
 */
public class UserListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UserListAdapter adapter = new UserListAdapter(getActivity());
        this.setListAdapter(adapter);

        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new FetchUserListTask().execute();
    }

    private class FetchUserListTask extends SafeAsyncTask<List<User>>{
        @Override
        public List<User> call() throws Exception {
            // data 통신 후, 결과 리턴
            List<User> userList = new UserService().fetchUserList();
            return userList;
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            //통신중 에러 발생시 이 메서드 실행(통신 실패시 실행)
            super.onException(e);
        }

        @Override
        protected void onSuccess(List<User> listUser) throws Exception {
            // call() 메서드가 리턴을 한 경우 이 메서드 실행(통신 성공시 실행)
            if(listUser.size() == 0){
                return;
            }
            // UI 업데이트
            ((UserListAdapter)getListAdapter()).add(listUser);
        }
    }
}