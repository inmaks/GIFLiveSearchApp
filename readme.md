
# GIFLiveSearch

This app is written mostly in Jetpack Compose, it uses materail3 and latest experimental LazyStagerredGrid.
Because of that, app will work only on Android 13(SDK 33) or later versions.

Implemented requirements:
- Live search - request is sent in 600 milliseconds after the user has entered some input;
- Results are displayed in the grid of items;
- Request pagination - loads enough items to populate the list and load more items every time the user scrolls to the end of the list;
- Using modern Android development approaches and libraries such as Android MVVM, Coroutines, and other jetpack compose modules;
- Unit tests
- Loading more results "seamlessly"

Current known bugs:
- Sometimes search doesn't trigger on last entered letter (couldn't reproduce it constantly to know the exact reason);
- Sometimes when searching for new gifs, grid doesn't scroll all the way up;

Here is small video that will help You understand main concept:

https://user-images.githubusercontent.com/46053138/211217495-57debb5b-e78c-43a3-864b-3ae19eca9301.mp4

