package com.movile.recyclerviewsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.recyclerviewsample.R;
import com.movile.recyclerviewsample.model.Content;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Content> mContents;
    private int mLayout;

    public RecyclerAdapter(Context context, int layout) {
        mContext = context;
        mLayout = layout;
    }

    public void updateContents(List<Content> contents) {
        mContents = contents;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Content content = mContents.get(position);

        holder.title().setText(content.title());
        holder.description().setText(content.description());

        int color = position % 2 == 0 ? R.color.content_item_container_even_background : R.color.content_item_container_odd_background;
        holder.root().setBackgroundColor(mContext.getResources().getColor(color));
    }

    @Override
    public int getItemCount() {
        return mContents != null ? mContents.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private TextView mTitle;
        private TextView mDescription;

        public ViewHolder(View view) {
            super(view);
            mRoot = view;
            mTitle = (TextView) mRoot.findViewById(R.id.content_item_title);
            mDescription = (TextView) mRoot.findViewById(R.id.content_item_description);
        }

        public View root() {
            return mRoot;
        }

        public TextView title() {
            return mTitle;
        }

        public TextView description() {
            return mDescription;
        }

    }

}
