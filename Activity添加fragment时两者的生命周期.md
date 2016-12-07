在Activity的onCreate方法中添加Fragment时的生命周期，Activity:<br>
onCreate->onStart <p>
接着Fragment调用<br>
onAttach->onCreate->onCreateView->onViewCreated->onActivityCreated->onStart<p>
然后Activity接着调用<br>
onResume<p>
最后Fragment调用<p>
onResume
