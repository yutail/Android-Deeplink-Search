package yutailuo.androiddeeplinksearch.display;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yutailuo.androiddeeplinksearch.core.VerticalSearchResult;
import yutailuo.library.R;

/**
 * Created by yutailuo on 10/17/16.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>
        implements ISearchResultDisplayer {

    private List<VerticalSearchResult> mSearchResultList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public SearchContainerView searchContainerView;
        public ViewHolder(SearchContainerView itemView) {
            super(itemView);
            searchContainerView = itemView;
        }
    }

    public SearchResultAdapter() {
        mSearchResultList = new ArrayList<>();
    }

    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_container_view,
                parent, false);

        ViewHolder viewHolder = new ViewHolder((SearchContainerView) v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchResultAdapter.ViewHolder holder, int position) {
        holder.searchContainerView.setVerticalSearchResult(mSearchResultList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSearchResultList.size();
    }

    @Override
    public void display(VerticalSearchResult verticalSearchResult, String query) {
        mSearchResultList.add(verticalSearchResult);
        notifyDataSetChanged();
    }
}
