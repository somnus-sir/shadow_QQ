package com.whn.group;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whn.whn_qq_datebase.R;

public class Group_List_Adapter extends BaseAdapter {

	private Context c;
	private ArrayList<Person_Group> p ;
	public Group_List_Adapter(Context context, ArrayList<Person_Group> list) {
		p = list;
		c = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = View.inflate(c, R.layout.item_persons, null);
		} else {
			view = convertView;// 将需要显示的填充到view中
		}
		//获取数据
		Person_Group person = p.get(position);
		
		ImageView touxiang = (ImageView) view.findViewById(R.id.touxiang_iv_item_persons);
		TextView quanxian = (TextView) view.findViewById(R.id.quanxian_tv_item_persons);
		TextView account = (TextView) view.findViewById(R.id.account_tv_item_persons);
		
		touxiang.setBackgroundResource(Integer.parseInt(person.touxiang));
		quanxian.setText(person.quanxian);
		account.setText(person.account);
		return view;
	}

	@Override
	public int getCount() {
		return p.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
