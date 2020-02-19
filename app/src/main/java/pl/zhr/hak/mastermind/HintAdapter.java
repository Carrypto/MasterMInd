package pl.zhr.hak.mastermind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HintAdapter extends RecyclerView.Adapter<HintAdapter.HintViewHolder> {


    public static class HintViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstHintNumber;
        private final TextView secondHintNumber;
        private final TextView thirdHintNumber;
        private final TextView fourthHintNumber;
        private final ImageView hintImage;

        public HintViewHolder(@NonNull View itemView) {
            super(itemView);
            this.firstHintNumber = itemView.findViewById(R.id.firstHintNumber);
            this.secondHintNumber = itemView.findViewById(R.id.secondHintNumber);
            this.thirdHintNumber = itemView.findViewById(R.id.thirdHintNumber);
            this.fourthHintNumber = itemView.findViewById(R.id.fourthHintNumber);
            this.hintImage = itemView.findViewById(R.id.hintImage);
        }
    }

    private List<Hint> mHintList;
    private final LayoutInflater mInflater;
    Context mContext;

    public HintAdapter(List<Hint> mHintList, Context context) {
        this.mHintList = mHintList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.hint_layout, parent, false);
        return new HintViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HintViewHolder holder, int position) {
        holder.firstHintNumber.setText(Integer.toString(mHintList.get(position).getFirstHintNumber()));
        holder.secondHintNumber.setText(Integer.toString(mHintList.get(position).getSecondHintNumber()));
        holder.thirdHintNumber.setText(Integer.toString(mHintList.get(position).getThirdHintNumber()));
        holder.fourthHintNumber.setText(Integer.toString(mHintList.get(position).getFourthHintNumber()));
        switch (mHintList.get(position).getBlackPins()) {
            case 0:
                switch (mHintList.get(position).getWhitePins()) {
                    case 0:
                        holder.hintImage.setImageResource(R.drawable.m0b0w);
                        break;
                    case 1:
                        holder.hintImage.setImageResource(R.drawable.m0b1w);
                        break;
                    case 2:
                        holder.hintImage.setImageResource(R.drawable.m0b2w);
                        break;
                    case 3:
                        holder.hintImage.setImageResource(R.drawable.m0b3w);
                        break;
                    case 4:
                        holder.hintImage.setImageResource(R.drawable.m0b4w);
                        break;
                }
                break;
            case 1:
                switch (mHintList.get(position).getWhitePins()) {
                    case 0:
                        holder.hintImage.setImageResource(R.drawable.m1b0w);
                        break;
                    case 1:
                        holder.hintImage.setImageResource(R.drawable.m1b1w);
                        break;
                    case 2:
                        holder.hintImage.setImageResource(R.drawable.m1b2w);
                        break;
                    case 3:
                        holder.hintImage.setImageResource(R.drawable.m1b3w);
                        break;
                }
                break;
            case 2:
                switch (mHintList.get(position).getWhitePins()) {
                    case 0:
                        holder.hintImage.setImageResource(R.drawable.m2b0w);
                        break;
                    case 1:
                        holder.hintImage.setImageResource(R.drawable.m2b1w);
                        break;
                    case 2:
                        holder.hintImage.setImageResource(R.drawable.m2b2w);
                        break;
                }
                break;
            case 3:
                switch (mHintList.get(position).getWhitePins()) {
                    case 0:
                        holder.hintImage.setImageResource(R.drawable.m3b0w);
                        break;
                    case 1:
                        holder.hintImage.setImageResource(R.drawable.m3b1w);
                        break;
                }
                break;
            case 4:
                holder.hintImage.setImageResource(R.drawable.m4b0w);
                break;
            default:
                holder.hintImage.setImageResource(R.drawable.m0b4w);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mHintList.size();
    }
}
