# fragment-inside-fragment

Fragmentの中にFragmentをパーツとして配置するデモ。

- 親FragmentはScrollViewを持ち、その中に子Fragmentを配置する。
- 各子FragmentはRecyclyerViewを持ち、リストアイテムを表示する。

<image src="https://user-images.githubusercontent.com/69252773/117393392-0361d900-af2f-11eb-8cd2-40c7ce924096.png" width="240px">

## 参考
- https://stackoverflow.com/questions/6672066/fragment-inside-fragment
- https://qiita.com/yoppie_x/items/30b91d63f55b775af3c5


## 課題

各子FragmentでRecyclerViewを持っているが、全FragmentのアイテムのViewがメモリに展開されたままになる。

（Fragment in Fragmentだと、全ての子Fragmentのライフサイクルが同時に onResume になるためか？）

RecyclerViewのように、表示されているFragmentかつ表示されているリストアイテムだけinflateしたい。

↓

実装概念はありそう、RecyclerView in Epoxy で実現できるか調べる。

- https://techblog.lclco.com/entry/2019/10/02/100000


# Different View Type in Epoxy

異なるViewTypeアイテムを持つRecyclerViewをEpoxyで実装した。

- 異なるitemレイアウトを任意の順番で配置
- グリッドレイアウト
- ViewModelと連携
  - 疑似的に遅延させてリストを更新
  - LiveDataでアイテムの変更を監視
  - アイテムタップをDataBindingを通してViewModelで処理

<image src="https://user-images.githubusercontent.com/69252773/118109975-cf9a1e00-b41c-11eb-8723-9386b752154b.png" width="240px">


## 参考
- https://github.com/airbnb/epoxy
- https://qiita.com/i_greenwood/items/e945bc013f3cc3883865#recyclerview%E3%81%AE%E3%82%BB%E3%83%83%E3%83%88%E3%82%A2%E3%83%83%E3%83%97
- https://qiita.com/hohohoris/items/97e90f318d4f3d8134e5#model
- https://stackoverflow.com/questions/60129670/android-epoxyrecyclerview-switching-orientation
- https://proandroiddev.com/building-complex-screens-in-a-recyclerview-with-epoxy-97845e51f4da
- https://proandroiddev.com/android-paging-library-with-multiple-view-type-68f85fe1222d
- https://github.com/airbnb/epoxy/issues/772
- https://github.com/airbnb/epoxy/issues/224
