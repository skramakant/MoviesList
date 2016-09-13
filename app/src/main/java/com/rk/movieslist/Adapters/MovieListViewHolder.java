package com.rk.movieslist.Adapters;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rk.movieslist.MainActivity;
import com.rk.movieslist.R;

/**
 * Created by ramakant on 14/9/16.
 */
public class MovieListViewHolder extends RecyclerView.ViewHolder {

    private android.widget.LinearLayout.LayoutParams layoutParams;
    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    private ImageView image;

    public MovieListViewHolder(View itemView, Context context) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.poster);
        image.setTag("POSTER");
        image.setOnLongClickListener(new MyClickListener(context));
        MainActivity.dragPlace.setOnDragListener(new MyDragListener(context));
    }

    private class MyClickListener implements View.OnLongClickListener {
        private Context context;

        public MyClickListener(Context context) {
            this.context = context;
        }

        // called when the item is long-clicked
        @Override
        public boolean onLongClick(View view) {
            // create it from the object's tag

            ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());
            String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag( data, //data to be dragged
                    shadowBuilder, //drag shadow
                    view, //local data about the drag and drop operation
                    0   //no needed flags
            );
            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {

        private Context context;
        //Drawable normalShape = context.getResources().getDrawable(R.drawable.normal_shape);
        //Drawable targetShape = context.getResources().getDrawable(R.drawable.target_shape);
        public MyDragListener(Context context) {
            this.context = context;
        }


        @Override
        public boolean onDrag(View v, DragEvent event) {
            // Handles each of the expected events
            switch (event.getAction()) {
                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackground(targetShape);   //change the shape of the view
                    break;
                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackground(normalShape);   //change the shape of the view back to normal
                    break;
                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    // if the view is the bottomlinear, we accept the drag item
                    if(v == MainActivity.dragPlace) {
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);
                        LinearLayout containView = (LinearLayout) v;
                        containView.addView(view);
                        view.setVisibility(View.VISIBLE);

                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        //Context context = getApplicationContext();
                        Toast.makeText(context, "You can't drop the image here", Toast.LENGTH_LONG).show();
                        break;
                    }

                    break;
                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackground(normalShape);   //go back to normal shape
                default:
                    break;

            }
            return true;
        }

    }

}


