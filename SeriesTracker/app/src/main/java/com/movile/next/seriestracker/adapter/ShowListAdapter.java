package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.listener.OnShowClickListener;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.model.Show;

public class ShowListAdapter extends ArrayAdapter<Show> {

    private OnShowClickListener mListener;

    public ShowListAdapter(Context context, OnShowClickListener listener) {
        super(context, R.layout.show_list_item_fragment, 0);
        mListener = listener;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            int resource = R.layout.show_list_item_fragment;

            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);

            holder = new ViewHolder(view);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        populateViewFromHolder(holder, position);

        return view;
    }

    private void populateViewFromHolder(ViewHolder holder, int position) {

        final Show show = getItem(position);

        if (show != null)
        {
            Glide.with(getContext()).load(show.images().poster().get(Images.ImageSize.THUMB)).centerCrop().into(holder.mShowThumbnail);
            holder.mRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.showClick(show);
                }
            });
        }

    }

    public static class ViewHolder {

        View mRoot;
        ImageView mShowThumbnail;

        public ViewHolder(View root) {
            mRoot = root;
            mShowThumbnail = (ImageView) root.findViewById(R.id.show_item_thumbnail);
        }
    }
}
