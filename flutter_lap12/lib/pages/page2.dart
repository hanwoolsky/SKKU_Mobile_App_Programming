import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class Page2 extends StatelessWidget {
  final Map<String, String> arguments;
  Page2(this.arguments);

  @override
  Widget build(BuildContext context) {
    Page2CounterProvider counter2 = Provider.of<Page2CounterProvider>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text("Page 2"),
      ),
      body: Center(
        child:Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text("Hi! Welcome to Page 2"),
              Consumer<Page2CounterProvider> (
                builder: (context, counter, child) => Text(
                  '${counter2.counter}',
                  style: Theme.of(context).textTheme.headline4,
                ),
              )
            ]
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => counter2.incrementCounter(),
        tooltip: 'increment',
        child: Icon(Icons.add),
      ),
    );
  }
}

class Page2CounterProvider with ChangeNotifier {
  int _counter;
  get counter => _counter;

  Page2CounterProvider(this._counter);
  void incrementCounter(){
    _counter++;
    notifyListeners();
  }
}