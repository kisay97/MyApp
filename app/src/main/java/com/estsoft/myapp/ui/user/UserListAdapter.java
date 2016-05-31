package com.estsoft.myapp.ui.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.estsoft.myapp.R;
import com.estsoft.myapp.core.domain.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by bit on 2016-05-31.
 */
public class UserListAdapter extends ArrayAdapter<User> {
    private DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
            // .showImageOnLoading( R.drawable.ic_default_profile )// resource or drawable
            .showImageForEmptyUri( R.drawable.ic_default_profile )// resource or drawable
            .showImageOnFail( R.drawable.ic_default_profile )// resource or drawable
            //.resetViewBeforeLoading( false )// default
            .delayBeforeLoading( 0 )
            //.cacheInMemory( false )// default
            .cacheOnDisc( true )// false is default
            //.preProcessor(...)
            //.postProcessor(...)
            //.extraForDownloader(...)
            //.considerExifParams( false )// default
            // .imageScaleType( ImageScaleType.IN_SAMPLE_POWER_OF_2 )// default
            //.bitmapConfig( Bitmap.Config.ARGB_8888 )// default
            //.decodingOptions(...)
            //.displayer( new SimpleBitmapDisplayer() )// default
            //.handler( new Handler() )// default
            .build();

    //getView 함수에서 layout을 inflation 하기 위해서
    private LayoutInflater layoutInflater;

    public UserListAdapter(Context context){
        super(context, R.layout.row_user_list);
        layoutInflater = LayoutInflater.from(context);
    }

    public void add(List<User> list){
        if(list == null) return;

        for (User user : list) {
            add(user);
        }

        /* ArrayAdapter의 add를 호출하게 되면 notifyDataSetChanged() 생략 */
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){ //ListView 남는 View가 없다 (재사용 View가 없음)
            //Inflatation
            view = layoutInflater.inflate(R.layout.row_user_list, parent, false);
        }

        //위젯에 데이터 바인딩
        User user = getItem(position);
        ((TextView)view.findViewById(R.id.name)).setText(user.getName());

        ImageLoader.getInstance().displayImage( user.getProfilePic(), (ImageView)view.findViewById(
                R.id.profile ), displayImageOptions );

        return view;
    }
}