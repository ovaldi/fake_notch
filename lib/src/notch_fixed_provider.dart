import 'package:flutter/widgets.dart';

class NotchFixedProvider extends InheritedWidget {
  const NotchFixedProvider({
    Key key,
    this.width = 0,
    this.height = 0,
    Widget child,
  })  : assert(width != null),
        assert(height != null),
        super(key: key, child: child);

  final double width;
  final double height;

  @override
  bool updateShouldNotify(InheritedWidget oldWidget) {
    NotchFixedProvider oldProvider = oldWidget as NotchFixedProvider;
    return width != oldProvider.width || height != oldProvider.height;
  }

  static NotchFixedProvider of(BuildContext context) {
    return context.inheritFromWidgetOfExactType(NotchFixedProvider)
        as NotchFixedProvider;
  }
}
