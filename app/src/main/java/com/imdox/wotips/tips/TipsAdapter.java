package com.imdox.wotips.tips;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imdox.wotips.R;
import com.imdox.wotips.support.AppController;

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
            return AppController.TYPE_TIPS;
        }else if (dataList.get(position) instanceof UserTips) {
            return AppController.USER_TIPS_TYPE;
        }
        return -1;
    }

    // Invoked by layout manager to replace the contents of the views
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType=holder.getItemViewType();
        switch (viewType){
            case AppController.TYPE_TIPS:
                TipsData tipsData=(TipsData) dataList.get(position);
                ((DetailsViewHolder)holder).showTipsDetails(tipsData,position);
                break;
            case AppController.USER_TIPS_TYPE:
                UserTips userTips=(UserTips) dataList.get(position);
                ((userTipsViewHolder)holder).userTipsShowDetails(userTips,position);
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
            case AppController.TYPE_TIPS:
                layout= R.layout.tips_item;
                View modelView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                viewHolder=new DetailsViewHolder(modelView);
                break;
            case AppController.USER_TIPS_TYPE:
                layout= R.layout.user_tips_item;
                View userTipsView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                viewHolder=new userTipsViewHolder(userTipsView);
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

    public class userTipsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtHeading,txtTips,txtUser;

        public userTipsViewHolder(View itemView) {
            super(itemView);
            // Initiate view
            txtHeading =(TextView)itemView.findViewById(R.id.txtHeading);
            txtTips = (TextView) itemView.findViewById(R.id.txtTips);
            txtUser = (TextView) itemView.findViewById(R.id.txtUser);

            txtHeading.setTypeface(AppController.getDefaultBoldFont(context));
            txtTips.setTypeface(AppController.getDefaultFont(context));
            txtUser.setTypeface(AppController.getDefaultFont(context));
        }

        public void userTipsShowDetails(final UserTips userTips, final int position){
            txtHeading.setText((position+1)+". "+ userTips.getTipTitle());
            txtTips.setText(userTips.getTipContent());
            txtUser.setText("Added by "+userTips.getUserDisplayName()+", on "+userTips.getStrCreatedDate());
        }
    }
}

