package com.chenzhanyang.mvpsample.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenzhanyang.mvpsample.R;
import com.chenzhanyang.mvpsample.api.ApiUrl;
import com.chenzhanyang.mvpsample.model.bean.PicListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Auther: chen_zhanyang.
 * Emaol: zhanyang.chen@gmail.com
 * Data: 2017/4/5.
 */

public class PicListAdapter extends RecyclerView.Adapter<PicListAdapter.ViewHolder> {
    private static final String TAG = "PicListAdapter";

    private List<PicListBean.TngouBean> mData;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_pic_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PicListBean.TngouBean itemTngouBean = mData.get(position);
        String imgUrl = itemTngouBean.getImg();
        Glide.with(holder.mIv.getContext())
                .load(ApiUrl.TIAN_GOU_IMG_BASE_URL + imgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.mIv);
        holder.mTitle.setText(itemTngouBean.getTitle());
//        holder.mDescription.setText(itemTngouBean.get);
//        Log.i(TAG, "onBindViewHolder: " + ApiUrl.TIAN_GOU_IMG_BASE_URL + imgUrl);
    }

    @Override
    public int getItemCount() {
//        Log.i(TAG, "getItemCount: ==" + (mData == null ? 0 : mData.size()));
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fragment_piclist_item_iv)
        ImageView mIv;
        @BindView(R.id.fragment_piclist_item_title_tv)
        TextView mTitle;
        @BindView(R.id.fragment_piclist_item_desc_tv)
        TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<PicListBean.TngouBean> data) {
        this.mData = data;
        notifyDataSetChanged();
//        Log.i(TAG, "setData: == 刷新数据了");
    }
}
