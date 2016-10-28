package com.mvp.framework.module.base.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseListAdapter
 * @author create by Tang
 * @date date 16/10/21 下午3:02
 * @Description: 只支持一种布局
 * 要支持多布局请自己实现
 * @Data: 列表项实体类
 */

public abstract class BaseListAdapter<Data> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VIEW_TYPE_HEADER = 1;
    private static final int VIEW_TYPE_FOOTER = 2;
    private static final int VIEW_TYPE_NORMAL = 3;

    private View headerView;
    private View footerView;

    private int headerViewId;
    private int footerViewId;

    private OnItemClickListener l;

    //数据源
    private List<Data> mData = new ArrayList<>();


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        if (headerViewId != 0){
            onCreateHeaderView(headerViewId,recyclerView.getContext(),recyclerView);
        }

        if (footerViewId != 0){
            onCreateFooterView(footerViewId,recyclerView.getContext(),recyclerView);
        }

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == VIEW_TYPE_HEADER
                            || getItemViewType(position) == VIEW_TYPE_FOOTER){
                        return 1;
                    }
                    return gridLayoutManager.getSpanCount();
                }

            });

        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) lp;
            slp.setFullSpan(getItemViewType(holder.getLayoutPosition()) == VIEW_TYPE_HEADER
                    || getItemViewType(holder.getLayoutPosition()) == VIEW_TYPE_FOOTER);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER ){
            return new Holder(headerView);
        }

        if (viewType == VIEW_TYPE_FOOTER){
            return new Holder(footerView);
        }

        return onCreate(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_HEADER){
            return;
        }
        if (getItemViewType(position) == VIEW_TYPE_FOOTER){
            return ;
        }

        final int mPosition = getRealPosition(holder);
        final Data data ;

        data = mData.get(mPosition);
        onBind(holder,mPosition,data);

        if (l != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(mPosition,data);
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        if (headerView == null){
            if (footerView == null){
                return mData.size();
            }

            return mData.size() + 1;
        }
        if (footerView == null ){
            return mData.size() + 1;
        }
        return mData.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (headerView != null && position == 0){
            return VIEW_TYPE_HEADER;
        }

        if (footerView != null && position == getItemCount() - 1){
            return VIEW_TYPE_FOOTER;
        }

        return VIEW_TYPE_NORMAL;
    }

    public void setHeaderView(View headerView){
        this.headerView = headerView;
    }

    public void setHeaderView(int layoutId){
        this.headerViewId = layoutId;
    }

    private void onCreateHeaderView(int headerViewId,Context context,ViewGroup parent){
        View headerView = LayoutInflater.from(context).inflate(headerViewId,parent,false);
        this.headerView = headerView;
    }


    public void setFooterView(View footerView){
        this.footerView = footerView;
    }

    public void setFooterView(int layoutId){
        this.footerViewId = layoutId;
    }

    private void onCreateFooterView(int footerViewId,Context context,ViewGroup parent){
        View footerView = LayoutInflater.from(context).inflate(footerViewId,parent,false);
        this.footerView = footerView;
    }


    public void setData(List<Data> data){
        //这样处理是为了防止Presenter中的数据因运算改动时。影响List中的数据
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public List<Data> getData(){
        return mData;
    }


    public void removeItem(int position){
        mData.remove(position);
        notifyItemChanged(position);
    }

    public void removeItem(Data item){
        int position = mData.indexOf(item);
        mData.remove(position);
        notifyItemChanged(position);
    }

    public void addItem(Data item){
        mData.add(item);
        notifyItemChanged(mData.size() - 1);
    }


    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent, final  int viewType);
    public abstract void onBind(RecyclerView.ViewHolder holder, int realPosition, Data data);

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener<Data>{
        void onItemClick(int position,Data Data);
    }
}
