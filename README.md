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