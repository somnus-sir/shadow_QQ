package com.whn.accounts_list;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whn.whn_qq_datebase.R;

public class Accounts_List_Adapter extends BaseAdapter{
	
	private Context context;
	private List<Accounts> datas;

	public Accounts_List_Adapter(Context c,List list){
		datas = list;
		context = c;
	
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			view = View.inflate(context, R.layout.item_list, null);
		} else {
			view = convertView;//将需要显示的填充到view中
		}

		TextView account_tv_item = (TextView) view
				.findViewById(R.id.account_tv_item);
		TextView pwd_tv_itemTextView = (TextView) view
				.findViewById(R.id.pwd_tv_item);

		account_tv_item.setText(datas.get(position).account);
		pwd_tv_itemTextView.setText(datas.get(position).pwd);

		return view;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public int getCount() {
		return datas.size();
	}
	

}
