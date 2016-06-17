#FloatActionExpand

参考google官方Material Design设计规范的ToolBar和Dialog


##Dialog
![Alt text](./dialog.gif)



##Toobar
![Alt text](./toolbar.gif)


##How to use

###Constructor

```

FABBaseDialog(Context context, View fab, int resId)

FABBaseDialog(Context context, View fab, View toolbar)


```
###Show dismiss

```

void showAsToolBar()

void showAsDialog()

void dismiss()


```

###ClickListener

```

void setOnClickListener(int viewId,View.OnClickListener listener)

void setOnClickListener(int[] viewIds,View.OnClickListener listener)


```

##Useages

toobar layout
 
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="#FF4081"
    android:orientation="horizontal">

    <ImageView
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="48dp"
        android:padding="14dp"
        android:src="@drawable/ic_menu_camera"
        />

    <ImageView
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="48dp"
        android:padding="14dp"
        android:src="@drawable/ic_menu_gallery"
        />

    <ImageView
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="48dp"
        android:padding="14dp"
        android:src="@drawable/ic_menu_slideshow"
        />

    <ImageView
        android:id="@+id/image"
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="48dp"
        android:padding="14dp"
        android:src="@drawable/ic_menu_manage"
        />

</LinearLayout>

```


```

FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
fabBaseDialog = new FABBaseDialog(this,fab,R.layout.toolbar);
fabBaseDialog.setOnClickListener(R.id.image, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabBaseDialog.dismiss();
            }
        });
        
... ...
        
fabBaseDialog.showAsToolBar();

```


##Dependencies

```
compile 'com.daijia.android:library:1.0.1'
```

##License

[The MIT License (MIT)
](https://opensource.org/licenses/mit-license.php)