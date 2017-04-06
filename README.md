# MvpLibrary
提供了基本http请求的MVP框架。

优点：

- 可以做单元测试（解耦就不说了，正是因为解耦才可以做单元测试）
  
有这样一种情况：需求文档有了，服务器接口开发好了，UI迟迟出不来。以前开发只能找一些low的图片去替代，等到UI切出图片来我们再替换掉low的图片。这样无形中给我们开发增加了工作量，项目中也会增加一些无用的图片。 
 
MVP很好的解决了这种情况。这个示例中会体现出来。

- 代码结构清晰（我们代码中体验）
- 业务模块复用（这个还没想出好的例子）

缺点就不说了(嘿嘿)

下面带你体验一下：

Model层	
	
	//必须继承自Bean，否则下面的Presenter传入的泛型会报错（做了泛型限定）
	public class PicClassifyBean extends Bean{

	}

Model层(HttpModel不用管，类库中已经写好了直接使用就可以（严格来说这块应该属于Presenter层。我分离了出来，而且划到了Model层中）)

	//MainPresenter 泛型控制mPresenter的类型，方便直接调用MainPresenter的方法
	public class PicClassifyModel extends HttpModel<MainPresenter>{
		public void getPicClassify(){
			TianGouApi tianGouApi = MainApp.getTianGouApi();
        	tianGouApi.getRxPicClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicClassifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mPresenter.onHttpStart();
                    }

                    @Override
                    public void onNext(PicClassifyBean picClassifyBean) {
                        mPresenter.onHttpSuccess(picClassifyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresenter.onHttpFaild(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });
		}
	}

Presenter层(HttpPresenter不用管，类库中已经写好了直接使用就可以)
	
	//PicClassifyModel 真正做网络请求的类，PicClassifyBean返回的的实体类，MainActivity 视图层类
	public class MainPresenter extends HttpPresenter<PicClassifyModel,PicClassifyBean,MainActivity>{
	
	    public MainPresenter(Class clazz) {
	        super(clazz);
	    }
		//泛型中指定PicClassifyModel的对象对应mModel，PicClassifyModel类中的方法可以在这里直接使用
	    public void getPicClassify(){
	        mModel.getPicClassify();
	    }
		//其实PicClassifyModel和MainPresenter应该都属于Presenter层，
		//我是为了扩展、复用分开，未来你可以在MainPresenter增加特有方法，而不影响网络请求，这也符合单一设计原则
	}

View层（view层可以是任何类，不局限于Activity和Fragment（不是Activity和Fragment时，可以实现一个页面多个网络请求，请在界面销毁的时候，把view层的对象释放掉。））

	//PicClassifyBean 请求成功后返回的实体类
	public class MainActivity extends AppCompatActivity implements IHttpView<PicClassifyBean>{ 
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
			//PicClassifyModel 真正执行网络请求的类
			mMainPresenter = new MainPresenter(PicClassifyModel.class);
	        mMainPresenter.attach(this);
			//执行网络请求
			mMainPresenter.getPicClassify();
	 	}
		@Override
	    public void onHttpStart() {
	        Log.i(TAG, "onHttpStart: ");
	    }
	
	    @Override
	    public void onHttpSuccess(PicClassifyBean picClassifyBean) {
	
	        List<PicClassifyBean.TngouBean> titles = picClassifyBean.getTngou();
	        mViewPagerAdapter.setData(titles);
	    }
	
	    @Override
	    public void onHttpFaild(Throwable t) {
	        Log.i(TAG, "onHttpFaild: ");
	    }

		@Override
	    protected void onDestroy() {
	        super.onDestroy();
	        mMainPresenter.dettach();
	    }
	}


结束语：设计思想、代码有什么不好的地方欢迎大家指出，一起进步。也欢迎大家多点几颗星，给点动力，写出更好更优的代码。

<h6 style="text-align:right">
	Auther: 陈占洋&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp</br>
	Email: zhanyang.chen@gmail.com
</h6>

