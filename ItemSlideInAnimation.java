import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public class ItemSlideInAnimation {
    private Interpolator interpolator;
    private int lastPosition = -1;
    private int duration = 300;

    public ItemSlideInAnimation() {
        interpolator = new AccelerateDecelerateInterpolator();
    }

    /**
     * Resets the internals like its the first time
     */
    public void clear() {
        lastPosition = -1;
    }

    /**
     * Adds the animation to the current view
     * @param position
     * @param view
     */
    public void add(int position, View view) {
        if (position > lastPosition) {
            for (Animator anim : getAnimators(view)) {
                anim.setDuration(duration).start();
                anim.setInterpolator(interpolator);
            }
            lastPosition = position;
        } else {
            ViewCompat.setAlpha(view, 1);
            ViewCompat.setTranslationY(view, 0);
        }
    }

    private Animator[] getAnimators(View view) {
        return new Animator[] {
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f),
                ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0)
        };
    }

}
