package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.database.dbflow.entity.FavoriteEntity;
import com.movile.next.seriestracker.database.dbflow.entity.FavoriteEntity$Adapter;
import com.movile.next.seriestracker.listener.OnFavoriteClickListener;

/**
 * Created by movile on 05/07/15.
 */
public class FavoritesAdapter extends CursorAdapter {

    private OnFavoriteClickListener mListener;

    public FavoritesAdapter(Context context, OnFavoriteClickListener listener) {
        super(context, null, 0);
        mListener = listener;
    }

    public void updateFavorites(Cursor favoritesCursor) {
        swapCursor(favoritesCursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        FavoriteEntity$Adapter entityAdapter = new FavoriteEntity$Adapter();
        final FavoriteEntity entity = new FavoriteEntity();
        entityAdapter.loadFromCursor(cursor, entity);

        holder.title().setText(entity.title());
        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.favoriteClick(entity.slug());
            }
        });
    }

    public static class ViewHolder {

        private View mRoot;
        private TextView title;

        public ViewHolder(View view) {
            mRoot = view;
            title = (TextView) mRoot.findViewById(R.id.favorite_item_title);
        }

        public View root() {
            return mRoot;
        }
        public TextView title() {
            return title;
        }

    }
}
