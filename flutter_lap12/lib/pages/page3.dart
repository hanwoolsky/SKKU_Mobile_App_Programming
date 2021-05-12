import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class Page3 extends StatelessWidget {
  final Map<String, String> arguments;
  Page3(this.arguments);

  @override
  Widget build(BuildContext context) {
    Page3CounterProvider counter3 = Provider.of<Page3CounterProvider>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text("Page 3"),
      ),
      body: Center(
        child:Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text("Hi! Welcome to Page 3"),
              Consumer<Page3CounterProvider> (
                builder: (context, counter, child) => Text(
                  '${counter3.counter}',
                  style: Theme.of(context).textTheme.headline4,
                ),
              )
            ]
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => counter3.incrementCounter(),
        tooltip: 'increment',
        child: Icon(Icons.add),
      ),
    );
  }
}

class Page3CounterProvider with ChangeNotifier {
  int _counter;
  get counter => _counter;

  Page3CounterProvider(this._counter);
  void incrementCounter(){
    _counter++;
    notifyListeners();
  }
}