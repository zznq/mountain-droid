package zznq.burly.word.mountain_droid;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/*
 * Class provided by 
 * http://developer.android.com/guide/topics/media/camera.html#custom-camera 
 * 
 * @author zznq
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private String CAMERAPREVIEWTAG = "Mountain Droid Camera Preview";
	
	public CameraPreview(Context context, Camera camera){
		super(context);
		
		mCamera = camera;
		
		mHolder = getHolder();
		mHolder.addCallback(this);
		
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	public void surfaceCreated(SurfaceHolder holder){
		try {
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		} catch(IOException e) {
			Log.d(CAMERAPREVIEWTAG, "Error setting camera preview: " + e.getMessage());
		}
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		;// TODO: clean up i guess
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h){
		if(mHolder.getSurface() == null)
			return;
		
		try {
			mCamera.stopPreview();
		} catch(Exception e) {
			;//ignore
		}
		
		try{
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		} catch (Exception e) {
			Log.d(CAMERAPREVIEWTAG, "Error starting camera preview:" + e.getMessage());
		}
	}
}
	