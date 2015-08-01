package demo.layout.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	@SuppressWarnings("unused")
	private static final String TAG = "demo.layout.transition.MainActivity";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setOnClickListenerForChild(R.id.frm_linear_01, true, true);
        setOnClickListenerForChild(R.id.frm_linear_02, true, true);
        setOnClickListenerForChild(R.id.frm_linear_03, true, false);
        setOnClickListenerForChild(R.id.frm_linear_04, false, true);
        
        LayoutTransitionUtil.sAnimatorListener = new Animator.AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {}
			
			@Override
			public void onAnimationRepeat(Animator animation) {}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				ObjectAnimator objectAnimator = (ObjectAnimator) animation;
				if (objectAnimator.getTarget() instanceof TextView) {
					TextView textView = (TextView) objectAnimator.getTarget();
					textView.setTextColor(Color.GREEN);
				}
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {}
		};
    }

	private void setOnClickListenerForChild(int id, boolean enable, boolean needScroll) {
		ViewGroup viewGroup = (ViewGroup)findViewById(id);
		
		if(enable){
			if(needScroll){
				LayoutTransitionUtil.enableChangeTransition(viewGroup.getLayoutTransition());
			}else{
				LayoutTransitionUtil.setupChangeAnimationOneTime(viewGroup);
			}
			
		}
        for(int i = 0;i<viewGroup.getChildCount();i++){
        	viewGroup.getChildAt(i).setOnClickListener(this);
        }
	}
	@Override
	public void onClick(View view) {
		if (view instanceof TextView) {
			TextView textView = (TextView) view;
			if(textView.getText().equals("")){
				textView.setText("long long long text");
			}else{
				textView.setText("");
			}
			textView.setTextColor(Color.BLUE);
		};
		LayoutTransitionUtil.setupChangeAnimation((ViewGroup)findViewById(R.id.frm_linear_04));
	}
}
