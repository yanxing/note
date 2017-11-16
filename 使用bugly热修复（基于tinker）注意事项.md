1.如果app需要多渠道，可以使用美团的多渠道打包工具，避免针对多个渠道打补丁包（渠道代码一样）。<br>
2.补丁下发后，杀死进程重启app会自动下载合并补丁包，再次杀死进程重启app补丁包才生效。<br>
3.基线包tinkerId和补丁包tinkerId具有唯一性，下发时补丁包会根据包含的基线包tinkerId匹配基线app，进行合并。<br>
4.tinker不支持instant run模式，日常调试可以关闭tinker。<br>
5.选择app/build/outputs/patch目录下的补丁包(patch_signed_7zip.apk)上传，<br>
* patch_unsigned.apk    没有签名的补丁包<br>
* patch_signed.apk      签名后的补丁包<br>
* patch_signed_7zip.apk 签名后并使用7zip压缩的补丁包，上传补丁使用这个<br>

6.tinkerPatch->ignoreWarning=true<br>
7.如果针对同一个基准包发补丁，后面的补丁会覆盖前面的。
