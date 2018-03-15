打印日志放在setContentView之前，各生命周期super方法之后，Activity和Fragment生命周期执行关系：  
  *  Activity-->onCreate Activity正在被创建，此时子线程操作UI不会报线程错误。
  *  Fragment: onAttach  Fragment和Activity进行关联，即Fragment依附到Activity上。
  *  Fragment: onCreate  
  *  Fragment: onCreateView  初始化Fragment的布局，加载布局和findViewById的操作通常在此函数内完成。  
  *  Fragment: onViewCreated，此方法执行完，Activity的onCreate方法也将执行完（onCreate方法无其他逻辑）。   
  *  Fragment: onActivityCreated，此方法发生在Activity的onCreate方法执行完之后。    
  *  Fragment: onStart  Fragment变为可见，但是不位于前台，不可交互。
  *  Activity-->onStart  
  *  Activity-->onResume Activity可见，可以进行交互。 
  *  Fragment: onResume  
  *  Fragment: onPause  
  *  Activity-->onPause  
  *  Fragment: onStop  
  *  Activity-->onStop  
  *  Fragment: onDestroyView  销毁Fragment视图。  
  *  Fragment: onDestroy  销毁Fragment。
  *  Fragment: onDetach   解除与Activity的绑定。  
  *  Activity-->onDestroy  
  Fragment依附Activity，在创建的过程中，Activity带领着Fragment，在销毁的过程中，Fragment带领着Fragment。
