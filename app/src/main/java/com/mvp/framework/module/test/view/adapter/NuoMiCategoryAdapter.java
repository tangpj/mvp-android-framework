package com.mvp.framework.module.test.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.framework.R;
import com.mvp.framework.module.base.view.adapter.BaseListAdapter;
import com.mvp.framework.module.test.bean.NuoMiCategoryBean;

/**
 * @ClassName: NuoMiCategoryAdapter
 * @author create by Tang
 * @date date 16/10/24 下午5:17
 * @Description: TODO
 */
public class NuoMiCategoryAdapter extends BaseListAdapter<NuoMiCategoryBean>{


    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_item_string,parent,false);
        return new NuoMiCategoryHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, int realPosition, NuoMiCategoryBean nuoMiCategoryBean) {
        ((NuoMiCategoryHolder)holder).title.setText(nuoMiCategoryBean.cat_name);
    }

    class NuoMiCategoryHolder extends Holder{

        public TextView title;

        public NuoMiCategoryHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.text);
        }
    }


}
