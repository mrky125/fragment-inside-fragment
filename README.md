# fragment-inside-fragment

Fragmentの中にFragmentをパーツとして配置するデモ。

- 親FragmentはScrollViewを持ち、その中に子Fragmentを配置する。
- 各子FragmentはRecyclyerViewを持ち、リストアイテムを表示する。

<image src="https://user-images.githubusercontent.com/69252773/117393392-0361d900-af2f-11eb-8cd2-40c7ce924096.png" width="240px">


# 課題

各子FragmentでRecyclerViewを持っているが、全FragmentのアイテムのViewがメモリに展開されたままになる。

（Fragment in Fragmentだと、全ての子Fragmentのライフサイクルが同時に onResume になるためか？）

RecyclerViewのように、表示されているFragmentかつ表示されているリストアイテムだけinflateしたい。

↓

実装概念はありそう、RecyclerView in Epoxy で実現できるか調べる。

- https://techblog.lclco.com/entry/2019/10/02/100000
