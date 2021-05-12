import 'package:flutter/material.dart';
import 'package:flutter_lap12/pages/page1.dart';
import 'package:flutter_lap12/pages/page2.dart';
import 'package:flutter_lap12/pages/page3.dart';
import 'package:flutter_lap12/pages/page4.dart';
import 'package:provider/provider.dart';


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (context) => Page1CounterProvider(0)),
          ChangeNotifierProvider(create: (context) => Page2CounterProvider(0)),
          ChangeNotifierProvider(create: (context) => Page3CounterProvider(0)),
          ChangeNotifierProvider(create: (context) => Page4CounterProvider(0)),
        ],
        child: MaterialApp(
          title: 'Flutter Demo',
          theme: ThemeData(
            primarySwatch: Colors.blue,
          ),
          initialRoute: '/',
          onGenerateRoute: (routerSettings) {
            switch(routerSettings.name){
              case '/':
                return MaterialPageRoute(builder: (_) => MyHomePage(title: "Dynamic Routing"));
              case '/page1.dart':
                return MaterialPageRoute(builder: (_) => Page1((routerSettings.arguments)));
              case '/page2.dart':
                return MaterialPageRoute(builder: (_) => Page2(routerSettings.arguments));
              case '/page3.dart':
                return MaterialPageRoute(builder: (_) => Page3(routerSettings.arguments));
              case '/page4.dart':
                return MaterialPageRoute(builder: (_) => Page4(routerSettings.arguments));
              default:
                return MaterialPageRoute(builder: (_) => MyHomePage(title: "Error Unknown Route!"));
            }
          }
        )
    );
  }
}

class MyHomePage extends StatelessWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  Widget build(BuildContext context) {
    final Page1CounterProvider counter = Provider.of<Page1CounterProvider>(context);
    final Page2CounterProvider counter2 = Provider.of<Page2CounterProvider>(context);
    final Page3CounterProvider counter3 = Provider.of<Page3CounterProvider>(context);
    final Page4CounterProvider counter4 = Provider.of<Page4CounterProvider>(context);

    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              child: Text("Move to Page 1"),
              onPressed: (){
                Navigator.pushNamed(
                    context,
                    '/page1.dart',
                  arguments: {
                    "user-msg1": "Move to Page1 by Dynamic Navigation",
                    "user-msg2": "Welcome to Page1",
                  },
                );
              },
            ),
            ElevatedButton(
                child: Text("Move to Page 2"),
                onPressed: () {
                  Navigator.pushNamed(context, '/page2.dart');
                },
            ),
            ElevatedButton(
              child: Text("Move to Page 3"),
              onPressed: () {
                Navigator.pushNamed(context, '/page3.dart');
              }
            ),
            ElevatedButton(
                child: Text("Move to Page 4"),
                onPressed: () {
                  Navigator.pushNamed(context, '/page4.dart');
                }
            ),
            ElevatedButton(
                child: Text("Unknown"),
                onPressed: (){
                  Navigator.pushNamed(context, 'unknown');
                }
            ),
            Consumer<Page1CounterProvider> (
              builder: (context, counter, child) => Text(
                'Page 1 Count: ${counter.counter}',
                style: Theme.of(context).textTheme.headline5,
              ),
            ),
            Consumer<Page2CounterProvider> (
              builder: (context, counter, child) => Text(
                'Page 2 Count: ${counter2.counter}',
                style: Theme.of(context).textTheme.headline5,
              ),
            ),
            Consumer<Page3CounterProvider> (
              builder: (context, counter, child) => Text(
                'Page 3 Count: ${counter3.counter}',
                style: Theme.of(context).textTheme.headline5,
              ),
            ),
            Consumer<Page4CounterProvider> (
              builder: (context, counter, child) => Text(
                'Page 4 Count: ${counter4.counter}',
                style: Theme.of(context).textTheme.headline5,
              ),
            )
          ],
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
