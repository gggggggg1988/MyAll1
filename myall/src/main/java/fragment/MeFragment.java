package fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myall.R;
import com.example.administrator.myall.RegisterActivity;
import com.example.administrator.myall.utils.LogUtils;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {


    private Tencent mTencent;
    private UserInfo userInfo;
    public static IUiListener userInfoListener;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.login).setOnClickListener(this);
        view.findViewById(R.id.register).setOnClickListener(this);
        view.findViewById(R.id.share).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                //TODO implement
                login();
                break;
            case R.id.register:
                //TODO implement
                mActivity.startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
            case R.id.share:
                showShare();
                break;
        }
    }

    public void login()
    {
        userInfoListener = new TencentCallBack();
        mTencent = Tencent.createInstance("1105433218", getActivity().getApplicationContext());
        if (!mTencent.isSessionValid())
        {
            mTencent.login(this, "all",userInfoListener);
        }
    }

    private void showShare() {
        ShareSDK.initSDK(mActivity);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(mActivity);
    }

   public class TencentCallBack implements IUiListener{

        @Override
        public void onComplete(Object value) {
            System.out.println("有数据返回..");
            if (value == null) {
                return;
            }

            try {
                JSONObject jo = (JSONObject) value;

                int ret = jo.getInt("ret");

                System.out.println("json=" + String.valueOf(jo));

                if (ret == 0) {
                    Toast.makeText(getActivity(), "登录成功",
                            Toast.LENGTH_LONG).show();

                    String openID = jo.getString("openid");
                    String accessToken = jo.getString("access_token");
                    String expires = jo.getString("expires_in");
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken, expires);
                }

                System.out.println("开始获取用户信息");
                userInfo = new UserInfo(getActivity(), mTencent.getQQToken());
                userInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object value) {
                        LogUtils.i("user info ----"+value.toString());
                        Toast.makeText(getActivity(), value.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(UiError uiError) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });

            } catch (Exception e) {
                // TODO: handle exception
            }


        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }



}
