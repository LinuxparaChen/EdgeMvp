package com.tcl.smarthome.mvpapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcl.smarthome.mvpapp.R;
import com.tcl.smarthome.mvpapp.interfaces.OnItemClickListener;
import com.tcl.smarthome.mvpapp.model.bean.PicClassifyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 图片分类列表
 * Auther: 陈占洋
 * Email: zhanyang.chen@gmail.com
 * Data: 2017/3/17.
 */

public class PicClassifyAdapter extends RecyclerView.Adapter<PicClassifyAdapter.ViewHoder> {

    private List<PicClassifyBean.TngouBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public PicClassifyAdapter(List<PicClassifyBean.TngouBean> data) {
        this.mData = data;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pic_classify_list_item, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, final int position) {
        holder.mTv.setText(mData.get(position).getTitle());
        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<PicClassifyBean.TngouBean> data){
        mData = data;
        this.notifyDataSetChanged();
    }

    public List<PicClassifyBean.TngouBean> getData(){
        return mData;
    }

    /**
     * 设置点击事件监听器
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public static class ViewHoder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pic_classify_list_item)
        TextView mTv;

        public ViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
