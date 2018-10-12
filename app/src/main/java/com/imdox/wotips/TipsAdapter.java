package com.imdox.wotips;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class TipsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> dataList = new ArrayList();
    private Context context;
    String strStatus = "0";

    // Constructor
    public TipsAdapter(Context context){
        this.context=context;
    }

    public void setAdapterData(List<Object> adapterData){
        this.dataList = adapterData;
    }

    // We need to override this as we need to differentiate
    // which type viewHolder to be attached
    // This is being called from onBindViewHolder() method
    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position) instanceof TipsData) {
            return MainActivity.TYPE_TIPS;
        }
        return -1;
    }

    // Invoked by layout manager to replace the contents of the views
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType=holder.getItemViewType();
        switch (viewType){
            case MainActivity.TYPE_TIPS:
                TipsData tipsData=(TipsData) dataList.get(position);
                ((DetailsViewHolder)holder).showTipsDetails(tipsData,position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount(){return dataList.size();}

    // Invoked by layout manager to create new views
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = 0;
        RecyclerView.ViewHolder viewHolder;
        // Identify viewType returned by getItemViewType(...)
        // and return ViewHolder Accordingly
        switch (viewType){
            case MainActivity.TYPE_TIPS:
                layout= R.layout.tips_item;
                View modelView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                viewHolder=new DetailsViewHolder(modelView);
                break;
            default:
                viewHolder=null;
                break;
        }
        return viewHolder;
    }


    // ViewHolder of object type details
    public class DetailsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtHeading,txtTips;

        public DetailsViewHolder(View itemView) {
            super(itemView);
            // Initiate view
            txtHeading =(TextView)itemView.findViewById(R.id.txtHeading);
            txtTips = (TextView) itemView.findViewById(R.id.txtTips);

            txtHeading.setTypeface(AppController.getDefaultBoldFont(context));
            txtTips.setTypeface(AppController.getDefaultFont(context));
        }

        public void showTipsDetails(final TipsData tipsData, final int position){
            txtHeading.setText((position+1)+". "+ tipsData.getStrHeading());
            txtTips.setText(tipsData.getStrValue());
        }
    }
}

