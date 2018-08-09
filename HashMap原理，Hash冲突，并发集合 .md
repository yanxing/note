 HashMap是通过数组和链表实现，通过散列码（hashcode）计算存储的位置，当put key，通过哈希函数计算数组下标i，然后插入到数组中，如果不同的key两次计算的i相等，就形成了hash冲突（碰撞）。解决冲突的方法有：开放定址法、再散列函数法、链地址法（数组+链表，HashMap用的方法）。数组是HashMap的主体，链表是为了解决冲突的，如果查找到的数组位置不包含链表，则查找和添加仅需一次寻址；若查到的数组位置包含链表，则要遍历链表，存在则进行替换，否则添加。
      HashMap是线程非安全的，并发集合有HashTable、Vector、CurrentHashMap、CopyOrWirteArrayList。
